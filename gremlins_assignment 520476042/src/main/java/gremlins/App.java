package gremlins;

//import packages
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.*;
import processing.event.KeyEvent;
import processing.event.*;
import java.util.*;
import javax.swing.plaf.TreeUI;
import java.io.*;

public class App extends PApplet {

    // Setup Variables
    public static final int WIDTH = 720;
    public static final int HEIGHT = 720;
    public static final int SPRITESIZE = 20;
    public static final int BOTTOMBAR = 60;
    public static final int FPS = 60;
    public static final Random randomGenerator = new Random();
    public String configPath;

    public boolean end = false;
    private PFont lives;
    private PFont endGame;
    public int countFire = 0;
    public int countSlime = 0;

    private Wizard wizard;
    private Fireball fireball;
    private Slime slime;
    private PImage gremlin;
    private PImage brickWall;
    private PImage stoneWall;
    private PImage wizardLives;
    private Teleport tele;
    private Powerup powerup;
    private Exit exit;

    public ArrayList<Gremlins> gremlinList = new ArrayList<Gremlins>();
    public ArrayList<Stonewall> stoneList = new ArrayList<Stonewall>();
    public ArrayList<Brickwall> brickList = new ArrayList<Brickwall>();
    public ArrayList<Gremlins> gremlinListTwo = new ArrayList<Gremlins>();
    public ArrayList<Stonewall> stoneListTwo = new ArrayList<Stonewall>();
    public ArrayList<Brickwall> brickListTwo = new ArrayList<Brickwall>();
    public Boolean[][] wallList = new Boolean[33][36];
    public Boolean[][] wallListTwo = new Boolean[33][36];
    public String[][] levelOneArray = new String[33][36];
    public String[][] levelTwoArray = new String[33][36];
    public File levelFileOne = new File("level1.txt");
    public File levelFileTwo = new File("level2.txt");

    // constructor for app
    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the
     * player, enemies and map elements.
     */
    public void setup() {
        frameRate(FPS);

        this.lives = this.createFont("Arial", 20, true);
        this.endGame = this.createFont("Arial", 20, true);

        this.stoneWall = this.loadImage("src/main/resources/gremlins/stonewall.png");
        this.brickWall = this.loadImage("src/main/resources/gremlins/brickwall.png");
        this.exit = new Exit(this.loadImage("src/main/resources/gremlins/Door.png"), 620, 620);
        this.tele = new Teleport(this.loadImage("src/main/resources/gremlins/energyorbteleport.png"), 160, 220);
        this.powerup = new Powerup(this.loadImage("src/main/resources/gremlins/Powerup.png"), 20, 280);
        this.wizard = new Wizard(this.loadImage("src/main/resources/gremlins/wizard1.png"),
                this.loadImage("src/main/resources/gremlins/wizard0.png"),
                this.loadImage("src/main/resources/gremlins/wizard2.png"),
                this.loadImage("src/main/resources/gremlins/wizard3.png"),
                this.loadImage("src/main/resources/gremlins/wizard1.png"), 20, 40, gremlinList,
                wallList, wallListTwo, tele, exit, powerup, false);
        this.gremlin = loadImage("src/main/resources/gremlins/gremlin.png");
        this.wizardLives = loadImage("src/main/resources/gremlins/wizard1.png");
        this.fireball = new Fireball(loadImage("src/main/resources/gremlins/fireball.png"), brickList);
        this.slime = new Slime(loadImage("src/main/resources/gremlins/slime.png"));

        JSONObject conf = loadJSONObject(new File(this.configPath));
        if (wizard.changeLevel == true) {
            loadLevelTwo();
        } else {
            loadLevelOne();
        }

    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        fireball.pressed((key == 32), wizard.wizDown, wizard.wizLeft, wizard.wizRight, wizard.wizUp);
        wizard.pressed((key == 37), (key == 39), (key == 38), (key == 40));

    }

    /**
     * Receive key released signal from the keyboard.
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        wizard.released((key == 37), (key == 39), (key == 38), (key == 40));

    }

    /**
     * Draw all elements in the game by current frame.
     */

