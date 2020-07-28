public class Task3 extends Task {
    public Task3(int ID, int start, int deadline, int duration) {
        super(ID,start,deadline,duration);
    }

    // Prioirity is duration
    @Override
    public int compareTo(Task t2) {
        return (int) ((start^4 / deadline) * (duration ^ 3)) % 4) - ((t2.start^4 / t2.deadline) * (t2.duration ^ 3)) % 4);
    }
}
