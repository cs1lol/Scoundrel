package cards;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static final char[] SUITS = {'s', 'c', 'd', 'h'};
    private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static boolean isEmpty;

    public static void createDeck() {
        for(char suit : SUITS) {
            for(int value : VALUES) {
                switch (suit) {
                    case 'd':
                        if (value > 10) {
                            break;
                        }
                        deck.add(new WeaponCard(value, suit));
                        break;
                    case 'h':
                        if (value > 10) {
                            break;
                        }
                        deck.add(new PotionCard(value, suit));
                        break;
                    default:
                        deck.add(new MonsterCard(value, suit));
                        break;
                }
            }
        }
        shuffleDeck();
        isEmpty = false;
    }

    public static Card pickCard() {
        Card topCard = deck.get(0);
        deck.remove(0);
        if (getIsEmpty()) {
            setIsEmpty(true);
        }
        return topCard;
    }
    
    public static Card pickCard(int valueCondition, String typeCondition, boolean isBelow) {
        if (isBelow) {
            for (Card card : deck) {
                if ((card.getValue() <= valueCondition) && (typeCondition.equals(card.getType()))) {
                    deck.remove(card);
                    return card;
                }
            }
        }
        for (Card card : deck) {
            if ((card.getValue() == valueCondition) && (typeCondition.equals(card.getType()))) {
                deck.remove(card);
                return card;
            }
        }
        return null;
    }

    public static void printDeck() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }
    public static void shuffleDeck() {
        Collections.shuffle(deck);
    }
    public static void addToDeck(Card card) {
        deck.add(card);
    }

    public static boolean getIsEmpty() {
        return isEmpty;
    }
    public static void setIsEmpty(boolean newIsEmpty) {
        isEmpty = newIsEmpty;
    }
    public static void checkEmpty() {
        if (deck.size() <= 0) {
            setIsEmpty(true);
        }
    }
    public static int getSize() {
        return deck.size();
    }

    @Override
    public String toString() {
        return deck.size() + " cards left.";
    }
}
