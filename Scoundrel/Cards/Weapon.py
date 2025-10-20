from .Card import Card
from .Monster import Monster

class Weapon(Card):
    def __init__(self, value, suit):
        super().__init__(value, suit)
        self.bladeDamage = int(value)
        self.bladeDurability = 15

    def setCardType(self):
        return 'weapon'
    
    def attack(self, monster): # test_weapon.attack(test_monster_1)
        if (not isinstance(monster, Monster)):
            print('Must attack a monster')
            return False #Just in case something happens
        if (int(monster.value) >= self.bladeDurability):
            print('Your blade will not withstand the blow, attack a weaker monster.')
            return False #Does not affect the targeted Card
        
        print('You killed the monster, your blade dulls with the blow.')
        self.bladeDurability = int(monster.value)
        return True
    
    def useCard(self, player):
        player.equipWeapon(self)


    def displayCard(self):
        print(self)
        print('What would you like to do?')
        print('(1) Equip Weapon')
        print('(2) Discard Weapon')
        print('(3) Go Back')
        return [1,2,3]