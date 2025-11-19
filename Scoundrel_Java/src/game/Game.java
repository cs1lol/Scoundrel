package game;

import java.util.Scanner;
import java.util.InputMismatchException;

import cards.Deck;
import cards.Card;

public class Game {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean gameRunning;
    private static Player player;

    private static void gameLoop() {
        // Display player and Deck status
        System.out.println("\n\n");
        System.out.println(player);
        System.out.println("There are " + Deck.getSize() + " cards left in this dungeon.\n");
        // Display menu
        System.out.println(Room.printRoom());
        int[] validMoves = Room.getValidMoves(player.isCanFlee());
        String validMessage = displayValidMoves(validMoves);
        int output = promptForInt(validMessage, validMoves);
        // Validate input and perform action
        performAction(output);
        // Check Room
        Room.checkRoom();
        // Check status
        player.checkPlayer();
        Deck.checkEmpty();

        if (!player.isAlive() || Deck.getIsEmpty()) {
            gameRunning = false;
        }
    }

    public static void gameStart() {
        gameRunning = true;
        player = new Player();
        Deck.createDeck();
        Room.createFirstRoom();
        while (gameRunning) {
            gameLoop();
        }
    }

    public static boolean gameEnd() {
        if (!player.isAlive()) {
            System.out.println("You died. There were " + Deck.getSize() + " cards left in the dungeon.");
        } else {
            System.out.println("You won. Congratulations!");
        }
        System.out.println("Would you like to play again? (Y/N)");
        String userInput = scanner.nextLine();
        if ("y".equals(userInput.toLowerCase())) {
            gameStart();
            return true;
        } else {
            scanner.close();
            return false;
        }
    }
    
    // Room methods
    private static String displayValidMoves(int[] validMoves) {
        String output = "Your valid moves are: [ ";
        for(int i = 0; i < validMoves.length; i++) {
            if (validMoves[i] == 1) {
                output = output + (i+1) + " ";
            }
        }
        output = output + "]";
        return output;
    }
    private static boolean fleeRoom() {
        if ((!Room.getEscapable()) && (player.isCanFlee())) {
            Room.escapeRoom();
            player.setCanFlee(false);
            return true;
        }
        return false;
    }
    private static boolean leaveRoom() {
        if (Room.getEscapable()) {
            Room.createRoom();
            return true;
        }
        return false;
    }
    // Input processing and decision making
    private static boolean performAction(int userInput) {
        boolean playerSucceeded = false;
        switch (userInput) {
            case 5:
                playerSucceeded = leaveRoom();
                break;
            case 6:
                playerSucceeded = fleeRoom();
                break;
            default:
                Card targetCard = Room.selectCardFromRoom(userInput);
                playerSucceeded = player.playCard(targetCard);
                Room.removeCardFromRoom(playerSucceeded, targetCard);
                break;
        }
        return playerSucceeded;
    }
    // Prompts for an integer and ensures the user inputs an integer
    private static int promptForInt(String message, int[] validMoves) {
        int output = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.println(message);
                output = scanner.nextInt();
                if (validMoves[output - 1] != 1) {
                    System.out.println("Invalid Input");
                    continue;
                } else {
                    isValidInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.next();
            }
        }
        return output;
    }
}
