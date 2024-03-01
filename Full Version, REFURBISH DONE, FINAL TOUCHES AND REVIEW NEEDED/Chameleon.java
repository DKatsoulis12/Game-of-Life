import javafx.scene.paint.Color;
import java.util.List;
/**
 * The Chameleon cell changes colour when the number of Mycoplasma neighbours is over a threshold, to disguise itself.
 * When the number of Mycoplasma neighbours is over a threshold, its colour for the next generation changes to Cyan
 * Otherewise, its colour for the next generation is magenta
 * Chameleons cannot die
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2022.01.06
 */
public class Chameleon extends Cell {
    //The minimum number of Mycoplasma neighbours required for the Cameleon object to change its colour in the next generation
    private static final int populationCamouflage = 2;

    public Chameleon(Field field, Location loc, Color alivecol, Color deadcol) {
        super(field, loc, alivecol, deadcol);
    }

/**
 * Note that only the Mycoplasma cells are counted as neighbours, and since Chameleons cannot die no change to the nextState variable of the method are made
   */
public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    int neighboursSize = 0;
    if(neighbours != null){
        for (Cell neighbour : neighbours) {
        if (neighbour instanceof Mycoplasma && neighbour.isAlive()) {
            neighboursSize++;
        }
    }
    }
    if (isAlive()) {
        if (neighboursSize >= populationCamouflage) {
            setAliveColor(Color.CYAN);
        } else {
            setAliveColor(Color.MAGENTA);
        }
    }
}
/**
 * Since Chameleons do not die, the updateState() method from cell is overriden and the Chameleon object is immediately set to alive
   */
    @Override
    public void updateState() {
        setAlive();
    }
}
