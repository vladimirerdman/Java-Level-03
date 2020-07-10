import lesson6.homework.HomeworkSix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class LessonSixTaskTwo extends HomeworkSix {

    static Stream<Object[]> dataWithoutExceptions() {
        return Stream.of(new Object[][]{
                {new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}},
                {new int[]{15, 244, 53, 0, 17, 17}, new int[]{4, 15, 244, 53, 0, 17, 17}},
                {new int[]{}, new int[]{9, 4, 4, 11, 23, 4}}
        });
    }

    @ParameterizedTest
    @MethodSource("dataWithoutExceptions")
    public void afterFourTest(int[] expectedArray, int[] actualArray) {
        Assertions.assertArrayEquals(expectedArray, afterFour(actualArray));
    }

    static Stream<Object> dataWithExceptions() {
        return Stream.of(new Object[]{
                new int[]{},
                new int[]{1, 2, 3, 5, 6, 7}
        });
    }

    @ParameterizedTest
    @MethodSource("dataWithExceptions")
    public void afterFourWithoutFourTest(int[] actualArray) {
        Assertions.assertThrows(RuntimeException.class, () -> {
            afterFour(actualArray);
        });
    }
}
