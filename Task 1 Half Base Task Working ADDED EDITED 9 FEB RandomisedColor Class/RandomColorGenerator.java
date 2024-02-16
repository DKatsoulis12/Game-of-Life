import java.util.Random;
import javafx.scene.paint.Color;
/**
 * Write a description of class RandomColorGenerator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javafx.scene.paint.Color;

public enum RandomColorGenerator {
    
    RED(Color.RED), 
    GREEN(Color.GREEN), 
    YELLOW(Color.YELLOW);

    private final Color color;
    private static final Random rng = new Random();
    
    public static Color getRandomColor() {
        RandomColorGenerator[] colors = values();
        return colors[rng.nextInt(colors.length)].getColor();
    }

    public Color getColor() {
        return color;
    }
    
    RandomColorGenerator(Color color) {
        this.color = color;
    }

}

