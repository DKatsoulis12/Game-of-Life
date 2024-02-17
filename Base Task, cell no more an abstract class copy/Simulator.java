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

    private static final double MYCOPLASMA_ALIVE_PROB = 0.25;
    private static final double CHAMELEON_ALIVE_PROB = 0.45;
    private List<Cell> cells;
    private Field field;
    private int generation;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }


private void populate() {
    Random rand = Randomizer.getRandom();
    field.clear();
    cells.clear(); // Ensure cells list is cleared before populating

    for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            Location location = new Location(row, col);
            Cell cell;
            double random = rand.nextDouble();
            if (random <= MYCOPLASMA_ALIVE_PROB) {
                cell = new Mycoplasma(field, location, Color.ORANGE);
                cell.setAlive();// Ensure the cell is initially alive if the condition is met
            } 
            /*else if(random > MYCOPLASMA_ALIVE_PROB && random <= CHAMELEON_ALIVE_PROB){
                cell = new Chameleon(field, location, Color.MAGENTA);
                cell.setAlive();
            }*/
            else {
                cell = new Cell(field, location, Color.BLUE);
                cell.setDead();
            }
            cells.add(cell);
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
     * Randomly populate the field live/dead life forms
     */
    

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
