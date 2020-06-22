import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    Box(T... fruits) { this.fruits = new ArrayList<T>(Arrays.asList(fruits)); }

    //Add fruits into the box
    public void add(T... fruits) { this.fruits.addAll(Arrays.asList(fruits)); }

    //Remove fruits from the box
    public void remove(T... items) { for (T item: items) this.fruits.remove(item); }

    //Remove all fruits from the box
    private void clear() { fruits.clear(); }

    //Calc weight of the box
    private float getWeight() {
        if(fruits.size() == 0) return 0;
        float weight = 0;
        for (T fruit: fruits) { weight = weight+fruit.getWeight(); }
        return weight;
    }

    //Compare boxes
    boolean compare(Box box) { return this.getWeight() == box.getWeight(); }

    //Shift fruits from one box to another
    void shift(Box<? super T> box) {
        box.fruits.addAll(this.fruits);
        clear();
    }
}