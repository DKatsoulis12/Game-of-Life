import javafx.scene.paint.Color; 
import javafx.application.Platform;

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
    abstract public void act();

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
    /*public void updateState() {
        alive = nextAlive;
        if (alive == false) {
            DeadCell dead = new DeadCell(getField(),getLocation(),Color.BLUE);
            dead.setDead();
            getField().place(dead, getLocation());
        } else if (alive == true) {
            Mycoplasma myco = new Mycoplasma(getField(),getLocation(),Color.ORANGE);
            myco.setAlive();
            
            getField().place(myco, getLocation());
        }
    }*/
    
    public void updateState() {
    alive = nextAlive;

    // Create a separate thread for rendering the new cells with a delay
    new Thread(() -> {
        try {
            Thread.sleep(1000); // Adjust the delay duration (in milliseconds) as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            // Code to render new cells
            if (alive == false) {
                DeadCell dead = new DeadCell(getField(), getLocation(), Color.BLUE);
                dead.setDead();
                getField().place(dead, getLocation());
            } else if (alive == true) {
                Mycoplasma myco = new Mycoplasma(getField(), getLocation(), Color.ORANGE);
                myco.setAlive();
                getField().place(myco, getLocation());
            }
        });
    }).start();
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
}
