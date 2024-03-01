import javafx.scene.paint.Color; 
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *Note that instead of one colour, we have implemented one colour for each state of the parameter alive, alive = true and alive = false respectively
 *This is useful for testing purposes
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
     * Methods to manipulate the new parameters aliveColour and deadColour
       */
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
