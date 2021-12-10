import java.util.ArrayList;
import java.util.LinkedList;

public class TaskLRU implements Runnable {
    private final ArrayList<Integer> seq;
    private final Integer maxFrames;
    private final ArrayList<Integer> faults;
    
    public TaskLRU(ArrayList<Integer> sequence, int maxMemoryFrames, int maxPageReference, ArrayList<Integer> pageFaults) {
        this.seq = sequence;
        this.maxFrames = maxMemoryFrames;
        this.faults = pageFaults;
    }

    public void run()
    {
        ArrayList<Integer> pageThreads = new ArrayList<Integer>();
        LinkedList<Integer> allPages = new LinkedList<Integer>();

        for (Integer item: this.seq) {
            if (!allPages.contains(item)) {
                allPages.add(item);
            } else {
                allPages.removeIf(page -> page.equals(item));
                allPages.add(item);
            }

            if (!pageThreads.contains(item)) {
                this.faults.add(item);

                if (pageThreads.size() == this.maxFrames) {
                    Integer zeroIndex = allPages.indexOf(pageThreads.get(0));
                    Integer oneIndex = allPages.indexOf(pageThreads.get(1));
                    Integer twoIndex = allPages.indexOf(pageThreads.get(2));

                    if (zeroIndex < oneIndex) {
                        if (twoIndex < zeroIndex) {
                            pageThreads.set(2, item);
                        } else {
                            pageThreads.set(0, item);
                        }
                    } else {
                        if (oneIndex < twoIndex) {
                            pageThreads.set(1, item);
                        } else {
                            pageThreads.set(2, item);
                        }
                    }
                } else {
                    pageThreads.add(item);
                }
            }

        }
    }
}
