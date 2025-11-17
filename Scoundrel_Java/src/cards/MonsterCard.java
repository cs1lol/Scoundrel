package cards;

public class MonsterCard extends Card {
    public MonsterCard(int value, char suit) {
        super(value, suit);
        this.setType("MONSTER");
    }
}
