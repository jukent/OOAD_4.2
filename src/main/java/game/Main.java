package game;

public class Main {


    /**
     * @param args String[]
     *
     * This is the main method that runs the game.
     */
    public static void main(final String[] args) {
        run1Game();
        //runNGames(30);
    }


    /**
     * This method runs one game, verbose.
     */
    public static void run1Game() {
        GameEngine game1 =
            new GameEngine("ShowAll"); // OneScreen,ShowAll,ShowNone
        game1.runGame();
    }


    /**
     * @param n int
     *
     * This method runs 'n' games.
     */
    public static void runNGames(final int n) {
        for (int i = 0; i < n; i++) {
            int num = i + 1;
            System.out.println("Game Number: " + num);
            GameEngine gameN = new GameEngine("ShowNone");
            gameN.runGame();
        }
    }
}
