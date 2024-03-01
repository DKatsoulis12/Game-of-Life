import javafx.scene.paint.Color;

/**
 * White blood cells- CANNOT REPRODUCE.
 * Require A MINIMUM of 2 non-bacteria neighbours to survive to the next generation.
 * If they are surrounded by 4 or more bacteria, they permanently die.
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2022.01.06
   */
public class WhiteBlood extends Mycoplasma
{

    /**
     * Constructor for objects of class WhiteBlood. Note that White Blood objects cannot be diseased!
     */
    public WhiteBlood(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = false;
    }
    
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        fieldStats = SimulatorView.getFieldStats();
        bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
        nonBacteriaCount = neighbours.size() - bacteriaCount;
        determineNextState(2,2);
    }
    
/** Used to determine the next state of the WhiteBlood object by combining the rules for staying alive and dying
 * Achieves this by employing the aliveCell() method defined in the same class. Note that since white blood cells cannot reproduce, no reproduction() method is implemented 
 * @param isolation the minimum number of neighbours with which the WhiteBlood cell can survive
 * @param survival the ideal number of neighbours with which WhiteBlood cell can survive
   */
    protected void determineNextState(int isolation, int survival){
        if (isAlive()) {
            tempState = aliveCell(isolation,survival);
        } else {
            tempState = false;
        }
        setNextState(tempState);
    }  
    
    
/** Replaces the aliceCell() method of Mycoplasma so that the next state (alive/dead) of alive WhiteBlood cells can be determined according to the rules for white blood cells.
 * @param isolation the minimum number of neighbours with which the WhiteBlood cell can survive
 * @param survival the ideal number of neighbours with which the WhiteBlood cell can survive
 * @return the next state (alive/dead) of the WhiteBlood object 
 **/   
    @Override
    protected boolean aliveCell(int isolation, int survival){
        //isolation- less than 2 neighbours
        if (neighbourCount < isolation){
            return false;
        }
        else if (bacteriaCount >= 4) {
            return false;
        } 
        //survival- 2 or 3 neighbours
        else if(nonBacteriaCount >= survival){
            return true;
        }
        else{
            return false;
        }
    }
    
}
