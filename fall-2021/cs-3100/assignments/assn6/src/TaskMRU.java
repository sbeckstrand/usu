import java.util.ArrayList;

public class TaskMRU implements Runnable {
    
    public TaskMRU(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, ArrayList<Integer> pageFaults) {
        System.out.println("MRU");
    }

    public void run()
    {
        System.out.println("MRU");
    }
}
