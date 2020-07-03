import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import lesson6.testing.Calculator;

public class CalculatorTest {
    private static Calculator calc;

    @BeforeClass
    public static void init() {
        System.out.println("init calc");
        calc = new Calculator();
    }

    @Test
    public void justFail() {
        Assert.fail();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(6,calc.add(3, 3));
    }

    @Test
    public void testMistakeAdd() {
        Assert.assertNotEquals(4,calc.add(2, 0));
    }

    @Test(expected = ArithmeticException.class)
    public void testArithmeticException() {
        calc.div(10, 0);
    }

    @Test
    public void testDiv10By5() {

        Assert.assertEquals(calc.div(10, 5), 2);
    }

    @Ignore("Выключенный тест")
    @Test
    public void testEmpty() {
        Assert.assertEquals(calc.div(100, 5), 20);
        //Assert.fail();
    }

    @Test(timeout = 500)
    public void testTimeout() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDiv(){
      Assert.assertEquals(calc.div(100, 10), 10) ;// 10;
    }
}
