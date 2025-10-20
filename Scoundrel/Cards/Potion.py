from .Card import Card

class Potion(Card):
    def __init__(self, value, suit):
        super().__init__(value, suit)
        self.strength = int(self.value)
    
    def setCardType(self):
        return 'potion'
    
    def useCard(self, player):
        player.healHealth(self.strength)
        print(f'Healed {self.strength} HP.')
    
    def displayCard(self):
        print(self)
        print('What would you like to do?')
        print('(1) Drink Potion')
        print('(2) Discard Potion')
        print('(3) Go Back')
        return [1,2,3]