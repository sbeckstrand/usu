import java.io.File;
import java.util.*;

public class TestSched {

    // Method to read in file contents, and then creates three different arrays that store the tasks in the file. One that prioritizes based on Deadline, one based on start time, and one based on remaining duration of work needed.
    public static void readTasks(String file, ArrayList<Task> task1, ArrayList<Task> task2, ArrayList<Task> task3) {
        ArrayList<String> taskList = new ArrayList<String>();
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNext()) {
                String line = reader.nextLine().replaceAll("\\s+", " ");
                taskList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 1; i <= taskList.size(); i++) {
            String rawArguments[] = taskList.get(i -1).split(" ");
            ArrayList<Integer> arguments = new ArrayList<Integer>();
            for (String argument : rawArguments) {
                arguments.add(Integer.parseInt(argument));
            }

            Task1 taskA = new Task1(i, arguments.get(0), arguments.get(1), arguments.get(2));
            task1.add(taskA);
            Task2 taskB = new Task2(i, arguments.get(0), arguments.get(1), arguments.get(2));
            task2.add(taskB);
            Task3 taskC = new Task3(i, arguments.get(0), arguments.get(1), arguments.get(2));
            task3.add(taskC);
        }

    }

    // Builds three arrays of tasks from each task set and builds a schedule from it.
    public static void main(String args[]) {
        Scheduler s = new Scheduler();
        String [] files = {"taskset1.txt","taskset2.txt","taskset3.txt","taskset4.txt","taskset5.txt" };
        for (String f : files) {
            ArrayList<Task> t1 = new ArrayList();    // elements are Task1
            ArrayList<Task> t2 = new ArrayList();    // elements are Task2
            ArrayList<Task> t3 = new ArrayList();    // elements are Task3
            readTasks(f, t1, t2, t3);
            s.makeSchedule("Deadline Priority ("+ f + ")", t1);
            s.makeSchedule("Startime Priority (" + f + ")", t2);
            s.makeSchedule("Duration Priority (" + f + ")", t3);
       }

    }
}
