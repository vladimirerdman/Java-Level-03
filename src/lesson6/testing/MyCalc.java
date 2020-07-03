package lesson6.testing;

public class MyCalc {
    public int add(int x1, int x2) {
        return x1 + x2;
    }

    public int sub(int x1, int x2) {
        return x1 - x2;
    }

    public int mul(int x1, int x2) {
        int i = 10/0;
        return x1 * x2;
    }

    public int div(int x1, int x2) {
        return x1 / x2;
    }
}
