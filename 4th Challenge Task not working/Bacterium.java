import javafx.scene.paint.Color;
import java.util.List;

/**
 * Write a description of class Bacterium here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bacterium extends Mycoplasma
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
     * If they have A MAXIMUM of α white blood cells around them (a < γ) they die.
     * Bacteria require A MAXIMUM of γ non white blood neighbours to survive.
     * For a dead bacterium to become alive, it must have 0 white blood cell neighbours and a maximum of β mycoplasma
     * α<β<γ
     * 
     * 
     * 
     * The more white blood cells and mycoplasmata die, the more bacteria can survive and reproduce
     * The more bacteria die, the higher
     * the probability of existing white blood cells surviving in the next generation
     */
    private int whiteBloodNeighbours;
    /**
     * Constructor for objects of class Bacterium
     */
    public Bacterium(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = false;
    }

    
    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        fieldStats = SimulatorView.getFieldStats();
        
        //mycoCount = countLivingNeighbours(neighbours, Mycoplasma.class);
        //bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
        nonBacteriaCount = neighbours.size() - bacteriaCount;
        whiteBloodNeighbours = countLivingNeighbours(neighbours,WhiteBlood.class);
        
        determineNextState(whiteBloodNeighbours,nonBacteriaCount,whiteBloodNeighbours);
    
        //System.out.println("After act(): Cell at " + getLocation() + " isAlive: " + isAlive());
    }

    
    /*
     * If bacteria alive:
     *    If the number of white blood cell neighbours around them >= 2:
     *        They die 
     *    Else if the number of non bacteria neighbours >= 3:
     *        They die 
     *    Else:
     *        They survive
     * If bacteria is dead:
     *    If the number of white blood cells > 0:
     *          They stay dead
     *    Else if the number of non bacteria neighbours >= 3:
     *          They stay dead
     *    Else:
     *          They become alive
     */
    
    protected boolean aliveCell(int whiteBloodPop, int nonBacteriaPop){
        //isolation- less than 2 neighbours
        if (whiteBloodPop >= 2){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(nonBacteriaPop >= 3){
            return false;
        }
        //overpopulation- more than 3 neighbours
        else{
            return true;
        }
    }

    protected boolean reproduction(int whiteBloodPop){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (whiteBloodPop > 0){
            return false;
        }
        else if (nonBacteriaCount >= 3) {
            return false;
        } else {
            return true;
        }
    }
}
    
