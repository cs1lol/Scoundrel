package cards;

public abstract class Card {
    
    private int value;
    private char suit;
    private String type;

    public Card(int value, char suit) {
        this.value = value;
        this.suit = suit;
        this.type = null;
    }

    public int getValue() {
        return this.value;
    }
    public char getSuit() {
        return this.suit;
    }
    public String getType() {
        return this.type;
    }
    public void setValue(int newValue) {
        this.value = newValue;
    }
    public void setSuit(char newSuit) {
        this.suit = newSuit;
    }
    public void setType(String newType) {
        this.type = newType;
    }

    @Override
    public String toString() {
        return "A " + this.type.toLowerCase() + " of strength " + this.value;
    }
}
