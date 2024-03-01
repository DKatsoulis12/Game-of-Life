import javafx.scene.paint.Color;
import java.util.List;
import java.util.Random;

/**
 * The non-determinist cell has two different behaviour sets, which it exhibits based on a random probability.
 * If the value randomly generated is below 0.38 the first set of values for aliveCell() and reproduction() are used
 * Otherewise, the second set of values are used
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
 * All of the bahaviour is determined in this single method. Every other method is inherited from Mycoplasma, but the parameters on the method are altered depending on the randomly generated double value
 * The print statement is useful for testing purposes, as it displays the randomly generated value and we can determine which set of behaviours the current NonDeterministic object will display
   */
    public void act() {
    List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
    neighbourCount = neighbours.size();
    fieldStats = SimulatorView.getFieldStats();

    Random rand = Randomizer.getRandom();
    double rng = rand.nextDouble();
    //System.out.println("Cell at " + getLocation() + " has rng value: " + rng);
    
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
