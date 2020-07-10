import lesson6.homework.HomeworkSix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class LessonSixTaskThree extends HomeworkSix {

    static Stream<Object> dataWithFourOrOne() {
        return Stream.of(new Object[]{
                new int[]{2, 4, 7, 8, 12, 4},
                new int[]{1},
                new int[]{4}
        });
    }

    @ParameterizedTest
    @MethodSource("dataWithFourOrOne")
    public void hasOneOrFourTest(int[] actualArray) {
        Assertions.assertTrue(hasOneOrFour(actualArray));
    }

    static Stream<Object> dataWithoutFourOrOne() {
        return Stream.of(new Object[]{
                new int[]{2, 7, 8, 12},
                new int[]{2},
                new int[]{}
        });
    }

    @ParameterizedTest
    @MethodSource("dataWithoutFourOrOne")
    public void hasOneOrFourTest2(int[] actualArray) {
        Assertions.assertFalse(hasOneOrFour(actualArray));
    }
}
