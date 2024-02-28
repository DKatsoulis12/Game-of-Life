import javafx.scene.paint.Color;
import java.util.List;

/**
 * Write a description of class AdjustiveMycoplasma here.
 *Once working, add calcultaions based on grid widht & height!
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdjustiveMycoplasma extends Mycoplasma
{
    private int reproductionRate;
    /**
     * Constructor for objects of class AdjustiveMycoplasma
     */
    public AdjustiveMycoplasma(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location,alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = true;
    }
    
    public void act(){
        fieldStats = SimulatorView.getFieldStats();
        int populationCount = fieldStats.getCellCount(this.getClass());
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size(); 
        
        determineDisease();
        
        if (isDiseased) {
            actDiseased();
        } else {
            determineReproductionRate(populationCount);
            determineNextState(2,3,reproductionRate);
        }
    }
    
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

