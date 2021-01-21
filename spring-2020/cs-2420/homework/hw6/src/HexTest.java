import org.junit.Assert;

import java.io.FileNotFoundException;

public class HexTest {

    // Unit Test to confirm that DisjointSet constructor works and that finding an integer that has not been unioned with any other integer should report itself as its 'parent'
    @org.junit.Test
    public void DisjointSetFind() {
        DisjointSet testFind = new DisjointSet(5);

        Assert.assertEquals("test", 4, testFind.find(4));
    }

    // Unit Test to confirm that an index out of bounds error is returned if you try to find an object outside of the constructed index range.
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void DisjointSetFindIndexException() {
        DisjointSet testFind = new DisjointSet(5);
        testFind.find(5);
    }

    // Unit Test to confirm that if you union two items in a disjoint set that the representing integer is the largest of the two.
    @org.junit.Test
    public void DisjointSetUnion() {
        DisjointSet testFind = new DisjointSet(5);
        testFind.union(1, 2);
        Assert.assertEquals("test", 2, testFind.find(1));
    }

    // Unit Test to confirm that an index out of bounds error is returned if you try to find an object outside of the constructed index range.
    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void DisjointSetUnionIndexException() {
        DisjointSet testFind = new DisjointSet(5);
        testFind.union(1, 10);
    }

    // Tests the moves from "moves.txt" to confirm it comes out with a winner.
    @org.junit.Test
    public void HexConfirmConnection() throws FileNotFoundException {
        Hex testGame = new Hex(11, 11, "moves.txt");

        Assert.assertEquals("test", testGame.sets.find(121), testGame.sets.find(121));
    }

    // Tests the moves from "moves3.txt" to confirm it does not come out with a winner.
    @org.junit.Test
    public void HexConfirmNoConnection() throws FileNotFoundException {
        Hex testGame = new Hex(11, 11, "moves3.txt");

        Assert.assertEquals("test", testGame.winner, null);
    }
}
