package lesson4.homework;

/**
 * Task 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 */

public class Main {
    private volatile char current = 'A';
    private final Object a = new Object();

    public static void main(String[] args) {
        Main task1 = new Main();
        System.out.println("Task 1\n");
        new Thread(() -> task1.run('A', 'B')).start();
        new Thread(() -> task1.run('B', 'C')).start();
        new Thread(() -> task1.run('C', 'A')).start();
    }

    private void run(char currentLetter, char nextChar) {
        synchronized (a) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (current != currentLetter)
                        a.wait();
                    System.out.print(currentLetter);
                    current = nextChar;
                    a.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}