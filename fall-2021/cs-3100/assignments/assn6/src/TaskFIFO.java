import java.util.ArrayList;
import java.util.LinkedList;

public class TaskFIFO implements Runnable {
    private final ArrayList<Integer> seq;
    private final Integer maxFrames;
    private final ArrayList<ArrayList<Integer>> faults;


    public TaskFIFO(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, ArrayList<ArrayList<Integer>> pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
        
    }

    @Override
    public void run()
    {
        LinkedList<Integer> pageThreads = new LinkedList<Integer>();
        ArrayList<Integer> currFaults = new ArrayList<Integer>();

        for (Integer i = 0; i < this.seq.size(); i++) {
            // Check if item is in linked list
            Integer item = this.seq.get(i);
            if (!pageThreads.contains(item)) {
                currFaults.add(item);
                
                if (pageThreads.size() == this.maxFrames) {
                    pageThreads.pop();
                } 
                pageThreads.add(item);
            }

        }

        faults.set(maxFrames - 1, currFaults);
    }

    
}
