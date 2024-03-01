import javafx.scene.paint.Color;
import java.util.List;

/**
 * The Adjustive Mycoplasma adjusts the number of neighbours needed to reproduce depending on its own total population.
 * If the population is 20% or less of the total grid, then 2 neighbours are needed to reproduce
 * Otherwise, the population is 40% or less of the total grid, then 3 neighbours are needed to reproduce
 * Otherwise, the population is 60% or less of the total grid, then 4 neighbours are needed to reproduce
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2022.01.06
 */
public class AdjustiveMycoplasma extends Mycoplasma
{
    //the variable number of neighbours needed for a dead AdjustiveMycoplasma cell to become alive
    private int reproductionRate;
    /**
     * Constructor for objects of class AdjustiveMycoplasma
     */
    public AdjustiveMycoplasma(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location,alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = true;
        this.reproductionRate = 0;
    }
    
    /** 
     * Similar to the act() method of mycoplasma, except the number of neighbours required for a dead cell to reproduce is calculated each time the act() method is called.
     * Also calls the getCellCount() method from the FieldStats class. The getCellCount() method was created specfically to be used in this class.
     * The determineNextState() method is inherited from Mycoplasma 
       */
    public void act(){
        fieldStats = SimulatorView.getFieldStats();
        int populationCount = fieldStats.getCellCount(this.getClass());
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size(); 
        
        determineDisease();
        
        if (isDiseased) {
            actDiseased();
        } else {
            if(! isAlive()){
            determineReproductionRate(populationCount);
        }
            determineNextState(2,3,reproductionRate);
        }
    }
    
    /**
     * Assign a value to reproductionRate depending on the population. The current population is compared with the total number of cells in the field.
     * The total number of cells in the field is calculated with the product of the GRID_WIDTH and GRID_HEIGHT public static variables from SimulatorView.
     * @param populationCount the current population of AdjustiveMycoplasma objects in the field
     */
    private void determineReproductionRate(int populationCount){
        int gridSize = SimulatorView.GRID_WIDTH * SimulatorView.GRID_HEIGHT;
        if(populationCount <= 0.2 * gridSize){
            //underpopulation- rapid reproduction
            reproductionRate = 2;
        }
        else if(populationCount <= 0.4 * gridSize){
            //normal population - normal reproduction
            reproductionRate = 3;
        }
        else{
            //overpopulation - slow reproduction
            reproductionRate = 4;
        }
    }
}

