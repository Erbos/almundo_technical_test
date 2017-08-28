package domain;

import domain.model.Call;
import domain.model.Director;
import domain.model.Operator;
import domain.model.Supervisor;
import org.junit.Test;
import service.Dispatcher;

import java.util.Arrays;
import java.util.LinkedList;


public class CallCenterTest {

    private Dispatcher dispatcher;
    private LinkedList<Call> unAttendedCalls = new LinkedList<>();

    /**
     * @throws Exception
     * Caso de prueba que crea 10 trabajadores y 10 llamdas concurrentes.
     */
    @Test
    public void incomingTenCalls() throws Exception {
        setUpDispatcherTen();
        generateCalls(10);
    }

    /**
     * @throws Exception
     * Caso de prueba que crea 3 trabajadores y 50 llamdas concurrentes.
     */
    @Test
    public void incomingMoreThanTenCalls() throws  Exception {
        setUpDispatcherLessTen();
        generateCalls(50);
    }

    private void generateCalls(int cantCalls) {

        Call call;
        for(int i=0; i<cantCalls; i++){
            call = new Call(i);
            if (dispatcher.dispatchCall(call)) {
                System.out.println("Gracias por usar nuestros servicios");
            }
            else {
                System.out.println("Lamada " + call.get_id() + " en espera");
                unAttendedCalls.addLast(call);
            }
        }
        /** en caso de que hayan llegado llamadas y no hayan sido atendidas
         * bien porque no había empleados disponibles o porque el dispatcher estaba lleno
         */
        while(!unAttendedCalls.isEmpty()){
            call = unAttendedCalls.pop();
            dispatcher.dispatchCall(call);
            System.out.println("Lamada " + call.get_id() + " eliminada de la cola de espera");
        }
    }


    /**
     * Genera 10 trabajadores 4 Operadores, 4 supervisores y 2 Directores
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
     * Genera 3 trabajadores 1 Operadores, 1 supervisores y 1 Directores
     */
    private void setUpDispatcherLessTen(){
        dispatcher = new Dispatcher(Arrays.asList(
                new Operator(1),
                new Supervisor(2),
                new Director(5)
        ));
    }



}