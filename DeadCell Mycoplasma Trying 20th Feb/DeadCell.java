import javafx.scene.paint.Color;
import java.util.List;

/**
 * Write a description of class DeadCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DeadCell extends Cell
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class DeadCell
     */
    public DeadCell(Field field, Location location, Color col) {
        super(field, location, col);
    }

    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        int mycoplasmaNeighbourCount = 0;

        for (Cell neighbour : neighbours) {
            if (neighbour instanceof Mycoplasma) {
                mycoplasmaNeighbourCount++;
            }
        }

        if (mycoplasmaNeighbourCount == 3) {
            // Replace DeadCell with a new Mycoplasma in the next generation
            setNextState(true);
            //setColor(Color.ORANGE);
        } else {
            setNextState(false);
        }
    }
    
}
