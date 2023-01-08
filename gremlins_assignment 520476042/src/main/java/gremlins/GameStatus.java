package gremlins;

//import packages
import java.util.*;
import processing.data.JSONArray;
import processing.data.JSONObject;
import java.lang.*;

public class GameStatus extends App {
    // retrieve details from the configuration file
    private JSONObject configuration = loadJSONObject("config.json");
    private int lives = configuration.getInt("lives");

    private JSONArray array = configuration.getJSONArray("levels");
    private JSONObject first = array.getJSONObject(0);
    private JSONObject second = array.getJSONObject(1);

    private String levelOne = first.getString("layout");
    private Double wCoolDownOne = first.getDouble("wizard_cooldown");
    private Double eCoolDownOne = first.getDouble("enemy_cooldown");

    private String levelTwo = second.getString("layout");
    private Double wCoolDownTwo = second.getDouble("wizard_cooldown");
    private Double eCoolDownTwo = second.getDouble("enemy_cooldown");

    /**
     * getter for levelone details
     */
    public String getLevelOne() {
        return this.levelOne;
    }

    /**
     * getter for level one wizard cooldown details
     */
    public double getWizardCoolDownOne() {
        return this.wCoolDownOne;
    }

    /**
     * getter for level one enemy cooldown details
     */
    public double getEnemyCoolDownOne() {
        return this.eCoolDownOne;
    }

    /**
     * getter for level two details
     */
    public String getLevelTwo() {
        return this.levelTwo;
    }

    /**
     * getter for level two wizard cooldown details
     */
    public double getWizardCoolDownTwo() {
        return this.wCoolDownTwo;
    }

    /**
     * getter for level two enemy cooldown details
     */
    public double getEnemyCoolDownTwo() {
        return this.eCoolDownTwo;
    }

    /**
     * getter for lives of wizard
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * getter for checking lives of wizard
     */
    public void checkLives() {
        if (lives <= 0) {
            // System.exit(0);
        }
    }

}
