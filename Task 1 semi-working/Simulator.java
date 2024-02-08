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
    private void populate() {
      /*Location location1 = new Location(2, 1);
      Location location2 = new Location(2, 2);
      Location location3 = new Location(2, 3);
      Mycoplasma myco1 = new Mycoplasma(field, location1, Color.ORANGE);
      Mycoplasma myco2 = new Mycoplasma(field, location2, Color.ORANGE);
      Mycoplasma myco3 = new Mycoplasma(field, location3, Color.ORANGE);*/
     
     
      //USE THIS FOR TESTING PURPOSES
      //Random rand = Randomizer.getRandom();
      //field.clear();
      /*for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if ((row == 1 && col == 3) || (row == 2 && col == 1) || (row == 2 && col == 4) || (row == 3 && col == 1) || (row == 3 && col == 4)
          || (row == 4 && col == 2)){
            cells.add(myco);
          }
          else {
            myco.setDead();
            cells.add(myco);
          }
        }
      }*/
     
      //comment out below code
     
      Random rand = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
          if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            cells.add(myco);
          }
          else {
            myco.setDead();
            cells.add(myco);
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
