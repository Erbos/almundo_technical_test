package domain.model;

public abstract class Employee {
    protected int _id;
    protected boolean isBusy;

    Employee(int _id) {
        this._id = _id;
        this.isBusy = false;
    }

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
