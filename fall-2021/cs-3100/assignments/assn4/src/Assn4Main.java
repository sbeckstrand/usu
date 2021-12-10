/**
* Class: 			CS-3100
* Description: 		Assignment 4. Pi in threads.
* @author			Stephen Beckstrand
*/

public class Assn4Main {


    public static void main(String[] args) {
        /* Setup queue, result table and build their initial structor*/
        TaskQueue queue = new TaskQueue();
        ResultTable table = new ResultTable();
        queue.buildQueue();
        table.buildTable(queue.getSize());

        /* Determine the number of virtual cores in computer and create that many worker threads */
        Integer logicalCores = Runtime.getRuntime().availableProcessors();

        long start = System.currentTimeMillis();
        for (Integer i = 0; i < logicalCores; i += 1) {
            PiTask task = new PiTask(queue, table);
            Thread workerThread = new Thread(task);
            workerThread.start();
        }
        long end = System.currentTimeMillis();
        long duration = end - start;



        
        /* Print Pi Results and how long it took to calculate */
        System.out.print("\n\n3.");
        for (Integer i = 1; i <= 1000; i +=  1) {
            System.out.print(table.get(i));
        }
        System.out.println("\nPi Computation took " + duration + " ms");

    }

    

}

