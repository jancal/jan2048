import java.util.Random;

/**
 * RandomTest
 *
 * @author Jancal(Zhang Hao) 2017/4/27
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random(10);
        for (int i = 0; i < 16; i++) {
            System.out.println(random.nextInt(8));
        }
    }
}
