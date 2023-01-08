package gremlins;

import processing.core.PApplet;
import processing.core.PApplet;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void setupTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(3000);
        app.setup();

    }

    @Test
    public void loadLeveltwoTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(2000);
        app.loadLevelTwo();

    }

    @Test
    public void loadLevelOneTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(2000);
        app.loadLevelOne();
    }

    @Test
    public void drawTest() {
        App app = new App();
        PApplet.runSketch(new String[] { "App" }, app);
        app.setup();
        app.delay(2000);
        app.draw();
    }

}
