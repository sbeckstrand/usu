import java.util.ArrayList;

//
public class DisjointSet {
    ArrayList<Integer> sizes;
    ArrayList<Integer> parents;
    int numOfItems;

    public DisjointSet(int size) {
        numOfItems = size;
        sizes = new ArrayList<Integer>();
        parents = new ArrayList<Integer>();

        for (int i = 0; i < numOfItems; i++) {
            sizes.add(i);
            parents.add(i);
        }
    }
    public int find(int val) {
        if (parents.get(val) != val) {
            parents.set(val, find(parents.get(val)));
        }

        return parents.get(val);
    }

    public void union(int val1, int val2) {

        int val1Parent = find(val1);
        int val2Parent = find(val2);

        if (val1Parent == val2Parent) {
            return;
        }

        if (sizes.get(val1Parent) < sizes.get(val2Parent)) {
            parents.set(val1Parent, val2Parent);
        }

        else if (sizes.get(val1Parent) > sizes.get(val2Parent)) {
            parents.set(val2Parent, val1Parent);
        }

        else {
            parents.set(parents.get(val1Parent), val2Parent);
            sizes.set(val2Parent, sizes.get(val2Parent) + 1);
        }




    }
}
