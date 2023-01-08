package gremlins;

//import packages
import processing.core.PApplet;
import processing.core.PImage;
import java.lang.Math.*;
import java.util.ArrayList;

public class Gremlins {

    // initialise variables
    private PImage sprite;
    public int x;
    public int y;
    private int speedX = 1;
    private int speedY = 1;
    private Boolean[][] wallList;
    private Boolean[][] wallListTwo;
    public boolean moveable = true;
    public boolean noRepeat = false;
    public boolean nothingThereUp;
    public boolean nothingThereDown;
    public boolean nothingThereRight;
    public boolean nothingThereLeft;
    public boolean nothingThere;
    public boolean changeLevelGremlin = false;
    public boolean gremlinUp = false;
    public boolean gremlinDown = false;
    public boolean gremlinLeft = false;
    public boolean gremlinRight = false;

    // constructor for gremlins
    public Gremlins(PImage sprite, int x, int y, Boolean[][] wallList, Boolean[][] wallListTwo) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.wallList = wallList;
        this.wallListTwo = wallListTwo;
    }

    /**
     * Handling logic
     */
    public void tick() {
        int num = (int) Math.floor(Math.random() * 5);
        checkCollision(num, wallList);
        movingability(num);

    }

    /**
     * Handling graphics
     */
    public void draw(PApplet app) {
        app.image(this.sprite, this.y, this.x);

    }

    /**
     * Changes x and y value of gremlin to make movement
     */
    public void move(int dx, int dy) {
        this.x += dx * speedX;
        this.y += dy * speedY;
    }

    /**
     * Move the gremlins after check of obstacles
     */
    public void movingability(int rnum) {
        if (changeLevelGremlin == true) {
            if (rnum == 1 && nothingThereUp == true) {
                // while (nothingThereUp == true) {
                gremlinUp = true;
                gremlinDown = false;
                gremlinLeft = false;
                gremlinRight = false;
                move(1, 0);
                checkCollision(1, wallListTwo);
                // }
            } else if (rnum == 2 && nothingThereDown == true) {
                // while (nothingThereDown == true) {
                gremlinUp = false;
                gremlinDown = true;
                gremlinLeft = false;
                gremlinRight = false;
                move(-1, 0);
                checkCollision(2, wallListTwo);
                // }
            } else if (rnum == 3 && nothingThereLeft == true) {
                // while (nothingThereLeft == true) {
                gremlinUp = false;
                gremlinDown = false;
                gremlinLeft = true;
                gremlinRight = false;
                move(0, -1);
                checkCollision(3, wallListTwo);
                // }
            } else if (rnum == 4 && nothingThereRight == true) {
                // while (nothingThereRight == true) {
                gremlinUp = false;
                gremlinDown = false;
                gremlinLeft = false;
                gremlinRight = true;
                move(0, 1);
                checkCollision(4, wallListTwo);
                // }
            }

        } else {
            if (rnum == 1 && nothingThereUp == true) {
                // while (nothingThereUp == true) {
                gremlinUp = true;
                gremlinDown = false;
                gremlinLeft = false;
                gremlinRight = false;
                move(1, 0);
                checkCollision(1, wallList);
                // }
            } else if (rnum == 2 && nothingThereDown == true) {
                // while (nothingThereDown == true) {
                gremlinUp = false;
                gremlinDown = true;
                gremlinLeft = false;
                gremlinRight = false;
                move(-1, 0);
                checkCollision(2, wallList);
                // }
            } else if (rnum == 3 && nothingThereLeft == true) {
                // while (nothingThereLeft == true) {
                gremlinUp = false;
                gremlinDown = false;
                gremlinLeft = true;
                gremlinRight = false;
                move(0, -1);
                checkCollision(3, wallList);
                // }
            } else if (rnum == 4 && nothingThereRight == true) {
                // while (nothingThereRight == true) {
                gremlinUp = false;
                gremlinDown = false;
                gremlinLeft = false;
                gremlinRight = true;
                move(0, 1);
                checkCollision(4, wallList);
                // }
            }
        }

    }

    /**
     * Based on random number generated, move gremlin in a random direction
     * Checks if the blocks around are walls and then can only move if empty
     */
    public void checkCollision(int rnum, Boolean[][] wallList) {
        if (rnum == 1 && this.x % 20 == 0) {
            int locationX = (this.x + 20) / 20;
            int locationY = this.y / 20;
            if (wallList[locationX][locationY] == null) {
                nothingThereUp = true;
                nothingThere = true;
            } else {
                nothingThereUp = false;
                nothingThere = false;
            }
        } else if (rnum == 2 && this.x % 20 == 0) {
            int locationX = (this.x - 20) / 20;
            int locationY = this.y / 20;
            if (wallList[locationX][locationY] == null) {
                nothingThereDown = true;
                nothingThere = true;
            } else {
                nothingThereDown = false;
                nothingThere = false;
            }
        } else if (rnum == 3 && this.y % 20 == 0) {
            int locationX = this.x / 20;
            int locationY = (this.y - 20) / 20;
            if (wallList[locationX][locationY] == null) {
                nothingThereLeft = true;
                nothingThere = true;
            } else {
                nothingThereLeft = false;
                nothingThere = false;
            }
        } else if (rnum == 4 && this.y % 20 == 0) {
            int locationX = this.x / 20;
            int locationY = (this.y + 20) / 20;
            if (wallList[locationX][locationY] == null) {
                nothingThereRight = true;
                nothingThere = true;
            } else {
                nothingThereRight = false;
                nothingThere = false;
            }
        }

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
