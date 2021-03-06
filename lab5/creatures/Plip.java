package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * the max energy that Plips can have.
     */
    private double MAX_ENERGY = 2.0D;

    /**
     * the energy gian when Plips stay
     */
    private double STAY_GAIN_ENERGY = 0.2D;

    /**
     * the energy lose when Plips move
     */
    private double MOVE_LOSE_ENERGY = 0.15D;


    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 63;
        b = 76;
        energy = e;
        maxEnergyCheck();
//        minEnergyCheck();
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    @Override
    public Color color() {
        g = (int) (63 + 96 * this.energy);
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        energy -= MOVE_LOSE_ENERGY;
//        minEnergyCheck();
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    @Override
    public void stay() {
        this.energy += STAY_GAIN_ENERGY;
        maxEnergyCheck();
    }

    /**
     * Plips should never have energy greater than 2.
     * If an action would cause the Plip to have energy greater than 2,
     * then it should be set to 2 instead.
     */
    private void maxEnergyCheck() {
        this.energy = this.energy > MAX_ENERGY ? MAX_ENERGY : this.energy;
    }


    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    @Override
    public Plip replicate() {
        energy = energy / 2;
        Plip p = new Plip(energy);
        return p;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("clorus")) {
                anyClorus = true;
            } else if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            }
        }

        // If no empty adjacent spaces, STAY
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1.0D) {
            Direction direction = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        // Rule 3
        if (anyClorus) {
            if (StdRandom.uniform() <= 0.5) {
                Direction direction = HugLifeUtils.randomEntry(emptyNeighbors);
                return new Action(Action.ActionType.MOVE, direction);
            }
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }

}
