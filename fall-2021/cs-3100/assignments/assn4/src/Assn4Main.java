/**
* Class: 			CS-3100
* Description: 		Assignment 4. Pi in threads.
* @author			Stephen Beckstrand
*/

public class Assn4Main {

    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue();
        ResultTable table = new ResultTable();
        queue.buildQueue();
        table.buildTable(queue.getSize());

        Integer logicalCores = Runtime.getRuntime().availableProcessors();
        // Create as many worker threads as there are CPU cores
            // Figure out how many CPU cores there are. 

        long start = System.currentTimeMillis();
        for (Integer i = 0; i < logicalCores; i += 1) {
            PiTask task = new PiTask(queue, table);
            Thread workerThread = new Thread(task);
            workerThread.start();
        }
        long end = System.currentTimeMillis();
        long duration = end - start;



        

        
        
        // Output a "." for every 10th digit calculated

        // When queue is empty and all threads have finished, display the value of Pi
        System.out.print("\n\n3.");
        for (Integer i = 1; i <= 1000; i +=  1) {
            System.out.print(table.get(i));
        }
        System.out.println("\nPi Computation took " + duration + " ms");

        // Gracefully exit
    }

    

}

