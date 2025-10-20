# from .Card import Card
from .Monster import Monster
from .Potion import Potion
from .Weapon import Weapon
import random as rand

class Deck:
    def __init__(self):
        self.VALUES = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
        self.SUITS = ['s', 'c', 'h', 'd']
        self.deck = self.generateDeck()
        self.shuffleDeck()
    def __repr__(self):
        return '%r' % (self.deck)
    def generateDeck(self):
        deck = []
        for suit in self.SUITS:
            for value in self.VALUES:
                if suit in ['h', 'd'] and value in [11, 12, 13, 14]:
                    continue
                if (suit == 'h'):
                    card = Potion(value, suit)
                elif (suit == 'd'):
                    card = Weapon(value, suit)
                else:
                    card = Monster(value, suit)
                deck.append(card)
        return deck
    def shuffleDeck(self):
        rand.shuffle(self.deck)