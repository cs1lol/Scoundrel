from .Card import Card

class Monster(Card):
    def __init__(self, value, suit):
        super().__init__(value, suit)
        self.damage = int(self.value)
    
    def setCardType(self):
        return 'monster'
    
    def useCard(self, player, attackBarehanded=False):
        if (attackBarehanded or (player.currentWeapon == None)):
            print('You choke the monster')
            player.takeDamage(self.damage)
            return True
        outgoingDamage = self.damage - player.currentWeapon.bladeDamage
        if (player.currentWeapon.bladeDurability != 15):
            outgoingDamage = 0
        killedMonster = player.currentWeapon.attack(self)
        if (killedMonster):
            if (outgoingDamage < 0):
                outgoingDamage = 0
            player.takeDamage(outgoingDamage)
            return killedMonster
        else:
            return killedMonster
    
    def displayCard(self):
        print(self)
        print('What would you like to do?')
        print('(1) Attack Monster')
        print('(2) Choke Monster')
        print('(3) Go Back')
        return [1,2,3]