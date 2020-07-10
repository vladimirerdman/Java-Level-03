package lesson6.homework;

import java.util.Arrays;

public class HomeworkSix {

    /**
     * Task 2
     * @param array
     * @return
     */
    public int[] afterFour(int[] array) {
        int[] temp = new int[array.length];
        System.arraycopy(array, 0, temp,0,array.length);
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] == 4) {
                return Arrays.copyOfRange(temp, i + 1, temp.length);
            }
        }
        throw new RuntimeException("Array doesn't include value == 4");
    }

    /**
     * Task 3
     * @param array
     * @return
     */
    public boolean hasOneOrFour(int[] array) {
        for(int value : array) {
            if (value == 4 || value == 1) return true;
        }
        return false;
    }
}
