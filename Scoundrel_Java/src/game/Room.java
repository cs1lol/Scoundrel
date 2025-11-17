package game;

import java.util.ArrayList;
import cards.Card;
import cards.Deck;

public class Room {
    private static ArrayList<Card> room = new ArrayList<Card>();
    private static boolean isEscapable = false;
    private static boolean isEmpty = false;

    public static void createRoom() {
        isEmpty = false;
        room.clear();
        int deckSize = Deck.getSize();
        if (deckSize < 4) {
            for (int i = 0; i < deckSize; i++) {
                room.add(Deck.pickCard());
            }
        }
        for (int i = 0; i < 4; i++) {
            room.add(Deck.pickCard());
        }
        isEscapable = checkRoom();
    }

    public static void createFirstRoom() {
        room.clear();
        room.add(Deck.pickCard(5, "MONSTER", true));
        room.add(Deck.pickCard(7, "MONSTER", false));
        room.add(Deck.pickCard(5, "POTION", true));
        room.add(Deck.pickCard(5, "WEAPON", true));
        isEscapable = checkRoom();
    }
    // Adds card to bottom of deck when fleeing
    public static void escapeRoom() {
        for (Card card : room) {
            Deck.addToDeck(card);
        }
        createRoom();
    }
    // Checks if a room is clear of monsters
    public static boolean checkRoom() {
        if (room.size() == 0) {
            isEmpty = true;
        }
        for (Card card : room) {
            if ("MONSTER".equals(card.getType())) {
                isEscapable = false;
                return isEscapable;
            }
        }
        isEscapable = true;
        return isEscapable;
    }

    // Gets a card in the room at the specified index
    public static Card selectCardFromRoom(int index) {
        return room.get(index-1);
    }
    // Removes a card only if the player succeeded
    public static void removeCardFromRoom(boolean playerSucceeded, Card card) {
        if (playerSucceeded) {
            room.remove(card);
        }
    }

    public static int[] getValidMoves(boolean playerCanFlee) {
        int[] validMoves = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < room.size(); i++) {
            if (room.get(i) != null) {
                validMoves[i] = 1;
            }
        }
        if (getEscapable()) {
            validMoves[4] = 1;
        }
        if (playerCanFlee) {
            validMoves[5] = 1;
        }
        return validMoves;
    }

    public static String printRoom() {
        String output = "You enter a room with the following:\n";
        for (Card card : room) {
            output = output + card.toString() + "\n";
        }
        if (!isEscapable) {
            output = output + "You cannot leave this room without fleeing.\n";
        } else {
            output = output + "You can leave this room without fleeing.\n";
        }

        return output;
    }

    public static int getSize() {
        return room.size();
    }
    public static boolean getIsEmpty() {
        return isEmpty;
    }

    public static boolean getEscapable() {
        return isEscapable;
    }

    @Override
    public String toString() {
        String output = "You enter a room with the following:\n";
        for (Card card : room) {
            output = output + card.toString() + "\n";
        }
        return output;
    }
}
