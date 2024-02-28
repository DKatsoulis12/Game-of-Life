import javafx.scene.paint.Color; 
import java.util.List;
import java.util.Random;

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
    
    protected List<Cell> neighbours;
    protected int bacteriaCount;
    protected int nonBacteriaCount;
    protected boolean isDiseased;
    protected boolean diseaseAbility;
    protected boolean nextDiseased;
    protected int whiteBloodNeighbours;
    public Mycoplasma(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = true;
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        //fieldStats = SimulatorView.getFieldStats();
        
        
        determineDisease();
        if (isDiseased) {
            actDiseased();
        } else {
            bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
            //nonBacteriaCount = neighbours.size() - bacteriaCount;
        
            //System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());
            determineNextState(2,3,3);
        
            //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
        }
        
    }

protected int countLivingNeighbours(List<Cell> neighbours, Class<?> myClass) {
    int count = 0;
    for (Cell neighbour : neighbours) {
        if (neighbour.isAlive() && myClass.isInstance(neighbour)) {
            count++;
        }
    }
    return count;
}

protected void determineDisease() {
    if (diseaseAbility == true && isDiseased == false && isAlive()) {
        Random rng = new Random();
        double rand = rng.nextDouble();
        
        if (rand <= 0.2) {
            isDiseased = true;
        }
    }
}


protected void determineNextState(int isolation, int survival,int reproductionRate){
    if (isAlive()) {
        tempState = aliveCell(isolation,survival);
    } else {
        tempState = reproduction(reproductionRate);
    }
    setNextState(tempState);
}

    /*protected boolean symbiosisAlive(int isolation, int survival, int population1, int population2, Class<?> class1, Class<?> class2) {
        //isolation- less than 2 neighbours
        if (neighbourCount < isolation){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == isolation || neighbourCount == survival){
            if (countLivingNeighbours(neighbours,class1) >= population1) {
                return true;
            }
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }*/


    protected boolean aliveCell(int isolation, int survival){
        //isolation- less than 2 neighbours
        if (neighbourCount < isolation){
            return false;
        } 
        
        else if (bacteriaCount >= 2) {
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
        if (bacteriaCount >= 1) {
            return false;
        }
        else if (neighbourCount == reproduction){
            return true;
        }
        else {
            return false;
        }
    }
    
    protected void actDiseased(){
        System.out.println("Diseased Cell at: " + getLocation() + "is: " + isAlive());
        if (isAlive()) {
            whiteBloodNeighbours = countLivingNeighbours(neighbours,WhiteBlood.class);
            tempState = diseasedCell(whiteBloodNeighbours);
            setNextState(tempState);
        }
    }
    
    protected boolean diseasedCell(int wbcCount) {
        if (neighbourCount >= 3) {
            return false;
        } else if (wbcCount >= 1){
            return false;
        } else {
            spreadDisease();
            return true;
        }
    }
    
    protected void spreadDisease() {
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
    
    protected void setNextDiseasedState(boolean value) {
        nextDiseased = value;
    }
    
    protected void updateDiseased() {
        isDiseased = nextDiseased;
    }
    
    protected void setDiseased(boolean value) {
        isDiseased = value;
    }
    
    @Override
    public void updateState() {
        super.updateState();
        isDiseased = nextDiseased;
    }
    
    }
    
    
   
