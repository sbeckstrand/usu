import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class hw2 {
    public static void main(String[] args) {
        String sectionBreak = "##################################################";
        // Generate 5 different arrays of numbers to use as node values for our tree objects.
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        int val = 60;
        final int SIZE = 8;

        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};
        ArrayList<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            v2.add(t);
        }
        v2.add(val);
        Integer[] v3 = {200, 15, 3, 65, 83, 70, 90};
        ArrayList<Integer> v4 = new ArrayList<Integer>(Arrays.asList(v3));
        Integer[] v = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        ArrayList<Integer> v5 = new ArrayList<Integer>(Arrays.asList(v));
        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};


        // Create Tree objects using our array of numbers.
        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        Tree<Integer> tree2 = new Tree<Integer>(v2, "Tree2:");
        Tree<Integer> tree3 = new Tree<Integer>(v3, "Tree3:");
        Tree<Integer> treeA = new Tree<Integer>(v4, "TreeA:", 2);
        Tree<Integer> treeB = new Tree<Integer>(v5, "TreeB", 3);
        Tree<Integer> treeC = new Tree<Integer>("TreeC");


        // Task 1: toString function returning tree name and its nodes in order.
        System.out.println(sectionBreak);
        System.out.println("Method 1: toString()");
        System.out.println("Complexity: O(n) --> Traversing each item once.");
        System.out.println(sectionBreak);
        System.out.println(tree1.toString(tree1.getTreeName(), tree1.getRoot(), 0));
        System.out.println(tree1.toString2());


        // Task 2: Flipping a tree
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 2: flip()");
        System.out.println("Complexity: O(n) --> Traversing each item once. ");
        System.out.println(sectionBreak);
        System.out.println(treeA.toString(treeA.getTreeName(), treeA.getRoot(), 0));
        System.out.println(treeA.toString2());
        treeA.flip(treeA.getRoot());
        System.out.println("Now flipped\n" + treeA.toString(treeA.getTreeName(), treeA.getRoot(), 0));

        //task 3: Find successor
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 3: successor()");
        System.out.println("Complexity: O(n) --> Traversing each item once. ");
        System.out.println(sectionBreak);
        tree2.contains(val);  //Sets the current node inside the tree6 class.
        int succCount = 5;  // how many successors do you want to see?
        System.out.println("In Tree2, starting at " + val + ENDLINE);
        for (int i = 0; i < succCount; i++) {
            System.out.println("The next successor is " + tree2.successor());
        }

        // Task 4, Count nodes in level
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 4: nodesInLevel()");
        System.out.println("Complexity: O(n) --> Traversing each item once.");
        System.out.println(sectionBreak);
        for (int mylevel = 0; mylevel < SIZE; mylevel += 2) {
            System.out.println("Number nodes at level " + mylevel + " is " + tree1.nodesInLevel(tree1.getRoot(), 0, mylevel));
        }


        // Task 5, Print path to each leaf.
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 5: printAllPaths()");
        System.out.println("Complexity: O(n^2) --> Because I am iterating through all nodes to get to leaf nodes and then iterating back up through the tree until I get to the root to get the path");
        System.out.println(sectionBreak);
        System.out.println("All paths from tree1");
        tree1.printAllPaths(tree1.getRoot());

        // Task 6, Print nodes on levels in a zig zag fashion.
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 6: byLevelZigZag()");
        System.out.println("Compelxity: O(n) --> I am traversing through each item once. ");
        System.out.println(sectionBreak);
        System.out.print("Tree1 byLevelZigZag: (5): ");
        System.out.println(tree1.byLevelZigZag(tree1.getRoot()));

        System.out.print("Tree2 byLevelZigZag (3): ");
        System.out.println(tree2.byLevelZigZag(tree2.getRoot()));

        // Task 7, count Binary Search Trees
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 7: countBST()");
        System.out.println("Complexity: O(n) * (2T(n/2) + O(n)) --> This method has a helper method that is called within the primary method. So I am multiplying the complexity of both. ");
        System.out.println(sectionBreak);
        treeA.flip(treeA.getRoot());
        System.out.println("treeA Contains BST: " + treeA.countBST(treeA.getRoot(), 0));
        System.out.println("treeB Contains BST: " + treeB.countBST(treeB.getRoot(), 0));

        // Task 8, prune nodes not in path that is greater or equal to sum.
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 8: prune()");
        System.out.println("Complexity: O(n) --> Traverses through the tree once. ");
        System.out.println(sectionBreak);
        treeB.prune(treeB.getRoot(), 60);
        treeB.changeName("treeB after pruning 60");
        System.out.println(treeB.toString(treeB.getTreeName(), treeB.getRoot(), 0));
        treeA.prune(treeA.getRoot(), 220);
        treeA.changeName("treeA after pruning 220");
        System.out.println(treeA.toString(treeA.getTreeName(), treeA.getRoot(), 0));

        // Task 9, build tree using in order and preorder traversal.
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 9: buildTreeTraversals()");
        System.out.println("Unfortunatley I cannot quite figure this out one. I am able to get it to build the left side of my tree correctly but cant get the right side of my tree to build. ");
        System.out.println("Complexity: If I was able to get this to work, I think it would be complexity O(n^2) since for each node in one list, we are checking it against all items in the other list, so n * n (n^2).");
        System.out.println(sectionBreak);
        treeC.buildTreeTraversals(inorder, preorder,  0, null, null);
        treeC.changeName("Tree C built from inorder and preorder traversals");
        System.out.println(treeC.toString(treeC.getTreeName(), treeC.getRoot(), 0));


        // Task 10, Find lowest comment ancestor
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 10: lca()");
        System.out.println("Complexity: O(n) --> Traverses through tree once");
        System.out.println(sectionBreak);
        System.out.println(tree1.toString(tree1.getTreeName(), tree1.getRoot(), 0));
        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(tree1.getRoot(), 56, 61) + ENDLINE);
        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(tree1.getRoot(), 6, 25) + ENDLINE);

        // Task 11, Balance Tree
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 11: balanceTree()");
        System.out.println("Complexity: O(n^2) As we traverse through the tree to get an array containing each node and then go through each node to insert it into the tree.  ");
        System.out.println(sectionBreak);
        System.out.println(tree3.toString(tree3.getTreeName(), tree3.getRoot(), 0));
        tree3.balanceTreeWrapper();
        tree3.changeName("tree3 after balancing");
        System.out.println(tree3.toString(tree3.getTreeName(), tree3.getRoot(), 0));


        // Task 12, Only keep elements in range
        System.out.println("\n\n" + sectionBreak);
        System.out.println("Method 12: keepRange()");
        System.out.println("Complexity: O(n) --> Traversing through the tree once.");
        System.out.println(sectionBreak);
        tree1.keepRange(tree1.getRoot(), 10, 50);
        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
        System.out.println(tree1.toString(tree1.getTreeName(), tree1.getRoot(), 0));
        tree3.keepRange(tree3.getRoot(), 3, 85);
        tree3.changeName("tree3 after keeping only nodes between 3  and 85");
        System.out.println(tree3.toString(tree3.getTreeName(), tree3.getRoot(), 0));
    }
}
