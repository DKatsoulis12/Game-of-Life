import javafx.scene.paint.Color;
import java.util.List;
import java.util.Random;

/**
 * The non-determinist cell has two different behaviour sets, which it exhibits based on a random
 * @author Dimitrios Katsoulis (23051886), Siddhant Mohapatra (23007046)
 * @version 2022.01.06
 */
public class NonDeterministic extends Mycoplasma
{
    
public NonDeterministic(Field field, Location location, Color alivecol, Color deadcol) {
    super(field, location, alivecol, deadcol);
    this.isDiseased = false;
    this.nextDiseased = false;
    this.diseaseAbility = true;
}

    /**
    * The non-deterministic cell 
    */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    neighbourCount = neighbours.size();
    fieldStats = SimulatorView.getFieldStats();

    Random rand = Randomizer.getRandom();
    double rng = rand.nextDouble();
    System.out.println("Cell at " + getLocation() + " has rng value: " + rng);
    
    determineDisease();
    
    if (isDiseased) {
            actDiseased();
    } else {
        if (rng <= 0.38) {
            if (isAlive()) {
                tempState = aliveCell(3,4);
            } else {
                 tempState = reproduction(4);
            }
        } else {
            if (isAlive()) {
                tempState = aliveCell(2,5);
            } else {
                tempState = reproduction(2);
            }
    }
    setNextState(tempState);
    }
    
}

}
