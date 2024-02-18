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
    private List<Cell> cells;
    private Field field;
    private int generation;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /*private void populate() {
    Random rand = Randomizer.getRandom();
    field.clear();
    cells.clear(); // Ensure cells list is cleared before populating

    for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            Location location = new Location(row, col);
            Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);

            if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
                myco.setAlive(); // Ensure the cell is initially alive if the condition is met
            } else {
                myco.setDead();
            }

            cells.add(myco);
            System.out.println("Cell at " + location + " isAlive: " + myco.isAlive());
        }
    }
}*/
/*private void populate() {
    Random rand = Randomizer.getRandom();
    field.clear();
    cells.clear();

    for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            Location location = new Location(row, col);

            Chameleon chameleon = new Chameleon(field, location, Color.MAGENTA);
            chameleon.setNextState(true); // Chameleon cells are always alive
            cells.add(chameleon);

            System.out.println("Cell at " + location + " isAlive: " + cells.get(cells.size() - 1).isAlive());
        }
    }
}*/

private void populate() {
    Random rand = Randomizer.getRandom();
    field.clear();
    cells.clear();

    for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
            Location location = new Location(row, col);

            double randomVal = rand.nextDouble();
            if (randomVal <= MYCOPLASMA_ALIVE_PROB) {
                Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
                myco.setAlive();
                cells.add(myco);
            } else if (randomVal <= 0.5) {
                Chameleon chameleon = new Chameleon(field, location, Color.MAGENTA);
                chameleon.setAlive(); // Use setAlive without parameters
                cells.add(chameleon);
            }
            else if (randomVal <= 0.65) {
                Oscillator oscillator = new Oscillator(field, location, Color.GREEN,this);
                oscillator.setAlive(); // Use setAlive without parameters
                cells.add(oscillator);
            }
            else {
                // Neutral cell (initially dead)
                Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
                myco.setDead();
                cells.add(myco);
            }
            
            System.out.println("Cell at " + location + " isAlive: " + cells.get(cells.size() - 1).isAlive());
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
