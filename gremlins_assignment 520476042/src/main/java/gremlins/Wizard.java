package gremlins;

//import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class Wizard {
    // initialise variables
    private PImage spriteRight;
    private PImage spriteLeft;
    private PImage spriteUp;
    private PImage spriteDown;
    private PImage spriteDefault;

    public int x;
    public int y;
    public int lives = 3;
    public int hitDoor = 0;
    public boolean winGame = false;

    private int dx;
    private int dy;
    private double speedX = 2.0;
    private double speedY = 2.0;
    private ArrayList<Gremlins> gremlinListWiz;
    private Boolean[][] wallListWiz;
    private Boolean[][] wallListWizTwo;
    private Teleport telep;
    private Exit exit;
    private Powerup powerup;
    public boolean changeLevel;
    private boolean firstTime = false;
    private boolean keyDown = false;

    public boolean wizDown;
    public boolean wizUp;
    public boolean wizRight;
    public boolean wizLeft;
    public boolean powerthere = true;

    // constructor for wizard
    public Wizard(PImage spriteRight, PImage spriteLeft, PImage spriteUp, PImage spriteDown, PImage spriteDefault,
            int x, int y,
            ArrayList<Gremlins> gremlinListwiz, Boolean[][] walllistwiz, Boolean[][] wallListWizTwo, Teleport telep,
            Exit exit, Powerup powerup, boolean changelevel) {
        this.spriteDefault = spriteDefault;
        this.spriteRight = spriteRight;
        this.spriteLeft = spriteLeft;
        this.spriteDown = spriteDown;
        this.spriteUp = spriteUp;
        this.telep = telep;
        this.exit = exit;
        this.powerup = powerup;
        this.x = x;
        this.y = y;
        this.gremlinListWiz = gremlinListwiz;
        this.wallListWiz = walllistwiz;
        this.changeLevel = changelevel;

    }

    /**
     * Handles logic for the wizard
     */
    public void tick() {
        // checks if wizard is in middle of tile and if not, move it to the middle of
        // the tile
        if (keyDown == false) {
            if (this.x % 20 != 0) {
                if (this.x % 20 < 10) {
                    this.x -= 1;
                } else {
                    this.x += 1;
                }
            }

            if (this.y % 20 != 0) {
                if (this.y % 20 < 10) {
                    this.y -= 1;
                } else {
                    this.y += 1;
                }
            }
        }

        if (changeLevel == true && firstTime == true) {
            this.x = 20;
            this.y = 40;
            firstTime = false;
        }
        if (checkCollisionGremlin() == true) {
            lives -= 1;
            this.x = 20;
            this.y = 40;
        }

        if (checkCollisionTeleport() == true) {
            teleport();
        }

        if (checkCollisionDoor() == true) {
            changeLevel = true;
            firstTime = true;
            hitDoor++;
            if (hitDoor == 2) {
                winGame = true;
            }
        }
        if (checkCollisionPowerUp() == true) {
            powerthere = false;
            speedX = 4.0;
            speedY = 4.0;
        }

        move();

    }

    /**
     * Handles the graphics of the wizard
     */
    public void draw(PApplet app) {
        app.image(this.spriteRight, this.y, this.x);

    }

    /**
     * Move the wizard x and y position
     */
    public void move() {
        this.x += this.dx * speedX;
        this.y += this.dy * speedY;

    }

    /**
     * Checks to see if user has made input to move the wizard and whether the
     * wizard can move
     */
    public void pressed(boolean left, boolean right, boolean up, boolean down) {
        keyDown = true;
        if (changeLevel) {
            if (left) {
                wizLeft = true;
                wizRight = false;
                wizDown = false;
                wizUp = false;
                this.spriteRight = spriteLeft;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx][locationy - 1] == null) {
                    this.dy = -1;
                }
            } else if (right) {
                wizRight = true;
                wizDown = false;
                wizLeft = false;
                wizUp = false;
                this.spriteRight = spriteDefault;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx][locationy + 1] == null) {
                    this.dy = 1;
                }
            } else if (up) {
                wizRight = false;
                wizDown = false;
                wizLeft = false;
                wizUp = true;
                this.spriteRight = spriteUp;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx - 1][locationy] == null) {
                    this.dx = -1;
                }
            } else if (down) {
                wizRight = false;
                wizDown = true;
                wizLeft = false;
                wizUp = false;
                this.spriteRight = spriteDown;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx + 1][locationy] == null) {
                    this.dx = 1;
                }
            }

        } else {
            if (left) {
                wizLeft = true;
                wizRight = false;
                wizDown = false;
                wizUp = false;
                this.spriteRight = spriteLeft;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx][locationy - 1] == null) {
                    this.dy = -1;
                }
            } else if (right) {
                wizRight = true;
                wizDown = false;
                wizLeft = false;
                wizUp = false;
                this.spriteRight = spriteDefault;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx][locationy + 1] == null) {
                    this.dy = 1;
                }
            } else if (up) {
                wizRight = false;
                wizDown = false;
                wizLeft = false;
                wizUp = true;
                this.spriteRight = spriteUp;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx - 1][locationy] == null) {
                    this.dx = -1;
                }
            } else if (down) {
                wizRight = false;
                wizDown = true;
                wizLeft = false;
                wizUp = false;
                this.spriteRight = spriteDown;
                int locationy = this.y / 20;
                int locationx = this.x / 20;
                if (wallListWiz[locationx + 1][locationy] == null) {
                    this.dx = 1;
                }
            }
        }
    }

    /**
     * Stops movement of wizard once key is released
     */
    public void released(boolean left, boolean right, boolean up, boolean down) {
        keyDown = false;
        if (left) {
            this.dy = 0;
        } else if (right) {
            this.dy = 0;
        } else if (up) {
            this.dx = 0;
        } else if (down) {
            this.dx = 0;
        }
    }

    /**
     * Check collision for Gremlins
     */
    public boolean checkCollisionGremlin() {
        for (Gremlins gremlin : gremlinListWiz) {
            int gremLeft = gremlin.getx();
            int gremRight = gremlin.getx() + gremlin.getWidth();
            int gremUp = gremlin.gety();
            int gremDown = gremlin.gety() + gremlin.getHeight();
            int wizRight = this.x + this.spriteRight.width;
            int wizBottom = this.y + this.spriteRight.height;
            if (wizRight > gremLeft && this.x < gremRight && wizBottom > gremUp && this.y < gremDown) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check collision for teleportation door
     */
    public boolean checkCollisionTeleport() {
        int teleLeft = this.telep.getx();
        int teleRight = this.telep.getx() + this.telep.getWidth();
        int teleUp = this.telep.gety();
        int teleDown = this.telep.gety() + this.telep.getHeight();
        int wizRight = this.x + this.spriteDefault.width;
        int wizBottom = this.y + this.spriteDefault.height;
        if (wizRight > teleLeft && this.x < teleRight && wizBottom > teleUp && this.y < teleDown) {
            return true;
        }

        return false;

    }

    /**
     * Check collision for door
     */
    public boolean checkCollisionDoor() {
        int exitLeft = this.exit.getx();
        int exitRight = this.exit.getx() + this.exit.getWidth();
        int exitUp = this.exit.gety();
        int exitDown = this.exit.gety() + this.exit.getHeight();
        int wizRight = this.x + this.spriteDefault.width;
        int wizBottom = this.y + this.spriteDefault.height;
        if (wizRight > exitLeft && this.x < exitRight && wizBottom > exitUp && this.y < exitDown) {
            return true;
        }
        return false;
    }

    /**
     * Check collision for the door
     */
    public boolean checkCollisionPowerUp() {
        int powerLeft = this.powerup.getx();
        int powerRight = this.powerup.getx() + this.powerup.getWidth();
        int powerUp = this.powerup.gety();
        int powerDown = this.powerup.gety() + this.powerup.getHeight();
        int wizRight = this.x + this.spriteDefault.width;
        int wizBottom = this.y + this.spriteDefault.height;
        if (wizRight > powerLeft && this.x < powerRight && wizBottom > powerUp && this.y < powerDown) {
            return true;
        }

        return false;

    }

    /**
     * Move wizard once teleport is hit
     */
    public void teleport() {
        this.x = 400;
        this.y = 340;

    }

}
