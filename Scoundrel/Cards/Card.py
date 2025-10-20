class Card:
    VALUE_COMPARISON_MAP = {
        2: '2',
        3: '3',
        4: '4',
        5: '5',
        6: '6',
        7: '7',
        8: '8',
        9: '9',
        10: '10',
        11: 'J',
        12: 'Q',
        13: 'K',
        14: 'A'
    }
    SUIT_COMPARISON_MAP = {
        's': '♠',
        'c': '♣',
        'h': '♥',
        'd': '♦'
    }
    
    def __init__(self, value, suit):
        self.value = str(value)
        self.suit = suit
        self.RANK, self.PRETTY_SUIT = self.prettyCard()
        self.TYPE = self.setCardType()

    def __repr__(self):
        return f'{self.value}{self.PRETTY_SUIT}'

    def __str__(self):
        return f'A {self.TYPE} of strength {self.value}    (The {self.RANK} of {self.PRETTY_SUIT})'

    def prettyCard(self):
        rank = self.VALUE_COMPARISON_MAP.get(self.value, self.value)
        suit = self.SUIT_COMPARISON_MAP.get(self.suit, self.suit)
        return rank, suit

    def getValueOf(self):
        return int(self.value)
    
    def discardCard(self):
        del self