    public void draw() {
        // check wizard lives and display things accodingly
        if (wizard.lives <= 0) {
            background(194, 152, 111);
            textFont(endGame, 40);
            fill(255);
            text("Game Over", 250, 350);
        } else if (wizard.winGame == true) {
            background(194, 152, 111);
            textFont(endGame, 40);
            fill(255);
            text("You Win!", 270, 3600);
        } else {
            // load level two and subsequent items
            if (wizard.changeLevel == true) {
                background(194, 152, 111);
                loadLevelTwo();
                for (Stonewall s : stoneListTwo) {
                    s.draw(this);
                }

                for (Brickwall b : brickListTwo) {
                    b.draw(this);
                }

                // create the gremlins from the list and draw them
                Gremlins gremlin1 = gremlinListTwo.get(0);
                Gremlins gremlin2 = gremlinListTwo.get(1);
                Gremlins gremlin3 = gremlinListTwo.get(2);
                Gremlins gremlin4 = gremlinListTwo.get(3);

                gremlin1.draw(this);
                gremlin2.draw(this);
                gremlin3.draw(this);
                gremlin4.draw(this);

                gremlin1.tick();
                gremlin2.tick();
                gremlin3.tick();
                gremlin4.tick();

            } else {
                // load level one and the items that exist
                background(194, 152, 111);
                textFont(lives, 18);
                fill(255);
                text("Lives: ", 10, 695);
                text("Level: 1/2 ", 250, 695);
                if (wizard.lives == 3) {
                    image(wizardLives, 60, 680);
                    image(wizardLives, 80, 680);
                    image(wizardLives, 100, 680);
                } else if (wizard.lives == 2) {
                    image(wizardLives, 60, 680);
                    image(wizardLives, 80, 680);
                } else if (wizard.lives == 1) {
                    image(wizardLives, 60, 680);
                }
                for (Stonewall s : stoneList) {
                    s.draw(this);
                }

                for (Brickwall b : brickList) {
                    b.draw(this);
                }

                // create the gremlins from the list and draw them calling tick
                Gremlins gremlin1 = gremlinList.get(0);
                Gremlins gremlin2 = gremlinList.get(1);
                Gremlins gremlin3 = gremlinList.get(2);
                Gremlins gremlin4 = gremlinList.get(3);

                gremlin1.draw(this);
                gremlin2.draw(this);
                gremlin3.draw(this);
                gremlin4.draw(this);

                gremlin1.tick();
                gremlin2.tick();
                gremlin3.tick();
                gremlin4.tick();

                slime.left = gremlin1.gremlinLeft;
                slime.right = gremlin2.gremlinRight;
                slime.up = gremlin3.gremlinUp;
                slime.down = gremlin4.gremlinDown;

                if (countSlime == 0) {
                    fireball.x = gremlin1.y;
                    fireball.y = gremlin1.x;
                }
                countSlime++;
            }
            // draw wizard and call its tick outside the two levels
            wizard.draw(this);
            wizard.tick();
            this.exit.draw(this);
            this.tele.draw(this);
            // draw powerup
            if (wizard.powerthere == true) {
                this.powerup.draw(this);
            }
            // draw fireball when it is fired
            if (fireball.fired == true) {
                if (countFire == 0) {
                    fireball.x = wizard.y;
                    fireball.y = wizard.x;
                }
                countFire++;
                fireball.draw(this);
                fireball.tick();
            } else {
                countFire = 0;
            }

        }

    }

    /**
     * Load level one UI
     */
    public void loadLevelOne() {
        // create new scanner and create array from file
        try {
            Scanner levelOne = new Scanner(levelFileOne);
            int x = 0;
            while (levelOne.hasNextLine()) {
                String line = levelOne.nextLine();
                String[] lineArray = line.split("");
                for (int i = 0; i < 36; i++) {
                    levelOneArray[x][i] = lineArray[i];
                }
                x++;
            }
            levelOne.close();
        } catch (Exception e) {
        }

        // draw the map and create new objects
        for (int row = 0; row < 33; row++) {
            for (int column = 0; column < 36; column++) {
                if (levelOneArray[row][column].equals("X") || levelOneArray[row][column].equals("B")) {
                    wallList[row][column] = true;
                }
                if (levelOneArray[row][column].equals("X")) {
                    Stonewall c = new Stonewall(stoneWall, row * 20, column * 20);
                    stoneList.add(c);
                } else if (levelOneArray[row][column].equals("B")) {
                    Brickwall b = new Brickwall(brickWall, row * 20, column * 20);
                    brickList.add(b);
                } else if (levelOneArray[row][column].equals(" ")) {
                    continue;

                } else if (levelOneArray[row][column].equals("G")) {
                    Gremlins c = new Gremlins(gremlin, row * 20, column * 20, wallList, wallListTwo);
                    gremlinList.add(c);
                }
            }
        }

    }

    /**
     * Load level two UI
     */
    public void loadLevelTwo() {
        // logic to display UI
        textFont(lives, 18);
        fill(255);
        text("Lives: ", 10, 695);
        text("Level: 2/2 ", 300, 695);

        if (wizard.lives == 3) {
            image(wizardLives, 60, 680);
            image(wizardLives, 80, 680);
            image(wizardLives, 100, 680);
        } else if (wizard.lives == 2) {
            image(wizardLives, 60, 680);
            image(wizardLives, 80, 680);
        } else if (wizard.lives == 1) {
            image(wizardLives, 60, 680);
        }

        // read level file two and create array
        try {
            Scanner levelTwo = new Scanner(levelFileTwo);

            int x = 0;
            while (levelTwo.hasNextLine()) {
                String line = levelTwo.nextLine();
                String[] lineArray = line.split("");
                for (int i = 0; i < 36; i++) {
                    levelTwoArray[x][i] = lineArray[i];
                }
                x++;
            }

            // draw the map
            for (int row = 0; row < 33; row++) {
                for (int column = 0; column < 36; column++) {
                    if (levelTwoArray[row][column].equals("X") || levelTwoArray[row][column].equals("B")) {
                        wallListTwo[row][column] = true;
                    }
                    if (levelTwoArray[row][column].equals("X")) {
                        Stonewall c = new Stonewall(stoneWall, row * 20, column * 20);
                        stoneListTwo.add(c);
                    } else if (levelTwoArray[row][column].equals("B")) {
                        Brickwall b = new Brickwall(brickWall, row * 20, column * 20);
                        brickListTwo.add(b);
                    } else if (levelTwoArray[row][column].equals(" ")) {
                        continue;
                    } else if (levelTwoArray[row][column].equals("W")) {
                        wizard.draw(this);
                    } else if (levelTwoArray[row][column].equals("G")) {
                        Gremlins c = new Gremlins(gremlin, row * 20, column * 20, wallList, wallListTwo);
                        gremlinListTwo.add(c);
                    } else if (levelTwoArray[row][column].equals("E")) {
                        this.exit.draw(this);
                    }
                }
            }

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        PApplet.main("gremlins.App");
    }
}
