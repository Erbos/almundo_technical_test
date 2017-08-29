package domain;

import domain.model.Call;
import domain.model.Director;
import domain.model.Operator;
import domain.model.Supervisor;
import service.Dispatcher;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


/**
 * This class is unnecesarry, but in the order of the problem
 * it appears like a concept
 *
 */
public class CallCenter {

    private Dispatcher dispatcher;
    private LinkedList<Call> unAttendedCalls = new LinkedList<>();

    /**
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * This method simulate the incoming calls and their dispatch
     */
    public void incomingCalls() throws InterruptedException, ExecutionException, TimeoutException {

        Call call;
        for(int i=0; i<10; i++){
            call = new Call(i);
            if(dispatcher.dispatchCall(call))
                System.out.println("La llamda " +call.get_id() +
                        " ha finalizado con éxito");
            else
                unAttendedCalls.addLast(call);
        }
        while(!unAttendedCalls.isEmpty()){
            call = unAttendedCalls.pop();
            dispatcher.dispatchCall(call);
        }

        dispatcher.shutdownLine();
    }

    /**
     * This method generate a collection of employees
     */
    private void setUpTheDispatcher(){
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

}
