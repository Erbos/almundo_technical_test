package domain.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Call{
    private int _id;
    private int duration;
    private static int MIN = 5;
    private static int MAX = 10;

    public Call(int _id) {
        Random r = new Random();
        this._id = _id;
        this.duration = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }

    public int get_id() {
        return _id;
    }

    public int getDuration() {
        return duration;
    }
}
