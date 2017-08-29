package domain.model;

/**
 * Abstract class that represent an Employee
 */
public abstract class Employee implements Comparable<Employee>{
    protected int _id;
    protected boolean isBusy;
    protected int type;

    /**
     * @param _id
     * Employee constructor
     */
    Employee(int _id) {
        this._id = _id;
    }

    /**
     * employees
     * @return
     * Abstract method that probide the priority of the
     */
    public abstract int getType();

    /**
     * @param employee
     * @return
     * An override methode to compare an employee with another
     */
    @Override
    public int compareTo(Employee employee) {
        return Integer.compare(type, employee.getType());
    }


    /**
     * Getters and Setters
     */
    public int get_id() {
        return _id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
