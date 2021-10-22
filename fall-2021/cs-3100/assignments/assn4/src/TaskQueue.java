import java.util.*;

public class TaskQueue {

    ArrayList<Integer> decimalOrder = new ArrayList<Integer>();
    LinkedList<Integer> queue = new LinkedList<Integer>();
    Integer processed = 0;

    public void buildQueue() {
        for (Integer i = 1; i <= 1000; i += 1) {
            decimalOrder.add(i);
        }

        java.util.Collections.shuffle(decimalOrder);

        for (Integer val : decimalOrder) {
            queue.add(val);
        }


    }

    public Integer getNext() {
        Integer current = queue.getFirst();
        queue.remove();
        return current;
    }

    public Integer getSize() {
        return queue.size();
    }

    public Integer getProcessed() {
        return processed;
    }

    public void increment() {
        processed += 1;
    }

}