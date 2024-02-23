import javafx.scene.paint.Color;
import java.util.List;

/**
 * Write a description of class AdjustiveMycoplasma here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdjustiveMycoplasma extends Mycoplasma
{
    public static int reproductionRate;
    /**
     * Constructor for objects of class AdjustiveMycoplasma
     */
    public AdjustiveMycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
        willChange = false;
        reproductionRate = 3;
    }

    public void act(){
        int populationCount = fieldStats.getCellCount(this.getClass());
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        countLivingNeighbours(neighbours);
        if(populationCount <= 10){
            //underpopulation- rapid reproduction
            reproductionRate = 2;
        }
        else if(populationCount <= 15){
            //normal population - normal reproduction
            reproductionRate = 3;
        }
        else{
            //overpopulation - slow reproduction
            reproductionRate = 4;
        }
        nextAlive = aliveCell(2,3);
        if(!nextAlive){
            willChange = true;
        }
    }
    
    public void updateState() {
        if(willChange == true){
            Mycoplasma myco = new Mycoplasma(getField(), getLocation(), Color.ORANGE);
            myco.setDead();
            getField().place(myco, getLocation());
        }
        alive = nextAlive;
    }
}
