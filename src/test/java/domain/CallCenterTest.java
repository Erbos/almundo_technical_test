package domain;

import domain.model.Call;
import domain.model.Director;
import domain.model.Operator;
import domain.model.Supervisor;
import org.junit.Test;
import service.Dispatcher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


/**
 *
 */
public class CallCenterTest {

    private Dispatcher dispatcher;
    private LinkedList<Call> unAttendedCalls = new LinkedList<>();

    /**
     * @throws Exception
     * Test method that simulate 10 incoming calls simultaneously and generate
     * a set of 10 Employees
     */
    @Test
    public void incomingTenCalls() throws Exception {
        setUpDispatcherTen();
        generateCalls(10);
    }

    /**
     * @throws Exception
     * Test method that simulate 50 incoming calls simultaneously and generate
     * a set of 10 Employees
     */
    @Test
    public void incomingMoreThanTenCalls() throws  Exception {
        setUpDispatcherLessTen();
        generateCalls(50);
    }

    /**
     * @param cantCalls
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * Genera un numero de llamadas pasadas por parámetros
     */
    private void generateCalls(int cantCalls) throws InterruptedException, ExecutionException, TimeoutException {

        Call call;
        for(int i=0; i<cantCalls; i++){
            call = new Call(i);
            if(dispatcher.dispatchCall(call))
                System.out.println("La llamda " +call.get_id() +
                        " ha finalizado con exito");
            else {
                unAttendedCalls.addLast(call);
                System.out.println("La llamda " + call.get_id() + " fue puesta en espera " +
                        "para ser atendida");
            }
        }
        while(!unAttendedCalls.isEmpty()){
            call = unAttendedCalls.pop();
            dispatcher.dispatchCall(call);
            System.out.println("La llamda " +call.get_id() +
                    " ha finalizado con exito");
        }

        dispatcher.shutdownLine();
    }


    /**
     * Generate 10 employees 4 Operators, 4 Supervisors y 2 Directors
     */
    private void setUpDispatcherTen(){
        dispatcher = new Dispatcher(Arrays.asList(
                new Operator(1),
                new Supervisor(2),
                new Operator(3),
                new Supervisor(4),
                new Director(5),
                new Supervisor(6),
                new Operator(7),
                new Director(8),
                new Supervisor(9),
                new Operator(10)
                ));
    }

    /**
     * Generate 10 employees 4 Operators, 8 Supervisors y 8 Directors
     */
    private void setUpDispatcherLessTen(){
        dispatcher = new Dispatcher(Arrays.asList(
                new Operator(1),
                new Supervisor(2),
                new Director(3),
                new Supervisor(4),
                new Director(5),
                new Supervisor(6),
                new Operator(7),
                new Director(8),
                new Supervisor(9),
                new Operator(10),
                new Operator(11),
                new Supervisor(12),
                new Director(13),
                new Supervisor(14),
                new Director(15),
                new Supervisor(16),
                new Director(17),
                new Director(18),
                new Supervisor(19),
                new Director(20)
        ));
    }



}