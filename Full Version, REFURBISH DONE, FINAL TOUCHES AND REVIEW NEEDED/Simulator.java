import javafx.scene.paint.Color; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class Simulator {

    private static final double MYCOPLASMA_PROB = 0.3;
    private static final double BACT_PROB = 0.55;
    private static final double NONDETERMIN_PROB = 0.7;
    private static final double WHITEBLOODPROB = 0.85;
    private static final double ADJMYCO_PROB = 0.93;
    private List<Cell> cells;
    private Field field;
    private int generation;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

/** 
 * To populate the field, two randomly generated values are used
 * The first randomly generated value is compared with the probabilities for each organism set as  private static final double parameters for the Simulator class
 * When the first randomly generated value fits one of the bounds for the probabilities, a new cell object of that type is created
 * Then the second random value is compared with 0.5, and then the cell is either set as dead or alive with a 50% chance of either happening
 * Chameleon and WhiteBlood objects are always set alive, because Chameleons cannot die, and WhiteBlood cells remain permanently dead once they die
 * Note that essentially, each cell in the grid can be alive, dead, diseased and not diseased, but its cell type cannot change, meaning that it is always inhabited by cell of a fixed class
   */
private void populate() {
    Random rand = Randomizer.getRandom();
    field.clear();
    cells.clear();
    for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            Location location = new Location(row, col);

            double randomVal1 = rand.nextDouble();
            double randomVal2 = rand.nextDouble();
            
            if (randomVal1 <= MYCOPLASMA_PROB) {
                Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE,Color.BROWN);
                if (randomVal2 <= 0.5) {
                    myco.setAlive();
                } else {
                    myco.setDead();
                }
                cells.add(myco);
            } else if (randomVal1 <= BACT_PROB) {
                Bacterium bact = new Bacterium(field, location,Color.GOLDENROD,Color.GRAY);
                if (randomVal2 <= 0.5) {
                    bact.setAlive();
                } else {
                    bact.setDead();
                }
                cells.add(bact);
            } else if (randomVal1 <= NONDETERMIN_PROB) {
                NonDeterministic nonDet = new NonDeterministic(field, location, Color.YELLOW,Color.BLACK);
                if (randomVal2 <= 0.5) {
                    nonDet.setAlive();
                } else {
                    nonDet.setDead();
                }
                cells.add(nonDet);
            } else if (randomVal1 <= WHITEBLOODPROB) {
                WhiteBlood wbc = new WhiteBlood(field, location, Color.WHITE,Color.PURPLE);
                wbc.setAlive();
                cells.add(wbc);
            } else if(randomVal1 <= ADJMYCO_PROB){
                AdjustiveMycoplasma adjMyco = new AdjustiveMycoplasma(field, location, Color.GREEN,Color.RED);
                if (randomVal2 <= 0.5) {
                    adjMyco.setAlive();
                } else {
                    adjMyco.setDead();
                }
                cells.add(adjMyco);
            }
            
            else {
                Chameleon chameleon = new Chameleon(field, location, Color.MAGENTA,Color.WHITE);
                chameleon.setAlive(); // Use setAlive without parameters
                cells.add(chameleon);
            } 
        }
    }
}

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        generation++;
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            cell.act();
        }
        for (Cell cell : cells) {
          cell.updateState();
        }
        //The print statement below is useful for testing purposes, to seperate the information printed out for each generation
        //System.out.println("---------------------------------------------");
    }   

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    
    public Field getField() {
        return field;
    }

    public int getGeneration() {
        return generation;
    }
}
