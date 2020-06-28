import org.junit.Test;

/**
 * @author yangshuai
 * @Description: 测试Project1A实现的数据结构的正确性
 * @date 2020/6/28 1:55 下午
 */
public class TestArrayDequeGold {
    
    @Test
    public void stdRandomTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            int randomNum = StdRandom.uniform(0, 4);
            int randomNum2 = StdRandom.uniform(100);
            switch (randomNum) {
                case 0:
                    sad.addFirst(randomNum2);
                    arrayDequeSolution.addFirst(randomNum2);
                    deque.addFirst(randomNum2);
                    break;
                case 1:
                    sad.addLast(randomNum2);
                    arrayDequeSolution.addLast(randomNum2);
                    deque.addLast(randomNum2);
                    break;
                case 2:
                    if (!sad.isEmpty() && !arrayDequeSolution.isEmpty() && !deque.isEmpty()) {
                        sad.removeFirst();
                        arrayDequeSolution.removeFirst();
                        deque.removeFirst();
                    }
                    break;
                case 3:
                    if (!sad.isEmpty() && !arrayDequeSolution.isEmpty() && !deque.isEmpty()) {
                        sad.removeLast();
                        arrayDequeSolution.removeLast();
                        deque.removeLast();
                    }
                    break;
            }
        }
        sad.printDeque();
        System.out.println(arrayDequeSolution.toString());
        deque.printDeque();
    }

}
