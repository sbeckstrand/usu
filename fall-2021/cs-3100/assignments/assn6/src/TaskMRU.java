import java.util.ArrayList;
import java.util.LinkedList;

public class TaskMRU implements Runnable {
    private final ArrayList<Integer> seq;
    private final Integer maxFrames;
    private final int[] faults;
    
    public TaskMRU(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
    }

    public void run()
    {
        LinkedList<Integer> frames = new LinkedList<Integer>();
        int currFaults = 0;

        for (Integer item: this.seq) {
            
            if (!frames.contains(item)) {
                currFaults++;

                if (frames.size() == maxFrames) {
                    frames.pop();
                } 
                frames.push(item);
            } else {
                frames.remove(frames.indexOf(item));
                frames.push(item);
            }
        }

        faults[maxFrames - 1] = currFaults;
    }
}
