import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue("x equals y", offByOne.equalChars('x', 'y'));
        assertTrue("r equals q", offByOne.equalChars('r', 'q'));
        assertTrue("& equals %", offByOne.equalChars('&', '%'));
        assertFalse("a not equals z", offByOne.equalChars('a', 'z'));
        assertFalse("x equals x", offByOne.equalChars('x', 'x'));
    }

}
