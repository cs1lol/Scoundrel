from Cards import Deck, Weapon, Monster, Potion

class Dungeon:
    def __init__(self):
        self.deck = Deck()
        self.currentRoom = self.generateFirstRoom()
        self.roomComplete = False
        self.deckComplete = False
    
    # Completed
    def generateFirstRoom(self):
        firstRoom = []
        monsterCount = 0
        foundWeapon = False
        foundPotion = False
        cardsToRemove = []
        for card in self.deck.deck:
            if ((isinstance(card, Monster)) and (int(card.value) < 5) and (monsterCount < 2)):
                firstRoom.append(card)
                cardsToRemove.append(card)
                monsterCount += 1
            elif ((isinstance(card, Weapon)) and (int(card.value) < 5) and (not foundWeapon)):
                firstRoom.append(card)
                cardsToRemove.append(card)
                foundWeapon = True
            elif ((isinstance(card, Potion)) and (int(card.value) < 5) and (not foundPotion)):
                firstRoom.append(card)
                cardsToRemove.append(card)
                foundPotion = True
            if (len(firstRoom) == 4):
                break
        for card in cardsToRemove:
            self.deck.deck.remove(card)
        return firstRoom

    # Completed
    def generateRoom(self):
        self.currentRoom = []
        nextRoom = []
        if (len(self.deck.deck) == 0):
            self.deckComplete = True
        elif (len(self.deck.deck) < 3):
            nextRoom = self.deck.deck[0:]
            del self.deck.deck[0:]
        else:
            nextRoom = self.deck.deck[0:4]
            del self.deck.deck[0:4]
        self.currentRoom = nextRoom
    
    # Completed
    def checkRoom(self):
        if (len(self.currentRoom) == 0):
            self.roomComplete = True
            return
        for card in self.currentRoom:
            if (card.TYPE == 'monster'):
                self.roomComplete = False
                break
            else:
                self.roomComplete = True
    
    # Completed
    def displayRoom(self, player):
        number = 1
        validOptions = []
        for card in self.currentRoom:
            validOptions.append(number)
            print(f'({number}) {card}')
            number += 1
        print(f'({number}) Attempt to leave the room')
        validOptions.append(number)
        number += 1

        return validOptions
    
    # Completed
    def exitRoom(self):
        # If the room is not complete exit early
        if (not self.roomComplete):
            return False
        self.generateRoom()
        self.roomComplete = False
        return True
    
    # TODO
    def fleeRoom(self):
        for card in self.currentRoom:
            self.deck.deck.append(card)
        self.generateRoom()
        self.roomComplete = False
        return True