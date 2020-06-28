import org.junit.Assert;
import org.junit.Test;

/**
 * @author yangshuai
 * @Description:
 * @date 2020/6/28 10:40 上午
 */

public class TestOffByN {

    @Test
    public void testEqualCharsWithOffByN() {
        OffByN offBy5 = new OffByN(5);
        Assert.assertTrue("a equals f with offBy5", offBy5.equalChars('a', 'f'));
    }
}
