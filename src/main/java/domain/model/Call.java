package domain.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The call
 */
public class Call{
    private int _id;
    private int duration;
    private static int MIN = 5;
    private static int MAX = 10;

    /**Call Constructor
     * @param _id
     */
    public Call(int _id) {
        Random r = new Random();
        this._id = _id;
        this.duration = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
    }

    /**
     * Getter and Setters
     */
    public int get_id() {
        return _id;
    }

    public int getDuration() {
        return duration;
    }
}
