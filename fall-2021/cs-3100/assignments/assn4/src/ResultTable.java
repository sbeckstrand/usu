import java.util.HashMap;

public class ResultTable {

    HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();

    public void buildTable(Integer size) {
        for (Integer i = 1; i <= size; i += 1) {
            hashTable.put(i, -1);
        }
    }

    public void update(Integer position, Integer value) {
        hashTable.put(position, value);
    }

    public Integer get(Integer key) {
        return hashTable.get(key);
    }

}