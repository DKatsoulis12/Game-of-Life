import javafx.scene.paint.Color;

/**
 * Write a description of class Oscillator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Oscillator extends Cell
{
    Simulator simulator;
    /**
     * Constructor for objects of class Oscillator
     */
    public Oscillator(Field field, Location location, Color col,Simulator simulator) {
        super(field, location, col);
        simulator = this.simulator;
    }


    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void act()
    {
        Location location = getLocation();
        int generation = simulator.getGeneration();
        if(generation % 4 == 0){
            
        }
        else if(generation % 4 == 1){
            
        }
        else if(generation % 4 == 2){
            
        }
        else if(generation % 4 == 3){
            
        }
        else{
            
        }
    }
    private void topCorner(){
        
    }
    
    private void bottomCorner(){
        
    }
    
    private void singleSquare(){
        
    }
}
