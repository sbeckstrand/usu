import java.util.HashMap;

public class ResultTable {

    HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();

    /* Build initial table. */
    public void buildTable(Integer size) {
        for (Integer i = 1; i <= size; i += 1) {
            hashTable.put(i, -1);
        }
    }

    /* Update a position in the table with a result */
    public void update(Integer position, Integer value) {
        hashTable.put(position, value);
    }

    /* Get current value by key (decimal place) */
    public Integer get(Integer key) {
        return hashTable.get(key);
    }
}