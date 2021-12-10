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
        
        /* Worker Thread Task. TL;DR: While there are items in the queue, try to pull it, if successful, calculate pi at that decimal place and return the value.*/
        while (queue.getSize() != 0) {
            try {
                Integer decimal = queue.getNext();

                Pi calculator = new Pi();

                Integer value = calculator.calculate(decimal);
                
                table.update(decimal, value);

                 queue.increment();

                 if (queue.getProcessed() % 10 == 0) {
                     System.out.print(".");
                    
                    if (queue.getProcessed() % 200 == 0) {
                        System.out.print("\n");
                    } 
                 }
            /* If there is some execption, such as trying to pull the item at the same time as another thread, skip and move on */
            } catch (Exception e) {
                ;
            }
        } 
    }
}