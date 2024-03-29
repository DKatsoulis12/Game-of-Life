import javafx.scene.paint.Color; 
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Cell {

    private boolean alive;    
    private boolean nextAlive; // The state of the cell in the next iteration
    private Field field;
    private Location location;
    private Color color = Color.WHITE;

    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        this.field = field;
        setLocation(location);
        setColor(col);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    public void act() {
        /*int count = 0;
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        for (Cell neighbour: neighbours) {
            if (neighbour instanceof Mycoplasma) {
                count++;
            }
        }*/
        
        
        
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        int mycoplasmaNeighbours = countInstanceNeighbours(Mycoplasma.class);
        //int mycoplasmaNeighbours = neighbours.size();
        if (mycoplasmaNeighbours == 3) {
            getField().place(new Mycoplasma(getField(), getLocation(), Color.ORANGE),getLocation()); 
            //setAlive();
        }
        
        
        
    }

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    protected void setAlive() {
        alive = true;
    }
    
    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }

    /**
     * Changes the state of the cell
     */
    public void updateState() {
        alive = nextAlive;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
    
    public int countInstanceNeighbours(Class<? extends Cell> cellClass) {
        int count = 0;
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        for (Cell neighbour: neighbours) {
            if (cellClass.isInstance(neighbour)) {
                count++;
            }
        }
        return count;
    }
    
    
}
