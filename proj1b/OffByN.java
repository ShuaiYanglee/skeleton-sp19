/**
 * @author yangshuai
 * @Description: 判断两个字符ASCII码是否相差N
 * @date 2020/6/28 10:12 上午
 */
public class OffByN implements CharacterComparator {

    /**
     * 相差步长
     */
    private int step;

    public OffByN(int step) {
        this.step = step;
    }

    /**
     * 判断两个字符ASCII码是否相差N
     *
     * @param x
     * @param y
     * @return
     */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == step;
    }
}
