import java.util.PriorityQueue;
import java.util.Comparator;

/** Shorted Remaining Time First CPU Scheduling. This scheduling method prioritizes tasks with the shorted run time left.  This method is preemptive in that if there is a job with a shorter remaining time, a running process is stopped to make way for the new process. */
class SchedulerSRTF extends Scheduler {
    
    int contextSwitches = 0;
    Platform platform;
    PriorityQueue<Process> processQueue = new PriorityQueue<Process>(1, new CompareSRTFBurst());

    public SchedulerSRTF(Platform p) {
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
        
        // Check if Execution has completed. If so, return that the burst and execusion have completed and then return the next process in the queue if it exists. 
        if (proc.isExecutionComplete()) {
            this.contextSwitches++;
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");  
            platform.log("CPU " + cpu + " > Process " + proc.getName() + " execution complete");

            try {
                platform.log("CPU " + cpu + " > Scheduled " + processQueue.element().getName());
                this.contextSwitches++;
                return processQueue.remove();
            } catch (Exception e) {
                return null; // Queue Empty
            }

        // If execution has not completed, check if burase has completed. If so, return process to back of queue and try starting the next process in queue if it exists. 
        } else {
            if (proc.isBurstComplete()) { 
                platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");
                this.contextSwitches++;
                processQueue.add(proc);
                try {
                    platform.log("CPU " + cpu + " > Scheduled " + processQueue.element().getName());
                    this.contextSwitches++;
                    return processQueue.remove();
                } catch (Exception e) {
                    return null; // Queue Empty
                }
            }

            // If burst is not complete, check if there is another process with a shorter remaining time, if so move current process to back of queue and return process with shorter remaning time. 
            else {
                try {
                    if (proc.getRemainingBurst() > processQueue.element().getRemainingBurst()) {
                        processQueue.add(proc);
                        platform.log("CPU " + cpu + " > Preemptively removed: " + proc.getName());
                        this.contextSwitches++;
                        platform.log("CPU " + cpu + " > Scheduled " + processQueue.element().getName());
                        this.contextSwitches++;
                        return processQueue.remove();
                    }
                } catch (Exception e) {
                    return proc;
                }
            }
        }

        return proc;
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
class CompareSRTFBurst implements Comparator<Process> {

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