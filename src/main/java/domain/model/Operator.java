package domain.model;

/**
 * The class Operator
 */
public class Operator extends Employee {

    /**
     * @param _id
     * The constructor of an Operator
     */
    public Operator(int _id) {
        super(_id);
    }

    /**
     * employees
     * @return
     * Abstract method that probide the priority of the
     */
    @Override
    public int getType() {
        return 1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Operator");
        sb.append(_id);
        return sb.toString();
    }
}
