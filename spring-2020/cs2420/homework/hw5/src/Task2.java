public class Task2 extends Task {
    public Task2(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }

    // Prioirity is start time
    @Override
    public int compareTo(Task t2) {
        if (this.start == t2.start) {
            return this.deadline - t2.deadline;
        } else {
            return this.start - t2.start;
        }
    }
}
