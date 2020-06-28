/**
 * @author yangshuai
 * @Description: TODO
 * @date 2020/6/24 4:49 下午
 */
public class Palindrome {

    /**
     * 将单词转换为字符队列
     *
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (Character c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    /**
     * 判断单词是否为回文
     *
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        while (deque.size() >= 2) {
            Character leftChar = deque.removeFirst();
            Character rightChar = deque.removeLast();
            if (!leftChar.equals(rightChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断单词是否为回文
     * 两个字符是否相等通过CharacterComparator.equalsChars方法进行控制
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        while (deque.size() >= 2) {
            Character leftChar = deque.removeFirst();
            Character rightChar = deque.removeLast();
            if (!cc.equalChars(leftChar, rightChar)) {
                return false;
            }
        }
        return true;
    }

}
