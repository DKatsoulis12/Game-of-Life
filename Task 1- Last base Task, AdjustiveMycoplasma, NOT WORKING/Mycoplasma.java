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
    protected int mycoCount;
    protected int adjCount;
    protected boolean willChange;
    protected FieldStats fieldStats = SimulatorView.getFieldStats();
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
        willChange = false;
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    countLivingNeighbours(neighbours);
    Color orange = Color.ORANGE;
    Color black = Color.BLACK;

    System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());

    if (isAlive()) {
        nextAlive = aliveCell(2,3);
        if (!nextAlive) {
            //setColor(black);
            fieldStats.decrementCount(this.getClass());
        }
    } else {
        nextAlive = reproduction(3);
        if (nextAlive) {
            //setColor(orange);
            fieldStats.incrementCount(this.getClass());
        }
    }

    System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
}

protected void countLivingNeighbours(List<Cell> neighbours) {
    for (Cell neighbour : neighbours) {
        if (neighbour.isAlive()) {
            neighbourCount++;
            if(neighbour instanceof Mycoplasma){
                mycoCount++;
            }
            else if(neighbour instanceof AdjustiveMycoplasma){
                adjCount++;
            }
        }
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
            if(adjCount == AdjustiveMycoplasma.reproductionRate){
                willChange = true;
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public void updateState() {
        if(willChange == true){
            AdjustiveMycoplasma adjMyco = new AdjustiveMycoplasma(getField(), getLocation(), Color.RED);
            adjMyco.setAlive();
            getField().place(adjMyco, getLocation());
        }
        alive = nextAlive;
    }
    
    }
    
    
   
