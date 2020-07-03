package lesson6.testing;

public class Calculator
{
  int x; int y;

  public Calculator(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Calculator() {
  }

  public int add(int a, int b)
  {
    return a + b;
  }

  public int div(int a, int b)
  {

    return a / b;
  }

  public int hardDiv(int a, int b, int z, int q, String m)
  {
    int i = Integer.valueOf(m);
    return (div(a,b) + div(z,q))/i;
  }

  public Boolean bool(int x, int y)
  {
    if (x / y == 1)
    {
      return true;
    }
    return false;
  }
}
