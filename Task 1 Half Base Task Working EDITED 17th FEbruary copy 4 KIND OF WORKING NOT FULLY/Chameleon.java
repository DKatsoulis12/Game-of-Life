import javafx.scene.paint.Color;
import java.util.List;

public class Chameleon extends Cell {
    private static final int populationCamouflage = 2;

    public Chameleon(Field field, Location loc, Color col) {
        super(field, loc, col);
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
    
    System.out.println("Neighbour Size: " + neighboursSize);
    
    if (isAlive()) {
        if (neighboursSize >= populationCamouflage) {
            setColor(Color.CYAN);
        } else {
            setColor(Color.MAGENTA);
        }
    }
}

    @Override
    public void updateState() {
        // Override updateState to ensure all Chameleon cells stay alive
        setAlive();
    }
}
