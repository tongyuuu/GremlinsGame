package gremlins;

//import packages
import processing.core.PApplet;
import processing.core.PImage;

public class Teleport implements Features {

    // initialise variables
    private int x;
    private int y;
    private PImage sprite;

    // constructor for teleport
    public Teleport(PImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    /**
     * Handling graphics
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.y, this.x);

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
