package lesson4.p0;

public class TestRunMainThread {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        thread.setPriority(10);
        System.out.println(thread.getName());
        System.out.println(thread.isAlive());
        System.out.println(thread);
    }
}
