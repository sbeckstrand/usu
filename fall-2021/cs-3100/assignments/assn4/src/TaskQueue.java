import java.util.*;

public class TaskQueue {

    ArrayList<Integer> decimalOrder = new ArrayList<Integer>();
    LinkedList<Integer> queue = new LinkedList<Integer>();
    Integer processed = 0;

    /* Build initial queue structor */
    public void buildQueue() {
        for (Integer i = 1; i <= 1000; i += 1) {
            decimalOrder.add(i);
        }

        java.util.Collections.shuffle(decimalOrder);

        for (Integer val : decimalOrder) {
            queue.add(val);
        }


    }

    /* Get next item in queue */
    public Integer getNext() {
        Integer current = queue.getFirst();
        queue.remove();
        return current;
    }

    /* Get size of queue */
    public Integer getSize() {
        return queue.size();
    }

    /* Find how many queue items (decimal places) have been processed */
    public Integer getProcessed() {
        return processed;
    }

    /* Increment the number of processed queue items */
    public void increment() {
        processed += 1;
    }

}