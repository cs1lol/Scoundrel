from Cards import *
from Game import *
from GameUtils import *


player = Player()
dungeon = Dungeon()
dungeonUtil = DungeonUtils()

dungeonUtil.exploreRoom(dungeon, player)

if player.alive and dungeon.deckComplete:
    print('You Won!')
    print(f'You had {player.health} HP left.')
else:
    print('You Lost.')
    print(f'There were {len(dungeon.deck.deck)} cards left in the deck')