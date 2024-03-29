import javafx.scene.paint.Color; 
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public abstract class Cell {

    private boolean alive;    
    private boolean nextAlive; // The state of the cell in the next iteration
    private Field field;
    private Location location;
    private Color aliveColor;
    private Color deadColor;
    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location, Color alivecol,Color deadcol) {
        alive = true;
        nextAlive = false;
        this.field = field;
        setLocation(location);
        setAliveColor(alivecol);
        setDeadColor(deadcol);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    public abstract void act();

    public int countInstanceNeighbours(Class<?> classI) {
    List<Cell> neighbours = field.getLivingNeighbours(location);
    int count = 0;

    for (Cell neighbour : neighbours) {
        // Check if the neighbor is an instance of the specified class and is alive
        if (classI.isInstance(neighbour) && neighbour.isAlive()) {
            count++;
        }
    }

    return count;
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

    public void setAliveColor(Color col) {
        aliveColor = col;
    }
    
    public void setDeadColor(Color col) {
        deadColor = col;
    }
    
    public Color getDeadColor() {
        return deadColor;
    }
    
    public Color getAliveColor() {
        return aliveColor;
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
}
