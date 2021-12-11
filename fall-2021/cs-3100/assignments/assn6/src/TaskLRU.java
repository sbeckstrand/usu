import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TaskLRU implements Runnable {
    private final ArrayList<Integer> seq;
    private final Integer maxFrames;
    private final ArrayList<ArrayList<Integer>> faults;


    
    public TaskLRU(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, ArrayList<ArrayList<Integer>> pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
    }

    public void run()
    {
        LinkedList<Integer> frames = new LinkedList<Integer>();
        ArrayList<Integer> currFaults = new ArrayList<Integer>();

        for (Integer i = 0; i < this.seq.size(); i++) {
            
            Integer item = this.seq.get(i);
            
            if (!frames.contains(item)) {
                currFaults.add(item);

                if (frames.size() == maxFrames) {
                    frames.pop();
                } 
                frames.add(item);
            } else {
                frames.remove(frames.indexOf(item));
                frames.add(item);
            }
        }

        faults.set(maxFrames - 1, currFaults);
    }
}
