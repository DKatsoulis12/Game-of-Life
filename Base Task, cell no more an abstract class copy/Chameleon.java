import javafx.scene.paint.Color;
import java.util.List;
/**
 * Write a description of class Chameleon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chameleon extends Cell
{
    // instance variables - replace the example below with your own

    private static final int populationCamouflage = 2;
    /**
     * Constructor for objects of class Chameleon
     */
    public Chameleon(Field field, Location loc, Color col)
    {
        // initialise instance variables
        super(field,loc,col);
        
    }
    
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    int neighboursSize = 0;
    
    for (Cell neighbour : neighbours) {
        if (neighbour instanceof Mycoplasma && neighbour.isAlive()) {
            neighboursSize++;
        }
    }
    System.out.println("Neighbour Size: " + neighboursSize);
    if (isAlive()) {
        if (neighboursSize >= populationCamouflage) {
            setColor(Color.CYAN);
        } else {
            setColor(Color.MAGENTA);
        }
    }
}

}
