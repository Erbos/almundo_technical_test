package service;

import domain.model.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * The Dispatcher class
 */
public class Dispatcher{
    private static final int CALL_CALLS = 10;
    private int callsCounter = 0;
    private final BlockingQueue<Employee> employees;
    private final ExecutorService line;

    /**
     * @param employees
     * Dispatcher constructor
     */
    public Dispatcher(List<Employee> employees) {
        this.employees = new PriorityBlockingQueue();
        this.employees.addAll(employees);
        line = Executors.newFixedThreadPool(CALL_CALLS);
    }


    /**
     * @param call
     * @return callAttended
     * The dispatch method,
     */
    public boolean dispatchCall (Call call){
        boolean callAttended;
        Employee employee = employees
                .stream()
                .filter(e -> e instanceof Operator && !e.isBusy())
                .findAny()
                .map(Optional::of).orElseGet(() -> employees.stream()
                        .filter(e -> e instanceof Supervisor && !e.isBusy())
                        .findAny())
                .map(Optional::of).orElseGet(() -> employees.stream()
                        .filter(e -> e instanceof Director && !e.isBusy())
                        .findAny())
                .orElse(null);

        //caso de que no exista empleados disponibles o exeda el
        if(employee!=null && callsCounter <10) {
            callAttended = true;
            System.out.println("La llamada " + call.get_id() + " será atendida por el " + employee.toString());
            line.submit(() -> {
                    synchronized (employee) {
                        try {
                            callsCounter++;
                            employee.setBusy(true);
                            TimeUnit.SECONDS.sleep(call.getDuration());
                            employee.setBusy(false);
                            callsCounter--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            });

        }
        else
            callAttended = false;

        return callAttended;
    }

    public void shutdownLine(){
        line.shutdown();
    }
}
