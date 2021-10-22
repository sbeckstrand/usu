public class PiTask implements Runnable {
    
    TaskQueue queue; 
    ResultTable table;
    

    public PiTask(TaskQueue q, ResultTable t) {
        queue = q;
        table = t;
        run();
    }


    @Override
    public void run() {
        
        while (queue.getSize() != 0) {
            try {
                Integer decimal = queue.getNext();

                Pi calculator = new Pi();

                Integer value = calculator.calculate(decimal);
                
                table.update(decimal, value);

                 queue.increment();

                 if (queue.getProcessed() % 10 == 0) {
                     System.out.print(".");
                 }

                // In each thread
                    // Pull 'task' from queue which is basically just decimal position
                    // Compute requested digit
                
                    // Store results in Hash tble
                    // Keep pulling tasks until queue reports as empty
                    // voluntarily terminate

            } catch (Exception e) {
                ;
            }
        } 
    }
}