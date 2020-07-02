package lesson5.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicBoolean isWon;
    private CountDownLatch ready;
    private CountDownLatch finish;

    /**
     * CARS_COUNT - count participants
     */
    static {
        CARS_COUNT = 0;
        isWon = new AtomicBoolean(false);
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * @param race - racing strip
     * @param speed - speed
     * @param ready - ready counter
     * @param finish - finish counter
     */
    public Car(Race race, int speed, CountDownLatch ready, CountDownLatch finish) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Racer #" + CARS_COUNT;
        this.ready = ready;
        this.finish = finish;
    }

    @Override
    public void run() {

        /**
         * Getting ready
         */
        try {
            System.out.println(this.name + " is getting ready");
            Thread.sleep(500 + (int)(Math.random() * 800));//let everyone think that something serious is going on
            System.out.println(this.name + " is ready");

            /**
             * Waiting for start
             */
            ready.countDown();
            ready.await();

            /**
             * Start
             */
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            /**
             * Finish
             */
            finish.countDown();

            /**
             * Get winner
             */
            if (!isWon.getAndSet(true)) {
                System.out.println(this.name + " WIN!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}