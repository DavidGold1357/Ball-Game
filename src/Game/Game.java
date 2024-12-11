//David Goldstein 331010835
package Game;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreIndicator;
import Mech.Collidable;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Sprites.ScoreTrackingListener;
import Sprites.SpriteCollection;
import Sprites.Sprite;
import Geometry.Point;
import Geometry.Rectangle;
import Miscellaneous.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;
/**
 * The Game class manages the game environment, including sprites and collidables,
 * and handles the main game loop.
 */
public class Game {
    private SpriteCollection spriteCollection;
    private GameEnvironment gameEnvironment;
    private GUI gameGUI;
    private Sleeper gameSleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter gameScore;

    /**
     * Constructs a new Game object.
     */
    public Game() {
        this.spriteCollection = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.gameGUI = new GUI("Game", 800, 600);
        this.gameSleeper = new Sleeper();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.gameScore = new Counter();
    }

    /**
     * Returns the game environment.
     *
     * @return the game environment
     */
    protected GameEnvironment getGameEnvironment() {

        return this.gameEnvironment;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param collidable the collidable object to add
     */
    public void addCollidable(Collidable collidable) {

        this.gameEnvironment.addCollidable(collidable);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param sprite the sprite to add
     */
    public void addSprite(Sprite sprite) {
        this.spriteCollection.addSprite(sprite);
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param collidable the collidable object to remove
     */
    public void removeCollidable(Collidable collidable) {

        gameEnvironment.removeCollidable(collidable);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param sprite the sprite to remove
     */
    public void removeSprite(Sprite sprite) {

        spriteCollection.removeSprite(sprite);
    }


    /**
     * Runs the game loop, updating and drawing all sprites.
     */
    public void runGame() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface drawSurface = gameGUI.getDrawSurface();
            this.spriteCollection.drawAllOn(drawSurface);
            gameGUI.show(drawSurface);
            this.spriteCollection.notifyAllTimePassed();

            if (this.blockCounter.getValue() == 0) {
                this.gameScore.increase(100);
                drawSurface = gameGUI.getDrawSurface();
                this.spriteCollection.drawAllOn(drawSurface);
                gameGUI.show(drawSurface);
                gameSleeper.sleepFor(2000); // Sleep for 2 seconds to allow the player to see the final score
                gameGUI.close();
                return;
            }

            if (this.ballCounter.getValue() == 0) {
                gameGUI.close();
                return;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                gameSleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * Creates blocks and adds them to the game.
     *
     * @return an array of colors used for the blocks
     */
    protected Color[] createGameBlocks() {
        double blockWidth = 60;
        double blockHeight = 20;
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.gameScore);
        Color[] blockColors = new Color[6];
        Random randomGenerator = new Random();
        for (int row = 0; row < 6; row++) {
            boolean colorExists;
            Color blockColor;

// Generate a unique color for the current row
            if (row == 0) {
                blockColor = new Color(randomGenerator.nextInt(256), randomGenerator.nextInt(256), randomGenerator.nextInt(256));
                blockColors[0] = blockColor;
            } else {
                do {
                    colorExists = false;
                    blockColor = new Color(randomGenerator.nextInt(256), randomGenerator.nextInt(256), randomGenerator.nextInt(256));
                    for (int i = 0; i < row; i++) {
                        if (blockColor.equals(blockColors[i])) {
                            colorExists = true;
                            break;
                        }
                    }
                } while (colorExists);
                blockColors[row] = blockColor;
            }

// Create blocks for the current row
            for (int col = 12; col > row; col--) {
                Point upperLeftPoint = new Point(col * (int) blockWidth + 10, row * (int) blockHeight + 70);
                Rectangle blockRectangle = new Rectangle(upperLeftPoint, blockWidth, blockHeight);
                Block gameBlock = new Block(blockRectangle, blockColor);
                gameBlock.addHitListener(scoreTracker);
                gameBlock.addHitListener(blockRemover);
                blockCounter.increase(1);
                gameBlock.addToGame(this);
            }
        }
        return blockColors;
    }

    /**
     * Initializes the game by creating blocks, balls, and other game objects,
     * and adding them to the game.
     */
    public void initGame() {
        Point ballStartPoint = new Point(300, 400);
        int ballSize = 4;
        Color[] blockColors = createGameBlocks();

        Random random = new Random();

        // Create and add a ball
        Ball gameBall = new Ball(ballStartPoint, ballSize, blockColors[random.nextInt(6)], this.gameEnvironment);
        gameBall.setVelocity(-5, -5);
        gameBall.addToGame(this);
        this.ballCounter.increase(1);

        // Add second ball
        Ball gameBall2 = new Ball(new Point(400, 300), 4, blockColors[random.nextInt(6)], this.getGameEnvironment());
        gameBall2.setVelocity(-5, -5);
        gameBall2.addToGame(this);
        this.ballCounter.increase(1);

        // Add third ball
        Ball gameBall3 = new Ball(new Point(400, 300), 4, blockColors[random.nextInt(6)], this.getGameEnvironment());
        gameBall3.setVelocity(-5, -5);
        gameBall3.addToGame(this);
        this.ballCounter.increase(1);

        final Block topBorder = new Block(new Rectangle(new Point(0, 30), 800, 10), Color.BLACK);
        final Block leftBorder = new Block(new Rectangle(new Point(0, 30), 10, 600), Color.BLACK);
        final Block rightBorder = new Block(new Rectangle(new Point(790, 30), 10, 600), Color.BLACK);

        final Block deathRegion = new Block(new Rectangle(new Point(0, 590), 800, 10), Color.BLACK);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);

        topBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);

        Paddle gamePaddle;
        Rectangle paddleRectangle;
        final int paddleWidth = 100;
        final int paddleHeight = 20;
        final int paddleSpeed = 7;

        Point paddlePosition = new Point(350, 570); // Adjusted y-coordinate to 570
        paddleRectangle = new Rectangle(paddlePosition, paddleWidth, paddleHeight);
        gamePaddle = new Paddle(gameGUI.getKeyboardSensor(), paddleRectangle, Color.WHITE, paddleSpeed, 10, 790);
        gamePaddle.addToGame(this);

        // Add score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.gameScore);
        this.addSprite(scoreIndicator);
    }
}
