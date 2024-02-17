import javafx.scene.paint.Color;
import java.util.List;
/**
 * Write a description of class CameloenCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CameloenCell extends Cell
{
    // instance variables - replace the example below with your own

    private static final int populationnCamouflage = 2;
    /**
     * Constructor for objects of class CameloenCell
     */
    public CameloenCell(Field field, Location loc, Color col)
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
        if (neighboursSize >= populationnCamouflage) {
            setColor(Color.BLUE);
        } else {
            setColor(Color.MAGENTA);
        }
    }
}

}
