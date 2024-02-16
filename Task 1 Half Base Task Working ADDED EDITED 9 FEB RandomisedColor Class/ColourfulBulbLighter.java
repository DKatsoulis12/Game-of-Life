import javafx.scene.paint.Color;
import java.util.List;

/**
 * Write a description of class ColourfulBulbLighter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColourfulBulbLighter extends Cell
{
    private int neighbourCount;
    private long lastColorChangeTime;
    private static final long COLOR_CHANGE_INTERVAL = 100; 
    private boolean tempState;
    
    /**
     * Constructor for objects of class ColourfulBulbLighter
     */
    public ColourfulBulbLighter(Field field, Location location, Color col)
    {
        // initialise instance variables
        super(field, location, col);
        lastColorChangeTime = System.currentTimeMillis();
    }

    @Override
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        neighbourCount = neighbours.size();
        FieldStats fieldStats = SimulatorView.getFieldStats();

        Color blue = Color.BLUE;
        Color red = Color.RED;
        long currentTime = System.currentTimeMillis();

        if (isAlive()) {
            tempState = aliveCell();
            
            if (!tempState) {
                //setColor(blue);
                fieldStats.decrementCount(this.getClass());
            }
            setNextState(tempState);
        } else {
            tempState = reproduction();
            if (tempState) {
                //setColor(red);
                fieldStats.incrementCount(this.getClass());
            }
            setNextState(tempState);
            if (currentTime - lastColorChangeTime >= COLOR_CHANGE_INTERVAL) {
                setColor(RandomColorGenerator.getRandomColor()); // Change color to a random color
                lastColorChangeTime = currentTime;
            }
        }
        
    }
    
    private boolean aliveCell(){
        //isolation- less than 2 neighbours
        if (neighbourCount < 2){
            return false;
        }
        //survival- 2 or 3 neighbours
        else if(neighbourCount == 2 || neighbourCount == 3){
            return true;
        }
        //overpopulation- more than 3 neighbours
        else{
            return false;
        }
    }
    
    private boolean reproduction(){
        //reproduction - 3 neighbours next to a dead cell means it will become alive
        if (neighbourCount == 3){
            return true;
        }
        else {
            return false;
        }
    }


}
