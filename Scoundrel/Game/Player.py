from Cards import *
from GameUtils import IOUtils

class Player:
    def __init__(self):
        self.health = 20
        self.maxHealth = 20
        self.currentWeapon = None
        self.canFlee = True
        self.alive = True
    
    def pickCard(self, validOptions):
        choice = IOUtils.promptForInt(f'Select an option {validOptions}: ', validOptions)
        return choice
    
    def takeDamage(self, incomingDamage):
        self.health = self.health - incomingDamage
        if (self.health <= 0):
            self.alive = False
            print('You died.')
    
    def healHealth(self, healing):
        self.health = self.health + healing
        if (self.health > self.maxHealth):
            self.health = self.maxHealth
    
    def equipWeapon(self, weapon):
        self.currentWeapon = None
        self.currentWeapon = weapon
        print(f'Equipped {weapon}')
    
    def displayInfo(self):
        if (self.currentWeapon == None):
            return f'HP: {self.health}, Weapon: None, Can Flee? {self.canFlee}'
        return f'HP: {self.health}, Weapon: {self.currentWeapon.bladeDurability} Durability, Can Flee? {self.canFlee}'