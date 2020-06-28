/**
 * @author yangshuai
 * @Description: 判断两个字符ASCII码是否相差1
 * @date 2020/6/28 9:31 上午
 */
public class OffByOne implements CharacterComparator {


    /**
     * 如果两个字符ASCII码相差1，返回true；否则返回false
     *
     * @param x
     * @param y
     * @return
     */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
