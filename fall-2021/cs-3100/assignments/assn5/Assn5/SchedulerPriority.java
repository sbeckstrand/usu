import java.util.PriorityQueue;
import java.util.Comparator;

/** Priority CPU Scheduler. This scheulding method accepts a priority as input for each process and schedules them based on priority. This particular impelementation is non-premptive.  */
class SchedulerPriority extends Scheduler {
    
    int contextSwitches = 0;
    Platform platform;
    PriorityQueue<Process> processQueue = new PriorityQueue<Process>(1, new ComparePriorityBurst());

    public SchedulerPriority(Platform p) {
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
        
        // Check if execution has completed. If so return that burst and execution have completed.
        if (proc.isExecutionComplete()) {
            this.contextSwitches++;
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");  
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " execution complete");
        } 
        
        // If execution has not completed. Check if burst has completed. If so, add process back to queue, if not, continue to run.
        else {
            if (proc.isBurstComplete()) { 
                platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");
                this.contextSwitches++;
                processQueue.add(proc);
            } else {
                return proc;
            }
        }

        // If burst or execution has completed, return the next process in the queue, if it exists. 
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

    /** Method used to note that a new process has been added. When alerted, the process is added to the queue but in order of provided priority. */
    public void notifyNewProcess(Process process) {
        processQueue.add(process);
    }

}

/** Comparator class used to compare which process has a higher priority so that processes added to our priority queue are added in order of remaining burst time.  */
class ComparePriorityBurst implements Comparator<Process> {

    public int compare(Process p1, Process p2) {
        if (p1.getPriority() < p2.getPriority()) { 
            return -1;
        }
        else if (p1.getPriority()  > p2.getPriority()) { 
            return 1;
        }

        return 0;
    }
}