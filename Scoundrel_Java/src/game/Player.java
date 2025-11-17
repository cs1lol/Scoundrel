package game;

import cards.Card;
import cards.MonsterCard;
import cards.PotionCard;
import cards.WeaponCard;

public class Player {
    private int health;
    private int maxHealth;
    private WeaponCard currentWeapon;
    private boolean canFlee;
    private boolean isAlive;
    private int fleeCountdown;

    public Player() {
        this.health = 20;
        this.maxHealth = 20;
        this.currentWeapon = null;
        this.canFlee = true;
        this.isAlive = true;
        this.fleeCountdown = 0;
    }

    public void modifyHealth(int health) {
        this.health += health;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        if (this.health <= 0) {
            this.isAlive = false;
        }
    }

    public boolean playCard(Card targetCard) {
        if (targetCard instanceof WeaponCard weaponCard) {
            return equipCard(weaponCard);
        } else if (targetCard instanceof PotionCard potionCard) {
            return consumeCard(potionCard);
        } else if (targetCard instanceof MonsterCard monsterCard) {
            return attackCard(monsterCard);
        }
        return false;
    }

    public void checkPlayer() {
        if (this.health <= 0) {
            this.isAlive = false;
        }
        this.fleeCountdown -= 1;
        if (this.fleeCountdown == 0) {
            this.canFlee = true;
        }
    }


    // Private methods for playing each card type
    private boolean attackCard(Card targetCard) {
        if (!(targetCard instanceof MonsterCard)) {
            return false;
        }
        if (this.currentWeapon == null) {
            modifyHealth(-targetCard.getValue());
            return true;
        } else if (this.currentWeapon.getDurability() == 15) {
            int damageTaken = targetCard.getValue() - this.currentWeapon.getValue();
            if (damageTaken <= 0) {
                damageTaken = 0;
            }
            modifyHealth(-damageTaken);
            this.currentWeapon.modifyDurability(targetCard.getValue());
            return true;
        } else if (targetCard.getValue() >= this.currentWeapon.getDurability()) {
            modifyHealth(-targetCard.getValue());
            return true;
        } else if (targetCard.getValue() < this.currentWeapon.getDurability()) {
            this.currentWeapon.modifyDurability(targetCard.getValue());
            return true;
        } else {
            return false;
        }
    }

    private boolean equipCard(Card targetCard) {
        if (targetCard instanceof WeaponCard weaponCard) {
            setCurrentWeapon(weaponCard);
            return true;
        }
        return false;
    }

    private boolean consumeCard (Card targetCard) {
        if (targetCard instanceof PotionCard potionCard) {
            modifyHealth(potionCard.getValue());
            return true;
        }
        return false;
    }
    
    // Getters and Setters
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int newHealth) {
        this.health = newHealth;
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }
    public void setMaxHealth(int newMaxHealth) {
        this.maxHealth = newMaxHealth;
    }
    public WeaponCard getCurrentWeapon() {
        return this.currentWeapon;
    }
    public void setCurrentWeapon(WeaponCard newCurrentWeapon) {
        this.currentWeapon = newCurrentWeapon;
    }
    public boolean isCanFlee() {
        return this.canFlee;
    }
    public void setCanFlee(boolean canFlee) {
        this.canFlee = canFlee;
        this.fleeCountdown = 3;
    }
    public boolean isAlive() {
        return this.isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String printInfo() {
        if (this.currentWeapon == null) {
            return "HP: " + this.health + ", Weapon: Your Fists, Can Escape?: " + this.canFlee;
        }
        return "HP: " + this.health + ", Weapon: " + this.currentWeapon + ", Can Escape?: " + this.canFlee;
    }

    @Override
    public String toString() {
        if (this.currentWeapon == null) {
            return "HP: " + this.health + ", Weapon: Your Fists, Can Escape?: " + this.canFlee;
        }
        return "HP: " + this.health + ", Weapon: " + this.currentWeapon + ", Can Escape?: " + this.canFlee;
    }
}
