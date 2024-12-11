//David Goldstein 331010835
package Sprites;
import Game.Game;
import Mech.Collidable;
import Mech.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Sprites.Paddle class represents a paddle in a game, controlled by the player using the keyboard.
 * The paddle can move left and right, and it reacts to collisions with a ball.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboardSensor;
    private Rectangle paddleShape;
    private Color paddleColor;
    private int paddleSpeed;
    private int leftLimit;
    private int rightLimit;

    /**
     * Constructs a Sprites.Paddle object with the specified parameters.
     *
     * @param keyboardSensor the KeyboardSensor to read user input
     * @param shape the rectangle representing the paddle
     * @param color the color of the paddle
     * @param speed the speed at which the paddle moves
     * @param leftBoundary the left boundary of the paddle's movement
     * @param rightBoundary the right boundary of the paddle's movement
     */
    public Paddle(KeyboardSensor keyboardSensor, Rectangle shape, Color color, int speed, int leftBoundary, int rightBoundary) {
        this.keyboardSensor = keyboardSensor;
        this.paddleShape = new Rectangle(shape.getUpperLeftPoint(), shape.getWidthOfRectangle(), shape.getHeightOfRectangle());
        this.paddleColor = color;
        this.paddleSpeed = speed;
        this.leftLimit = leftBoundary;
        this.rightLimit = rightBoundary;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double newX = this.paddleShape.getUpperLeftPoint().getX() - paddleSpeed;
        if (newX < leftLimit) {
            newX = rightLimit - this.paddleShape.getWidthOfRectangle();
        }
        this.paddleShape = new Rectangle(new Point(newX, this.paddleShape.getUpperLeftPoint().getY()),
                this.paddleShape.getWidthOfRectangle(), this.paddleShape.getHeightOfRectangle());
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newX = this.paddleShape.getUpperLeftPoint().getX() + paddleSpeed;
        if (newX > rightLimit - this.paddleShape.getWidthOfRectangle()) {
            newX = leftLimit;
        }
        this.paddleShape = new Rectangle(new Point(newX, this.paddleShape.getUpperLeftPoint().getY()),
                this.paddleShape.getWidthOfRectangle(), this.paddleShape.getHeightOfRectangle());
    }

    /**
     * Updates the paddle's state based on user input.
     * Checks for left and right movement based on keyboard input.
     */
    public void timePassed() {
        if (keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the paddle on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.paddleColor);
        surface.fillRectangle((int) this.paddleShape.getUpperLeftPoint().getX(), (int) this.paddleShape.getUpperLeftPoint().getY(),
                (int) this.paddleShape.getWidthOfRectangle(), (int) this.paddleShape.getHeightOfRectangle());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.paddleShape.getUpperLeftPoint().getX(), (int) this.paddleShape.getUpperLeftPoint().getY(),
                (int) this.paddleShape.getWidthOfRectangle(), (int) this.paddleShape.getHeightOfRectangle());
    }

    /**
     * Returns the rectangle representing the paddle.
     *
     * @return the collision rectangle representing the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    /**
     * Handles the collision with the ball, changing its velocity based on the collision point.
     *
     * @param hitter the Sprites.Ball object that hit the paddle
     * @param collisionPoint the point of collision between the ball and the paddle
     * @param currentVelocity the current velocity of the ball before the collision
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = this.paddleShape.getWidthOfRectangle() / 5;
        double hitX = collisionPoint.getX();
        double paddleX = this.paddleShape.getUpperLeftPoint().getX();
        int region = (int) ((hitX - paddleX) / regionWidth) + 1;

        switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            case 2:
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            case 3:
                return new Velocity(currentVelocity.getDeltaX(), -currentVelocity.getDeltaY());
            case 4:
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            case 5:
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            default:
                return null;
        }
    }

    /**
     * Adds this paddle to the game by adding it to both the sprite and collidable collections.
     *
     * @param game the game object to add the paddle to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
}
