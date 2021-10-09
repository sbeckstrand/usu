public class Ptime {

    long totalTime = 0;

    public void getTime() {
        double inMili = totalTime / 1000.0;
        System.out.println(String.format("Total time in child processes: %.4f seconds", inMili));
    }

    public void updateTime(Long time) {
        totalTime += time;
    }
}