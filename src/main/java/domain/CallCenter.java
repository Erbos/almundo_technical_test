package domain;

import domain.model.Call;
import domain.model.Director;
import domain.model.Operator;
import domain.model.Supervisor;
import service.Dispatcher;

import java.util.Arrays;
import java.util.LinkedList;


public class CallCenter {

    private Dispatcher dispatcher;
    private LinkedList<Call> unAttendedCalls = new LinkedList<>();
    public void incomingCalls(){

        setUpTheDispatcher();
        Call call;
        for(int i=0; i<10; i++){
            call = new Call(i);
            if (dispatcher.dispatchCall(call)) {
                System.out.println("Gracias por usar nuestros servicios");
            }
            else
                unAttendedCalls.addLast(call);
        }
        /** caso de que hayan llegado llamadas y no hayan sido atendidas
         * bien porque no había empleados disponibles o porque el dispatcher estaba lleno
         */
        while(!unAttendedCalls.isEmpty()){
            dispatcher.dispatchCall(unAttendedCalls.pop());
        }
    }

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
