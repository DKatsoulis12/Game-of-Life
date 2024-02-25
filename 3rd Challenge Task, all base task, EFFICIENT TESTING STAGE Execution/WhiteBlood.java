import javafx.scene.paint.Color;

/**
 * Write a description of class WhiteBlood here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WhiteBlood extends Mycoplasma
{
    // instance variables - replace the example below with your own
    
    /*
     * White blood cells- CANNOT REPRODUCE.
     * Require A MINIMUM ρ number of mycoplama as neighbours to survive to the next generation.
     * If they are surrounded by λ bacteria, they permanently die.
     * 
     * 
     * Bacteria- CAN REPRODUCE.
     * If κ number of bacteria or more are around a mycoplasma 
     * (or its subclases that have not overriden AliveCell() ) that mycoplasma dies.
     * Bacteria require A MAXIMUM of γ mycoplasmata to survive.
     * If they have A MAXIMUM of α white blood cells around them (a < γ) they die.
     * For a dead bacterium to become alive, it must have 0 white blood cell neighbours and a maximum of β mycoplasma
     * α<β<γ
     * 
     * 
     * The more white blood cells and mycoplasmata die, the more bacteria can survive and reproduce
     * The more bacteria die, the higher
     * the probability of existing white blood cells surviving in the next generation
     */

    /**
     * Constructor for objects of class WhiteBlood
     */
    public WhiteBlood(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
    }
    
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        fieldStats = SimulatorView.getFieldStats();
        
        //mycoCount = countLivingNeighbours(neighbours, Mycoplasma.class);
        bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
        nonBacteriaCount = neighbours.size() - bacteriaCount;
        //System.out.println("Before act(): Cell at " + getLocation() + " isAlive: " + isAlive());
        
        
        determineNextState(2,2);
    
        //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
    }
    
    
    
    protected void determineNextState(int isolation, int survival){
        if (isAlive()) {
            tempState = aliveCell(isolation,survival);
        } else {
            tempState = false;
        }
        setNextState(tempState);
    }  
    
    
    
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
