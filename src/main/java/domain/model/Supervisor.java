package domain.model;

public class Supervisor extends Employee {
    public Supervisor(int _id) {
        super(_id);
    }

    /**
     * employees
     * @return
     * Abstract method that probide the priority of the
     */
    @Override
    public int getType() {
        return 2;
    }

    /**
     * @return
     * Override methode that provide a way to generate a String
     * of the object
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Supervisor");
        sb.append(_id);
        return sb.toString();
    }
}
