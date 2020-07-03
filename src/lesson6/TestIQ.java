package lesson6;

public class TestIQ {
    public static void main(String[] args) {
        int x = 12;
        x = x++;
        int y = x;
        if(y>12){
            System.out.println("Привет!, ");
        }
        if(x==12) {
            System.out.println(" и как ты сюда попал?!");
        }
        if (x>12 && y >12) {
            System.out.println(" давай повторим!");
        }
    }
}
