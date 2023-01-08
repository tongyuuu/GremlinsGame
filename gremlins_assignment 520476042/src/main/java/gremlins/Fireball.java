package gremlins;

//import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Fireball implements Features {

    // initialise variables
    public int x;
    public int y;
    private int dx;
    private int dy;
    public int speedX = 4;
    public int speedY = 4;
    public boolean fired = false;
    private ArrayList<Brickwall> brickWallWiz;
    public boolean fireBallHit = false;
    private PImage sprite;

    // constructor for fireball
    public Fireball(PImage sprite, ArrayList<Brickwall> brickWallWiz) {
        this.sprite = sprite;
        this.brickWallWiz = brickWallWiz;
    }

    /**
     * Move the fireball
     */
    public void move() {
        this.x += this.dx * speedX;
        this.y += this.dy * speedY;
    }

    /**
     * Method to check if fireball is fired
     */
    public void pressed(boolean space, boolean down, boolean left, boolean right, boolean up) {
        // check for input to shoot fireball
        if (space) {
            fired = true;
        } else {
            fired = false;
        }
        // check direction of wizard and then shoot fireball accordingly
        if (down) {
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
    }

    /**
     * Handling logic of fireball
     */
    public void tick() {
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

    /**
     * Check collision for Brickwall
     */
    public boolean checkCollisionBrick() {
        for (Brickwall brickwall : brickWallWiz) {
            int gremleft = brickwall.getx();
            int gremright = brickwall.getx() + brickwall.getWidth();
            int gremup = brickwall.gety();
            int gremdown = brickwall.gety() + brickwall.getHeight();
            int fireRight = this.x + this.sprite.width;
            int fireBottom = this.y + this.sprite.height;
            if (fireRight > gremleft && this.x < gremright && fireBottom > gremup && this.y < gremdown) {
                return true;
            }
        }
        return false;
    }

}
