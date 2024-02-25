import javafx.scene.paint.Color;
import java.util.List;
import java.util.Random;

/**
 * Write a description of class NonDeterminstic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NonDeterministic extends Mycoplasma
{
    
   // instance variables - replace the example below with your own
    
public NonDeterministic(Field field, Location location, Color alivecol, Color deadcol) {
    super(field, location, alivecol, deadcol);
}

    /**
    * This is how the NonDeterministic decides if it's alive or not
    */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    neighbourCount = neighbours.size();
    fieldStats = SimulatorView.getFieldStats();

    Random rand = Randomizer.getRandom();
    double rng = rand.nextDouble();
    //System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());
    System.out.println("Cell at " + getLocation() + " has rng value: " + rng);
    if (rng <= 0.38) {
        if (isAlive()) {
            tempState = aliveCell(3,4);
            if (!tempState) {
                //fieldStats.decrementCount(this.getClass());
            }   
        } else {
             tempState = reproduction(4);
             if (tempState) {
                //fieldStats.incrementCount(this.getClass());
             }
        }
    } else {
        if (isAlive()) {
            tempState = aliveCell(2,5);
            if (!tempState) {
                //fieldStats.decrementCount(this.getClass());
            } 
        } else {
            tempState = reproduction(2);
            if (tempState) {
                //fieldStats.incrementCount(this.getClass());
            }
        }
    }
    setNextState(tempState);
    //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
}

}
