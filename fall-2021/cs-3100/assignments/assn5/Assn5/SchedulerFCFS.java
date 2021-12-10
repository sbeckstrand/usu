import java.util.LinkedList;
import java.util.Queue;

/** First Come, First Serve CPU Scheduler. This scheduling algorithm uses a queue and handles processes in the order they are added to the schedule. Sometimes referred to 'First In, First Out' (FIFO) */
class SchedulerFCFS extends Scheduler {
    
    int contextSwitches = 0;
    Platform platform;
    Queue<Process> processQueue = new LinkedList<>();

    public SchedulerFCFS(Platform p) {
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

    /** This method is used to be notified when a new Process is added to the scheduler. When added, append to back of queue.  */
    public void notifyNewProcess(Process process) {
        processQueue.add(process);
    }
}