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


    public static void main(String[] args) {
        simulate();

    }

    public static void simulate() {
        System.out.println("Simulate");

        Integer minFIFOFaults = 0;
        Integer minLRUFaults = 0;
        Integer minMRUFaults = 0;

        // Create a thread pool with as many workers as there are processors available on your system. 
        Integer cores = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(cores);

        Integer total = 0;

        ArrayList<ArrayList<Integer>> FIFOFaults = new ArrayList<>(2);
        ArrayList<ArrayList<Integer>> LRUFaults = new ArrayList<>(2);
        ArrayList<ArrayList<Integer>> MRUFaults = new ArrayList<>(2);

        // Simulation loop
        for (Integer simulation = 0; simulation < 1000; simulation++) {

            /* Generate sequence of integers between [1,250] */
            ArrayList<Integer> sequence = new ArrayList<Integer>();
            

            for (Integer randomRange  = 0; randomRange < 1000; randomRange++) {
                Integer randomNumber = ThreadLocalRandom.current().nextInt(1,251);
                sequence.add(randomNumber);
            }

            // For each max Thread count from [1,100]
            for (Integer frames = 1; frames <= 100; frames++) {
                
                total++;
                

                Runnable fifo = new TaskFIFO(sequence, frames, 250, FIFOFaults );
                Runnable lru = new TaskLRU(sequence, frames, 250, LRUFaults );
                Runnable mru = new TaskMRU(sequence, frames, 250, MRUFaults );

                // Add objects to thread pool
                threadPool.execute(fifo);
                threadPool.execute(lru);
                // threadPool.execute(mru);
                
                // Determine which method has the last number of faults
                // Ties:
                

            }

            
        }

        threadPool.shutdown();

        // while (((ThreadPoolExecutor) threadPool).getTaskCount() > 0) {
        //     System.out.println(((ThreadPoolExecutor) threadPool).getTaskCount());
        // }

        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }
        catch (Exception ex) {
            System.out.println("Error in waiting for shutdown");
        }

        System.out.println("testing");
        // For 1 -> 1000
            // Generate sequence: 1000 element array where each value is a random number between 1 and 250
            // For each memory frame couunt 1 --> 100
                // Integer FIFOFaults, LRUFaults, MRUFaults;
                // Create a FIFO simulation task, e.g. Runnable fifo = new TaskFIFO(p, f, 250, pageFaults)
                // Create a LRU simulation task, e.g. Runnable lru = new TaskLRU(p, f, 250, pageFaults)
                // Create a MRU simulation task, e.g. Runnable mru = new TaskMRU(p, f, 250, pageFaults)
                // Add objects to thread pool
                // Determine which method has the least number of faults


        
        // Wait for all tasks to finish
        // Report simulation time. 
        // Report many times each method had the least number of faults
        // Report anomalies. 


        // if (FIFOFaults.size() == LRUFaults.size() && LRUFaults.size() == MRUFaults.size()) {
        //     minFIFOFaults++;
        //     minLRUFaults++;
        //     minMRUFaults++;
        // } 
        // else if (FIFOFaults.size() == LRUFaults.size() && FIFOFaults.size() > MRUFaults.size()) {
        //     minFIFOFaults++;
        //     minLRUFaults++;
        // } 
        // else if (FIFOFaults.size() == MRUFaults.size() && FIFOFaults.size() > LRUFaults.size()) {
        //     minFIFOFaults++;
        //     minMRUFaults++;
        // }
        // else if (LRUFaults.size() == MRUFaults.size() && LRUFaults.size() > FIFOFaults.size()) {
        //     minLRUFaults++;
        //     minMRUFaults++;
        // } else if (FIFOFaults.size() < LRUFaults.size() && FIFOFaults.size() < MRUFaults.size()) {
        //     minFIFOFaults++;
        // } else if (LRUFaults.size() < FIFOFaults.size() && LRUFaults.size() < MRUFaults.size()) {
        //     minLRUFaults++;
        // } else {
        //     minMRUFaults++;
        // }

        // System.out.println("FILO Faults: " + minFIFOFaults);
        // System.out.println("LRU Faults: " + minLRUFaults);
        // System.out.println("MRU Faults: " + minMRUFaults);

        System.out.println(FIFOFaults.get(1));
    }
    
    public static void test() {

        Integer cores = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(cores);

        ArrayList<Integer> testArray = new ArrayList<Integer>(Arrays.asList(7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0));

        ArrayList<ArrayList<Integer>> faultList = new ArrayList<>(2);
        TaskLRU lru = new TaskLRU(testArray, 4, 250, faultList );

        threadPool.execute(lru);

        

       


        threadPool.shutdown();


        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        }
        catch (Exception ex) {
            System.out.println("Error in waiting for shutdown");
        }

        System.out.println(faultList.size());
        System.out.println("+++");

        
    }
}