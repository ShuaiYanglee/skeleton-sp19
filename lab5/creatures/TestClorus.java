package creatures;

import huglife.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author yangshuai
 * @Description: Test the Clorus class
 * @date 2020/6/30 2:17 下午
 */
public class TestClorus {

    @Test
    public void testBasics() {
        Clorus clorus = new Clorus(2);
        assertEquals(2, clorus.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), clorus.color());
        clorus.move();
        assertEquals(1.97, clorus.energy(), 0.01);
        clorus.move();
        assertEquals(1.94, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(1.93, clorus.energy(), 0.01);
        clorus.stay();
        assertEquals(1.92, clorus.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus clorus = new Clorus(3);
        Clorus rep = clorus.replicate();
        Assert.assertNotEquals("p should not equal to rep", clorus, rep);
        assertEquals(1.50, rep.energy(), 0.01);
        assertEquals(1.50, clorus.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; recloruslicate towards an empty space.
        clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = clorus.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; move.
        clorus = new Clorus(.99);

        actual = clorus.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        clorus = new Clorus(.99);

        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE);

        assertEquals(expected, actual);


        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}
