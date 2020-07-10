package lesson5.homework.newVer;

import lesson5.homework.newVer.Stage;

public class Road extends Stage {

    private long finishTime;
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c, int stagePos, final int stageCount, final long startTime) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            finishTime = System.currentTimeMillis() - startTime;
            System.out.println(c.getName() + " закончил этап: " + description + ", время от старта секунд = " + ((float)(finishTime) / 1000));
            if (stagePos == stageCount && MainClass.firstFinish) {
                MainClass.firstFinish = false;
                System.out.println(c.getName() + " WIN!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
