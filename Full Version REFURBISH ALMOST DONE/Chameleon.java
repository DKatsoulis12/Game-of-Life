import javafx.scene.paint.Color;
import java.util.List;

public class Chameleon extends Cell {
    private static final int populationCamouflage = 2;

    public Chameleon(Field field, Location loc, Color alivecol, Color deadcol) {
        super(field, loc, alivecol, deadcol);
    }

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

    @Override
    public void updateState() {
        setAlive();
    }
}
