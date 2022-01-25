import java.util.ArrayList;
import java.util.LinkedList;

public class TaskFIFO implements Runnable {
    private final int[] seq;
    private final Integer maxFrames;
    private final int[] faults;


    public TaskFIFO(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
        
    }

    @Override
    public void run()
    {
        LinkedList<Integer> pageThreads = new LinkedList<Integer>();
        int currFaults = 0;

        for (int item: this.seq) {

            if (!pageThreads.contains(item)) {
                currFaults++;
                
                if (pageThreads.size() == this.maxFrames) {
                    pageThreads.pop();
                } 
                pageThreads.add(item);
            }

        }

        faults[maxFrames - 1] = currFaults;
    }

    
}
