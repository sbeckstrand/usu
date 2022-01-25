/**
* Class: 			CS-3100
* Description: 		Assignment 6. Page Replacement
* @author			Stephen Beckstrand
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Assign6 {

    /** main function, used to kick off the simulation function.
            Input: 
            - none

            Output:
            - none
    */
    public static void main(String[] args) {
        simulate();
    }

    /** simulate function, used to iterate through 100,000 simulations where we range from 1-100 thread counts 1000 times and use the FIFO, LRU, and MRU methods to count page Faults with a generated array of numbers.
            Input: 
            - none

            Output:
            - none
    */
    public static void simulate() {
        long startTime = System.nanoTime();

        // Create a thread pool with as many workers as there are processors available on your system. 
        Integer cores = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(cores);

        int[][] FIFOFaults = new int[1000][100];
        int[][] LRUFaults = new int[1000][100];
        int[][] MRUFaults = new int[1000][100];

        // Simulation loop
        for (Integer simulation = 0; simulation < 1000; simulation++) {

            // Generate sequence of integers between [1,250] 
            int[] sequence = new int[1000];
            

            for (Integer randomRange  = 0; randomRange < 1000; randomRange++) {
                Integer randomNumber = ThreadLocalRandom.current().nextInt(1,251);
                sequence[randomRange] = randomNumber;
            }

            // For each max Thread count from [1,100], create tasks for each algorithm and add them to the thread pool
            for (Integer frames = 1; frames <= 100; frames++) {

                Runnable fifo = new TaskFIFO(sequence, frames, 250, FIFOFaults[simulation] );
                Runnable lru = new TaskLRU(sequence, frames, 250, LRUFaults[simulation] );
                Runnable mru = new TaskMRU(sequence, frames, 250, MRUFaults[simulation] );

                threadPool.execute(fifo);
                threadPool.execute(lru);
                threadPool.execute(mru);
                

            }

            
        }

        threadPool.shutdown();

        // Verify that all threads do not have active processes.
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }
        catch (Exception ex) {
            System.out.println("Error in waiting for shutdown");
        }

        // Calculate runtime
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime) / 1000000;
        

        // Generate Report
        generateReport(totalTime, FIFOFaults, LRUFaults, MRUFaults);
        
    }

    /** GenerateReport function, Used to generate a report showing how long the appliction took to run through the simulations, the methods that had the least number of page faults and to trigger belady anomaly reporting
            Input: 
            - Duration (in ms) of how long application took to run
            - FIFO faults array
            - LRU faults array
            - MRU faults array

            Output:
            - Does not return any values. It instead prints a report containing run time and min fault counts
    */
    public static void generateReport(long duration, int[][] FIFOFaults, int[][] LRUFaults, int[][] MRUFaults) {

        Integer minFIFOFaults = 0;
        Integer minLRUFaults = 0;
        Integer minMRUFaults = 0;

        for (Integer simulation = 0; simulation < 1000; simulation++) {
            for (Integer frames = 0; frames < 100; frames++) {
                int FIFOFaultCount = FIFOFaults[simulation][frames];
                int LRUFaultCount = LRUFaults[simulation][frames];
                int MRUFaultCount = MRUFaults[simulation][frames];

                if (FIFOFaultCount <= LRUFaultCount && FIFOFaultCount <= MRUFaultCount) {
                    minFIFOFaults++;
                }

                if (LRUFaultCount <= FIFOFaultCount && LRUFaultCount <= MRUFaultCount) {
                    minLRUFaults++;
                }

                if (MRUFaultCount <= FIFOFaultCount && MRUFaultCount <= LRUFaultCount) {
                    minMRUFaults++;
                }

                System.out.print(FIFOFaults[simulation][frames]+ ",");
                
            }
            System.out.println("\n");
        }

        System.out.println("Simulation took " + duration + " ms\n" );
        

        // System.out.println("FILO min PF: " + minFIFOFaults);
        // System.out.println("LRU min PF: " + minLRUFaults);
        // System.out.println("MRU min PF: " + minMRUFaults);

        // beladyReport("FILO", FIFOFaults);
        // beladyReport("LRU", LRUFaults);
        // beladyReport("MRU", MRUFaults);
        
    }

    /** beladyReport function, used to detect belady anomalies between the number of page faults for each simulation iteration.
            Input: 
            - Algorithm type/name
            - two-dimensional faults array where the first dimension is a simulation and the second dimension contains the number of page faults for each frame count. 

            Output:
            - Does not return any values. It instead prints a series of log entires for each anomaly detected. 
    */
    private static void beladyReport(String type, int[][] faults) {
        System.out.println(String.format("\nBelady's Anomaly Report for %s.", type));

        int simCount = 0;
        int anomolyCount = 0;
        int maxDelta = 0;
        for (int i = 0; i < faults.length; i++) {
            for (int j = 0; j < faults[i].length - 1; j++) {
                int faultDifference = faults[i][j + 1] - faults[i][j];
                if (faultDifference > 0) {
                    if (faultDifference > maxDelta ) {
                        maxDelta = faultDifference;
                    }
                    anomolyCount++;
                    String logEntry = String.format("    Anomaly detected in simulation #%03d - %03d PF's @ %03d frames vs. %03d PF's @ %03d frames (Δ%d)", simCount, faults[i][j], (j + 1), faults[i][j+1], (j + 2), faultDifference );
                    System.out.println(logEntry);
                }
            }
            simCount++;
        }
        System.out.println("  Anomaly detected " + anomolyCount + " times in 1000 simulations with a max delta of " + maxDelta);
    }

}