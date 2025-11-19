import game.Game;

public class Scoundrel {
    public static void main(String[] args) {
        System.out.println("Welcome to Scoundrel V1\n");
        boolean isPlaying = true;
        while (isPlaying) {
            Game.gameStart();
            isPlaying = Game.gameEnd();
        }
    }
}