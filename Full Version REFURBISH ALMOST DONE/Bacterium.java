import javafx.scene.paint.Color;
import java.util.List;

/**
 * Bacteria- CAN REPRODUCE.
 * If 2 bacteria or more are around a mycoplasma 
 * (or its subclases that have not overriden AliveCell() ) that mycoplasma dies.
 * If they have A MINIMUM of 2 white blood cells around them they die.
 * Bacteria require A MAXIMUM of 2 non-bacteria neighbours to survive.
 * For a dead bacterium to become alive, it must have no white blood cell neighbours and a maximum of 2 non-bacteria neighbours.
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2024.02.29
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
     * If 2 bacteria or more are around a mycoplasma 
     * (or its subclases that have not overriden AliveCell() ) that mycoplasma dies.
     * If they have A MINIMUM of 2 white blood cells around them they die.
     * Bacteria require A MAXIMUM of 2 non-bacteria neighbours to survive.
     * For a dead bacterium to become alive, it must have no white blood cell neighbours and a maximum of 2 non-bacteria neighbours.
     * 
     * 
     * 
     * The more white blood cells and mycoplasmata die, the more bacteria can survive and reproduce
     * The more bacteria die, the higher
     * the probability of existing white blood cells surviving in the next generation
     */
    /**
     * Constructor for objects of class Bacterium. Note that Bacteria objects cannot be diseased!
     */
    public Bacterium(Field field, Location location, Color alivecol, Color deadcol) {
        super(field, location, alivecol, deadcol);
        this.isDiseased = false;
        this.nextDiseased = false;
        this.diseaseAbility = false;
    }

    /** The act() method for bacteria. The determineNextState() method is directly inherited from the Mycoplasma class.
       * However, the methods called by determineNextState() (aliveCell(), reproduction()) are overriden within the Bacterium class
       */
    public void act() {
        neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        bacteriaCount = countLivingNeighbours(neighbours, Bacterium.class);
        nonBacteriaCount = neighbours.size() - bacteriaCount;
        whiteBloodNeighbours = countLivingNeighbours(neighbours,WhiteBlood.class);
        determineNextState(whiteBloodNeighbours,nonBacteriaCount,whiteBloodNeighbours);
    }

/**Replaces the aliveCell() method of Mycoplasma 
 * Determines whether an alive bacterium will survive in the next generation, depending on the number of non bacteria and white blood neighbours, according to the rules of Bacterium
 * @param: whiteBloodPop the number of alive white blood cell neighbours
 * @param  nonBacteriaPop the number of alive bacteria neighbours
 * @return the next state (alive/dead) of the object 
 */
    @Override
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
    
/**Replaces the reproduction() method of Mycoplasma 
 * Determines whether a dead Bacterium will be become alive or not in the next generation, depending on the number of non bacteria and white blood neighbours, according to the rules of Bacterium
 * @ param whiteBloodPop the number of alive white blood cell neighbours
 * @return the next state (alive/dead) of the object 
 */
    @Override
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
    
