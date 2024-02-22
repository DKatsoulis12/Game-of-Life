import javafx.scene.paint.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Toilet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Toilet extends Cell
{
    // instance variables - replace the example below with your own
    
    Simulator simulator;
    private int numOfMyco;
    private ArrayList<Cell> mycoNeighbours;
    /**
     * Constructor for objects of class Toilet
     */
    public Toilet(Field field, Location location, Color col,Simulator simulator) {
        super(field, location, col);
        this.simulator = simulator;
        numOfMyco = 0;
        mycoNeighbours = new ArrayList<>();
    }
    
    public void act(){
        int numGeneration = simulator.getGeneration();
        int totalGen = SimulatorView.numOfGenerations;
        mycoNeighbours = getMycoNeighbours();
        for(Cell cell: mycoNeighbours){
            System.out.println(cell.getLocation());
        }
        numOfMyco = mycoNeighbours.size();
        setNextState(true);
        if(numGeneration <= Math.floor(totalGen/3) ){
           kill(3);
           System.out.println("Phase 1");
        }
        else if(numGeneration <= 2 * (Math.floor(totalGen/3))){
            kill(2);
            System.out.println("Phase 2");
        }
        else{
            kill(1);
            System.out.println("Phase 3");
        }
    }
    private void kill(int num){
        if(numOfMyco < num){
            for(Cell myco : mycoNeighbours){
                //myco.setDead();
                myco.setNextState(false);
            }
        }
        else{
            for(int i = 0; i < num; i++){
                //mycoNeighbours.get(i).setDead();
                mycoNeighbours.get(i).setNextState(false);
            }
        }
    }
    
    private ArrayList<Cell> getMycoNeighbours(){
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        ArrayList<Cell> mycoNeighbours = new ArrayList<>();
        for( Cell cell : neighbours){
            if(cell instanceof Mycoplasma){
                mycoNeighbours.add(cell);
            }
        }
        return mycoNeighbours;
    }
}
