import javafx.scene.paint.Color;
import java.util.List;
import java.util.Random;

/**
 * Write a description of class NonDeterminstic here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NonDeterministic extends Cell
{
    
   // instance variables - replace the example below with your own

    private int neighbourCount;
    private boolean tempState;
    
public NonDeterministic(Field field, Location location, Color alivecol, Color deadcol) {
    super(field, location, alivecol, deadcol);
}

    /**
    * This is how the NonDeterministic decides if it's alive or not
    */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    neighbourCount = neighbours.size();
    FieldStats fieldStats = SimulatorView.getFieldStats();

    Random rand = Randomizer.getRandom();
    double rng = rand.nextDouble();
    //System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());
    System.out.println("Cell at " + getLocation() + " has rng value: " + rng);
    if (rng <= 0.38) {
        if (isAlive()) {
            tempState = aliveCellSet1();
            if (!tempState) {
                fieldStats.decrementCount(this.getClass());
            }   
        } else {
             tempState = reproductionSet1();
             if (tempState) {
                fieldStats.incrementCount(this.getClass());
             }
        }
    } else {
        if (isAlive()) {
            tempState = aliveCellSet2();
            if (!tempState) {
                fieldStats.decrementCount(this.getClass());
            } 
        } else {
            tempState = reproductionSet2();
            if (tempState) {
                fieldStats.incrementCount(this.getClass());
            }
        }
    }
    setNextState(tempState);
    
    /*if (isAlive()) {
        if (rng <= 0.38) {
            tempState = aliveCellSet1();
        } else {
            tempState = aliveCellSet2();
        }
        if (!tempState) {
            fieldStats.decrementCount(this.getClass());
        }
        setNextState(tempState);
    } else {
        if (rng <= 0.38) {
            tempState = reproductionSet1();
        } else {
            tempState = reproductionSet2();
        }
        if (tempState) {
            fieldStats.incrementCount(this.getClass());
        }
        setNextState(tempState);
    }*/

    //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
}

    private boolean aliveCellSet1(){
        //isolation- less than 2 neighbours
        if (neighbourCount < 3){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == 3 || neighbourCount == 4){
            return true;
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }
    
    private boolean aliveCellSet2(){
        //isolation- less than 2 neighbours
        if (neighbourCount < 2){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == 2 || neighbourCount == 5){
            return true;
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }
    
    private boolean reproductionSet1(){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (neighbourCount == 4){
            return true;
        }
        else {
            return false;
        }
    }
    
    private boolean reproductionSet2(){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (neighbourCount == 2){
            return true;
        }
        else {
            return false;
        }
    }

}
