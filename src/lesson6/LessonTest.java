package lesson6;

public class LessonTest {
    public static void main(String[] args) {
        int x = 12;
        x = x++;//x does get incremented. But you are assigning the old value of x back into itself.
        int y = x;
        if (y > 12) {
            System.out.println("Привет!, ");
        }
        if (x == 12) {
            //we will get this message
            System.out.println("И как ты сюда попал?");
        }
        if (x > 12 && y > 12) {
            System.out.println(" давай повторим!");
        }
    }
}
