import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {
    protected int neighbourCount;
    protected boolean tempState;
    protected FieldStats fieldStats;
    public Mycoplasma(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    neighbourCount = neighbours.size();
    fieldStats = SimulatorView.getFieldStats();
    Color orange = Color.ORANGE;
    Color black = Color.BLACK;

    //System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());
    determineNextState(2,3,3);

    //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
}

private int countLivingNeighbours(List<Cell> neighbours) {
    int count = 0;
    for (Cell neighbour : neighbours) {
        if (neighbour.isAlive()) {
            count++;
        }
    }
    return count;
}

protected void determineNextState(int isolation, int survival,int reproductionRate){
    if (isAlive()) {
        tempState = aliveCell(isolation,survival);
        if (!tempState) {
            //setColor(black);
            //fieldStats.decrementCount(this.getClass());
        }
        setNextState(tempState);
    } else {
        tempState = reproduction(reproductionRate);
        if (tempState) {
            //setColor(orange);
            //fieldStats.incrementCount(this.getClass());
        }
        setNextState(tempState);
    }
}


    protected boolean aliveCell(int isolation, int survival){
        //isolation- less than 2 neighbours
        if (neighbourCount < isolation){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == isolation || neighbourCount == survival){
            return true;
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }
    
    protected boolean reproduction(int reproduction){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (neighbourCount == reproduction){
            return true;
        }
        else {
            return false;
        }
    }
    
    }
    
    
   
