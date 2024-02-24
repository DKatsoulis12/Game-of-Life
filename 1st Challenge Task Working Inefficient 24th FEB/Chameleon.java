import javafx.scene.paint.Color;
import java.util.List;

public class Chameleon extends Cell {
    private static final int populationCamouflage = 2;

    public Chameleon(Field field, Location loc, Color alivecol, Color deadcol) {
        super(field, loc, alivecol, deadcol);
    }

    /*
    public void act() {
        // Always set Chameleon cells as alive
        setAlive();

        // Toggle between magenta and cyan in each generation
        if (getColor().equals(Color.MAGENTA)) {
            setColor(Color.CYAN);
        } else {
            setColor(Color.MAGENTA);
        }

        // Make sure to update the state after changing the color
        setNextState(true);
    }*/
    
    

public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    int neighboursSize = 0;
    
    for (Cell neighbour : neighbours) {
        if (neighbour instanceof Mycoplasma && neighbour.isAlive()) {
            neighboursSize++;
        }
    }
    //System.out.println("Neighbour Size: " + neighboursSize);
    if (isAlive()) {
        if (neighboursSize >= populationCamouflage) {
            setAliveColor(Color.CYAN);
        } else {
            setAliveColor(Color.MAGENTA);
        }
    }
}

    @Override
    public void updateState() {
        // Override updateState to ensure all Chameleon cells stay alive
        setAlive();
    }
}
