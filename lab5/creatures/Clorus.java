package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * @author yangshuai
 * @Description: A fierce blue-colored predator that enjoys nothing more than snacking on hapless Plips.
 * @date 2020/6/29 3:58 下午
 */
public class Clorus extends Creature {

    /**
     * the energy lose when Cloru move
     */
    private double MOVE_LOSE_ENERGY = 0.03D;

    /**
     * the energy lose when Cloru stay;
     */
    private double STAY_LOSE_ENERGY = 0.01D;

    public Clorus(double e) {
        super("clorus");
        this.energy = e;
        r = 34;
        g = 0;
        b = 231;
    }

    @Override
    public void move() {
        energy -= MOVE_LOSE_ENERGY;
        minEnergyCheck();
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy / 2;
        Clorus rep = new Clorus(energy);
        return rep;
    }

    @Override
    public void stay() {
        energy -= STAY_LOSE_ENERGY;
        minEnergyCheck();
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("plip")) {
                anyPlips = true;
                plipNeighbors.add(entry.getKey());

            } else if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            }
        }
        // Rule 1
        if (emptyNeighbors.size() == 0 && !anyPlips) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlips) {
            Direction attackDirection = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, attackDirection);
        }
        // Rule 3
        if (energy >= 1.0D) {
            Direction repDirection = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, repDirection);
        }
        // Rule 4
        Direction moveDirection = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, moveDirection);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }
}
