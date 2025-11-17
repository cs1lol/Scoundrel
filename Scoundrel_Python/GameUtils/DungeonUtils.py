from Game import Dungeon, Player
import os
import time

class DungeonUtils:
    def __init__(self):
        pass
    def exploreRoom(self, dungeon, player):
        isExploring = True
        choices = []
        while (isExploring and player.alive and (not dungeon.deckComplete)):
            time.sleep(1.5)
            os.system('clear')
            dungeon.checkRoom()
            print(f'{len(dungeon.deck.deck)} cards left')
            print(player.displayInfo())
            choices = dungeon.displayRoom(player)
            choice = player.pickCard(choices)

            if ((choice - 1) >= len(dungeon.currentRoom)):
                self.promptForExit(dungeon, player)
            else:
                card = dungeon.currentRoom[choice - 1]
                choices = card.displayCard()
                choice = player.pickCard(choices)
                if (choice == 2):
                    self.discardCards(card, dungeon, player)
                elif (choice == 3):
                    continue
                else:
                    self.useCards(card, dungeon, player)
        time.sleep(1.5)
        os.system('clear')
    
    def useCards(self, card, dungeon, player):
        if (card.TYPE != 'monster'):
            card.useCard(player)
            dungeon.currentRoom.remove(card)
            card.discardCard()
            return True
        else:
            killedMonster = card.useCard(player)
            if (killedMonster):
                dungeon.currentRoom.remove(card)
                card.discardCard()
            return killedMonster
    def discardCards(self, card, dungeon, player):
        if (card.TYPE != 'monster'):
            card.discardCard()
            dungeon.currentRoom.remove(card)
            return True
        else:
            killedMonster = card.useCard(player, True)
            dungeon.currentRoom.remove(card)
            card.discardCard()
            return True
    def promptForExit(self, dungeon, player):
        print(f'(1) Exit room [All monsters have been killed]')
        print(f'(2) Flee the room')
        choices = [1,2]
        choice = player.pickCard(choices)

        if (choice == 1):
            didExitRoom = dungeon.exitRoom()
            if (not didExitRoom):
                print('Cannot exit, the room is not clear of monsters yet.')
            else:
                print('Exiting room')
                player.canFlee = True
        else:
            if (player.canFlee):
                print('Fleeing room')
                dungeon.fleeRoom()
                player.canFlee = False
            else:
                print('Cannot flee, you must challenge this room.')