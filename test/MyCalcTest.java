import org.junit.*;
import org.junit.rules.Timeout;
import lesson6.testing.Calculator;
import lesson6.testing.MyCalc;
import static java.lang.Thread.sleep;

public class MyCalcTest {
    private static MyCalc mycalc;

    @BeforeClass
    public static void initTest() {
        mycalc = new MyCalc();
        System.out.println("init suite");
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(7, mycalc.add(3, 4));
    }

    @Test
    public void testAdd02() {
        Assert.assertEquals(6, mycalc.add(2, 4));
    }

    @Test
    public void testAdd03() {
        Assert.assertEquals(7, mycalc.add(3, 4));
    }

    @Test
    public void testAdd04() {
        Assert.assertEquals(7, mycalc.add(3, 4));
    }

    @Test
    public void testAdd2() {
        Assert.assertEquals(0, mycalc.add(0, 0));
    }

    @Test
    public void testSub() {
        Assert.assertEquals(6, mycalc.sub(9, 3));
    }

    @Test
    public void testDiv() {
        Assert.assertEquals(3, mycalc.div(9, 3));
    }

    @Test
    public void testMul() {
        Assert.assertEquals(28, mycalc.mul(9, 3));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivBy0() {
        mycalc.div(10, 0);
    }

    @Rule
    public final Timeout timeout = new Timeout(1000);

    @Test(timeout=1000)
    public void testVeryFastMethod() {
        mycalc.add(10, 10);
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
