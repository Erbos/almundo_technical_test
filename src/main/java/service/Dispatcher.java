package service;

import domain.model.*;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Dispatcher{
    private int cantCalls = 0;
    private List<Employee> employees;
    private ExecutorService executor;

    public Dispatcher(List<Employee> employees) {
        this.employees = employees;
        executor = Executors.newFixedThreadPool(10);
    }

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

        /**caso de que no exista empleados disponibles o exeda el
         * número de llamdas disponibles
         */

        if(employee!=null && cantCalls<10) {
            callAttended = true;
            System.out.println("La llamada " + call.get_id() + " será atendida por el " + employee.getClass() + " " + employee.get_id());

            Callable<Integer> task = () ->{
                synchronized (employee) {
                    try {
                        cantCalls++;
                        employee.setBusy(true);
                        TimeUnit.SECONDS.sleep(call.getDuration());
                        employee.setBusy(false);
                        cantCalls--;
                    } catch (InterruptedException e) {
                        throw new IllegalStateException("task interrupted", e);
                    }
                    return employee.get_id();
                }
            };

            Future<Integer> future = executor.submit(task);
            /*executor.submit(() -> {

                    synchronized (employee) {
                        try {
                            cantCalls++;
                            employee.setBusy(true);
                            TimeUnit.SECONDS.sleep(call.getDuration());
                            employee.setBusy(false);
                            cantCalls--;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


            });*/

        }
        else
            callAttended = false;

        return callAttended;
    }
}
