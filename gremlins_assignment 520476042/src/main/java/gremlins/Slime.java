package gremlins;

//import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Slime implements Features {

    // initialise variables
    public int x;
    public int y;
    private int dx;
    private int dy;
    public boolean up;
    public boolean down;
    public boolean right;
    public boolean left;
    public int speedX = 4;
    public int speedY = 4;
    private PImage sprite;

    // constructor for slime
    public Slime(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Move the slime
     */
    public void move() {
        this.x += this.dx * speedX;
        this.y += this.dy * speedY;
    }

    /**
     * Handling logic to move the slime after checking direction
     */
    public void tick() {
        if (left) {
            this.dx = 0;
            this.dy = 1;
        } else if (up) {
            this.dx = 0;
            this.dy = -1;
        } else if (right) {
            this.dy = 0;
            this.dx = 1;
        } else if (left) {
            this.dy = 0;
            this.dx = -1;
        }
        move();

    }

    /**
     * Handling graphics
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    /**
     * Getter for x coordinate
     */
    public int getx() {
        return this.x;
    }

    /**
     * Getter for y coordinate
     */
    public int gety() {
        return this.y;
    }

    /**
     * Getter for width of sprite
     */
    public int getWidth() {
        return this.sprite.width;
    }

    /**
     * Getter for height of sprite
     */
    public int getHeight() {
        return this.sprite.height;
    }

}
