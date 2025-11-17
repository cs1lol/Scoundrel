package cards;

public class PotionCard extends Card {
    public PotionCard(int value, char suit) {
        super(value, suit);
        this.setType("POTION");
    }
}
