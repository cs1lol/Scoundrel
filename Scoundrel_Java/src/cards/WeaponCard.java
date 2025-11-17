package cards;

public class WeaponCard extends Card {
    private int durability;

    public WeaponCard(int value, char suit) {
        super(value, suit);
        this.setType("WEAPON");
        this.durability = 15;
    }

    public int getDurability() {
        return this.durability;
    }
    public void modifyDurability(int newDurability) {
        this.durability = newDurability;
    }
    public void setDurability(int newDurability) {
        this.durability = newDurability;
    }
}
