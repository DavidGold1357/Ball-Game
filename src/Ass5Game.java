//David Goldstein 331010835
import Game.Game;
public class Ass5Game {
    /**
     * The main method to start the game.
     * It creates an instance of Game, initializes it, and runs the game loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initGame();
        game.runGame();
    }
}
