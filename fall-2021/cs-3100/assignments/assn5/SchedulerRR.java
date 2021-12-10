import java.util.LinkedList;
import java.util.Queue;

/** Round Robin CPU Scheduler. This scheduling method takes a quantum time and gives each process that amount of time to run before switching to the next process.  */
class SchedulerRR extends Scheduler {
    
    int contextSwitches = 0;
    Platform platform;
    int cycles = -1;
    int quantum = 0;
    Queue<Process> processQueue = new LinkedList<>();

    public SchedulerRR(Platform p, int q) {
        this.platform = p;
        this.quantum = q;
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
        
        // If execution has not completed, check if burst has. If no, add the process to the back of the queue. If not, check if the elapsed burst time has reached the quantum time. If so, return it to the back of the queue. If neither of these conditions are met, keep running. 
        else {
            if (proc.isBurstComplete()) { 
                platform.log("CPU " + cpu + " > Process " + proc.getName() + " burst complete");
                this.contextSwitches++;
                processQueue.add(proc);
            } else {
                if (proc.getElapsedBurst() % quantum == 0) {
                    platform.log("CPU " + cpu + " > Time quantum complete for process " + proc.getName());
                    this.contextSwitches++;
                    processQueue.add(proc);
                } else {
                    return proc;
                }
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