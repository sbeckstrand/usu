import java.util.PriorityQueue;
import java.util.Comparator;

/** Shortest Job First CPU Scheduler. This scheduling method prioritizes tasks with the shorted run time left but does not stop a running process, even if there is a new process with a shorter run time (non-preemptive). It simply runs one task at a time, and runs the task with the shorted burst time.   */
class SchedulerSJF extends Scheduler {
    
    int contextSwitches = 0;
    Platform platform;
    PriorityQueue<Process> processQueue = new PriorityQueue<Process>(1, new CompareSJFBurst());

    public SchedulerSJF(Platform p) {
        this.platform = p;
    }

    
    /** Update method used to return the process that should be running on CPU based on scheduler logic */
    public Process update(Process proc, int cpu) {
        if (proc == null) {
            try {
                platform.log("CPU " + cpu + " > Scheduled " + processQueue.element().getName());
                this.contextSwitches++;
                return processQueue.remove();
            } catch (Exception e) {
                return null; // Queue Empty
            }
        }
        
        // Check to see if execution has completed. If so, return that burst and execusion have completed. 
        if (proc.isExecutionComplete()) {
            this.contextSwitches++;
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");  
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " execution complete");
        }

        // If Execution has not completed, check if burst has completed. If so, add process back to queue. If not, return the process so that it can continue to run. 
        else {
            if (proc.isBurstComplete()) { 
                platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");
                this.contextSwitches++;
                processQueue.add(proc);
            } 
            else {
                return proc;
            }
        }

        // This block is kicked off if burst has completed. We try to return the next process in the queue, if one exists. 
        try {
            platform.log("CPU " + cpu + " > Scheduled " + processQueue.element().getName());
            this.contextSwitches++;
            return processQueue.remove();
        } catch (Exception e) {
            return null; // Queue Empty
        }
    }

    /** Method to get the number of context switches. Context Switch counter is being incremented anytime a new process is scheduled or when an already running process is returned to the queue. */
    public int getNumberOfContextSwitches() {
        return this.contextSwitches;
    }

    /** Method used to note that a new process has been added. When alerted, the process is added to the queue but in order of remaining burst time left. */
    public void notifyNewProcess(Process process) {
        processQueue.add(process);
    }

}

/** Comparator class used to compare which process has a shorter remaining burst time so that processed addd to our priority queue are added in order of remaining burst time.  */
class CompareSJFBurst implements Comparator<Process> {

    public int compare(Process p1, Process p2) {
        if (p1.getRemainingBurst() < p2.getRemainingBurst()) { 
            return -1;
        }
        else if (p1.getRemainingBurst()  > p2.getRemainingBurst()) { 
            return 1;
        }

        return 0;
    }
}

