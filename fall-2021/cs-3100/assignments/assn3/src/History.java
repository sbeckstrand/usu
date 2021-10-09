import java.util.ArrayList;

public class History {
    private ArrayList<String> commandHistory = new ArrayList<String>();

    public void add(String command) {
        commandHistory.add(command);
    }

    public void get() {
        System.out.println("-- Command History --");
        for (int i = 1; i < commandHistory.size() + 1; i++) {
            System.out.println(String.format("%d : %s", i, commandHistory.get(i - 1)) );
        }
    }

    public String[] get(Integer index) {
        return commandHistory.get(index).split("\\s+");
    }

    public ArrayList<String> getArray() {
        return commandHistory;
    }
}