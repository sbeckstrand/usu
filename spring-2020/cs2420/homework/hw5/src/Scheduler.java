
import java.util.ArrayList;

// Create a Scheduler class that facilitates creating a schedule from an array of tasks and then builds and then lists the efficiency of the tasks using various priorities.
public class Scheduler {
    public void makeSchedule(String description, ArrayList<Task> taskArray) {
        Schedule schedule = new Schedule(taskArray, description);
        schedule.doTasks();
    }
}

// Creates a schedule object that builds a queue of tasks and priorities based on different criteria. It then goes through each task and prints what order they should be processed in based on the desired priority.
class Schedule {
    private LeftistMinHeap queue;
    private String description;

    // Constructor to build Schedule object and insert the tasks into our queue.
    public Schedule(ArrayList<Task> tasks, String description) {
        this.queue = new LeftistMinHeap();
        this.description = description;

        for (Task task: tasks) {
            queue.insert(task);
        }

    }


    // public method to print out tasks based on priority.
    public void doTasks() {
        doTasks(this.queue, this.description);
    }

    private void doTasks(LeftistMinHeap queue, String description) {
        System.out.println("##################################################################");
        System.out.println(description);
        System.out.println("##################################################################");

        int timeCounter = 1;
        int timeLate = 0;
        int tasksLate = 0;
        while (!queue.isEmpty()) {
            Task currentTask = (Task) queue.deleteMin();
            System.out.print("   Time " + timeCounter + ": ");
            if (timeCounter >= currentTask.start) {
                System.out.print(currentTask.toString());
                currentTask.duration = currentTask.duration - 1;
                if (currentTask.duration != 0) {
                    queue.insert(currentTask);
                } else {
                    System.out.print(" **");
                    if (timeCounter > currentTask.deadline) {
                        tasksLate += 1;
                        int minutesLate = timeCounter - currentTask.deadline;
                        timeLate += minutesLate;
                        System.out.print(" Late " + minutesLate);
                    }
                }
            } else {
                queue.insert(currentTask);
                System.out.print("No task to do");
            }

            timeCounter += 1;
            System.out.print("\n");
        }
        System.out.print("Tasks Late: " + tasksLate + "\nTotal Time Late: " + timeLate + "\n\n\n");
    }



}
