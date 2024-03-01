import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {
    protected int neighbourCount;
    protected boolean tempState;
    protected FieldStats fieldStats;
    
    protected List<Cell> neighbours;
    protected int bacteriaCount;
    protected int nonBacteriaCount;
    protected boolean isDiseased;
    // the parameter below determines whether or not the class has the ability catch a disease
    protected boolean diseaseAbility;
    protected boolean nextDiseased;
    protected int whiteBloodNeighbours;
    public Mycoplasma(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        //Whether or not objects of the class can become diseased is determined in the constructor, the other disease-related parameters are given default initial values that van change
        this.diseaseAbility = true;
    }

    /**
    * This is how the Mycoplasma decides if it will become diseased, and if it will be alive or not in the next generation
    */
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        // The Mycoplasma can become diseased based on a random probability
        determineDisease();
        //If the Mycoplasma is diseased, it has a completely new behaviour, therefore it is pointless to continue with the act() method
        if (isDiseased) {
            actDiseased();
        }
        // If the Mycoplasma is not disesed, then it behaves normally
        else {
            bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
            determineNextState(2,3,3);
        }
        
    }
    /**
   * Counts the living neighbours of a specific class 
   * @param neighbours the list containing all living neighbour cells
   * @param myClass the class type that is to be counted
   * @return count the number of neighbours that are objects of the class specified
   */
    protected int countLivingNeighbours(List<Cell> neighbours, Class<?> myClass) {
    int count = 0;
    //Ensures the List is not null, otherwise 
    if (neighbours != null){
        for (Cell neighbour : neighbours) {
        if (neighbour.isAlive() && myClass.isInstance(neighbour)) {
            count++;
        }
    }
    }
    return count;
  }

/** Determines whether the object will contract a disease in this generation */
   protected void determineDisease() {
    if (diseaseAbility == true && isDiseased == false && isAlive()) {
        Random rng = new Random();
        double rand = rng.nextDouble();
        
        if (rand <= 0.2) {
            isDiseased = true;
        }
    }
  }

/** Used to determine the next state of the object by combining the rules for staying alive, dying, reproducing and staying dead
 * Achieves this by employing the aliveCell() and reproduction() methods 
 * @param isolation the minimum number of neighbours with which the cell can survive
 * @param survival the ideal number of neighbours with which the cell can survive
 * @param reproductionRate the number of neighbours needed for a dead cell to be born (become alive again)
   */
   protected void determineNextState(int isolation, int survival,int reproductionRate){
    if (isAlive()) {
        tempState = aliveCell(isolation,survival);
    } else {
        tempState = reproduction(reproductionRate);
    }
    setNextState(tempState);
  }

/**Determines whether an alive cell will survive in the next generation
 * @param isolation the minimum number of neighbours with which the cell can survive
 * @param survival the ideal number of neighbours with which the cell can survive
 * @return the next state (alive/dead) of the object 
 **/
protected boolean aliveCell(int isolation, int survival){
        //isolation - dies
        if (neighbourCount < isolation){
            return false;
        } 
        //more than 2 bacteria neighbours means the cell dies, according to the rules
        else if (bacteriaCount >= 2) {
            return false;
        } 
        //survival- lives on to the next generation
        else if(neighbourCount == isolation || neighbourCount == survival){
            return true;
        }
        //overpopulation- dies
        else{
            return false;
        }
}

/**Determines whether a dead cell will be become alive or not in the next generation
 * @ param reproduction the number of neighbours needed for a dead cell to be born (become alive again)
 * @return the next state (alive/dead) of the object 
 **/
    protected boolean reproduction(int reproduction){
        //1 bacterium or more prohibits a bacterium from becoming alive
        if (bacteriaCount >= 1) {
            return false;
        }
        // The right amount of neighbours- becomes alive for the next generation
        else if (neighbourCount == reproduction){
            return true;
        }
        else {
            return false;
        }
    }

/** Replaces the act() method seen above so that diseased cells have a new behaviour
 * According to the rules a diseased cell will always be diseased hence why we immediately use setNextDiseasedState(true)
 * The print statement is useful for testing purposes
 * Additionally, since once dead a diseased cell will not be able to become alive, the method proceeds only if the diseased cell is alive
   */    
    protected void actDiseased(){
        setNextDiseasedState(true);
        //System.out.println("Diseased Cell at: " + getLocation() + "is: " + isAlive());
        if (isAlive()) {
            whiteBloodNeighbours = countLivingNeighbours(neighbours,WhiteBlood.class);
            tempState = diseasedCell(whiteBloodNeighbours);
            setNextState(tempState);
        }
    }

/** Replaces the aliceCell() method seen above so that the next state (alive/dead) of diseased cells can be determined
 * Note that the spreadDisease() method is only called on a specific condition, since a diseased alive cell is weakened and therefore cannot always survive and spread the disease
 * @ param wbcCount the number of alive white blood cell neighbours
 * @return the next state (alive/dead) of the diseased object 
 **/   
    protected boolean diseasedCell(int wbcCount) {
        if (neighbourCount >= 3) {
            return false;
        } else if (wbcCount >= 1){
            return false;
        } else if(neighbourCount <= 2 && neighbourCount > 0) {
            spreadDisease();
            return true;
        }
        else{
            return true;
        }
    }
/** Make certain neighbours of a diseased cell (the ones that can actually be diseased) diseased */
    protected void spreadDisease() {
        if (neighbours != null){
        for (Cell neighbour : neighbours) {
            if (neighbour.isAlive()){
                if (neighbour instanceof Mycoplasma){
                    ((Mycoplasma) neighbour).setNextDiseasedState(true);
                } else if (neighbour instanceof AdjustiveMycoplasma) {
                    ((AdjustiveMycoplasma) neighbour).setNextDiseasedState(true);
                } else if (neighbour instanceof NonDeterministic) {
                    ((NonDeterministic) neighbour).setNextDiseasedState(true);
                }
            }
        }
    }
}
    
    protected void setNextDiseasedState(boolean value) {
        nextDiseased = value;
    }
    
    protected void updateDiseased() {
        isDiseased = nextDiseased;
    }
    
    protected void setDiseased(boolean value) {
        isDiseased = value;
    }
    
    /** 
     * Overrides the updateState() from the abstact class Cell, since with objects that inherit from Mycoplasma the isDiseased parameter needs to be updated along with alive.
       */
    @Override
    public void updateState() {
        super.updateState();
        isDiseased = nextDiseased;
    }
    
    }
    
    
   
