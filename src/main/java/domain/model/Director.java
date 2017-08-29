package domain.model;

public class Director extends Employee {

    /**
     * @param _id
     * The constructor of an Operator
     */
    public Director(int _id) {
        super(_id);
    }

    /**
     * @return
     */
    @Override
    public int getType() {
        return 3;
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Director");
        sb.append(_id);
        return sb.toString();
    }
}
