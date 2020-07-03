package lesson5.homework.newVer;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c, int stagePos, int stageCount, long startTime);
}
