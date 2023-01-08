package gremlins;

//import packages
import processing.core.PApplet;
import processing.core.PImage;

public interface Features {

    /**
     * Setup methods for the interface
     */
    // draw the image
    public void draw(PApplet app);

    // get x position
    public int getx();

    // get y position
    public int gety();

    // get width of sprite
    public int getWidth();

    // get height of sprite
    public int getHeight();

}
