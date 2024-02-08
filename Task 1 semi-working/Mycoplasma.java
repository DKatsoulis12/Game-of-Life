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
    private int neighbourCount;
    private boolean tempState;
    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        FieldStats fieldStats = SimulatorView.getFieldStats();
        Color orange = Color.ORANGE;
        Color black = Color.BLACK;
        if (isAlive()) {
            tempState = aliveCell();
            if (tempState == false){
                setColor(black);
                fieldStats.decrementCount(this.getClass());
            }
            setNextState(tempState);
        }
        else if (isAlive() == false){
            tempState = reproduction();
            if(tempState){
                setColor(orange);
                fieldStats.incrementCount(this.getClass());
            }
            setNextState(tempState);
            
        }
        
    //updateState();
}

    private boolean aliveCell(){
        //isolation- less than 2 neighbours
        if (neighbourCount < 2){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == 2 || neighbourCount == 3){
            return true;
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }
    
    private boolean reproduction(){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (neighbourCount == 3){
            return true;
        }
        else {
            return false;
        }
    }
    
    }
    
    
   
