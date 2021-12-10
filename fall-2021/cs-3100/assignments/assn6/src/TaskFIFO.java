import java.util.ArrayList;
import java.util.LinkedList;

public class TaskFIFO implements Runnable {
    private final ArrayList<Integer> seq;
    private final Integer maxFrames;
    private final ArrayList<Integer> faults;

    public TaskFIFO(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, ArrayList<Integer> pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
        
    }

    public ArrayList<Integer> getFaults() {
        return this.faults;
    }

    @Override
    public void run()
    {
        LinkedList<Integer> pageThreads = new LinkedList<Integer>();
        for (Integer item: this.seq) {
            // Check if item is in linked list
            if (!pageThreads.contains(item)) {
                this.faults.add(item);
                
                if (pageThreads.size() == this.maxFrames) {
                    pageThreads.pop();
                } 
                pageThreads.add(item);
            }

        }
    }

    
}
