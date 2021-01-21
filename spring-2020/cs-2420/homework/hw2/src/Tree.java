// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import org.w3c.dom.Node;

import java.util.*;

class UnderflowException extends RuntimeException {
    // Constructor for exception object. Takes error message as argument.
    public UnderflowException(String message) {
        super(message);
    }
}


public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
        int preOrderIndex = 0;
    }

    /**
     * Create non ordered tree from list in preorder
     * @param arr    List of elements
     * @param label  Name of tree
     * @param height Maximum height of tree
     */
    public Tree(ArrayList<E> arr, String label, int height) {
        this.treeName = label;
        root = buildTree(arr, height, null);
    }

    /**
     * Create BST
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            bstInsert(arr.get(i));
        }
    }

    /**
     * Create BST from Array
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    public BinaryNode<E> getRoot() {
        return this.root;
    }
    public String getTreeName() { return this.treeName; }
    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString(String treeName, BinaryNode<E> root, int depth) {
        String result = "";

        if (depth == 0) {
            result += treeName + "\n";
        }

        if (root == null)
            return (treeName + " Empty tree\n");

        if (root.right != null) {
            result += toString(treeName, root.right, depth + 1);
        }
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            space.append(" ");
        }
        result += space + root.toString() + "\n";

        if (root.left != null) {
            result += toString(treeName, root.left, depth + 1);
        }


        return result;

    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip(BinaryNode<E> current) {
         if (current == null ) {
             return;
         }

         BinaryNode<E> temp = current.left;
         current.left = current.right;
         current.right = temp;

         flip(current.left);
         flip(current.right);

    }

    /**
     * Find successor of "curr" node in tree
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;

        //curr = successor(curr);
        if (curr == null) return "null";

        if (curr.right != null) {
            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
        } else {
            BinaryNode<E> currentCompare = curr.parent;
            while (currentCompare.element.compareTo(curr.element) < 1) {
                currentCompare = currentCompare.parent;
            }
            curr = currentCompare;

        }
        return curr.toString();
    }

    /**
     * Counts number of nodes in specifed level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(BinaryNode<E> current, int level, int target) {
        if (current == null) {
            return 0;
        }
        if (level == target) {
            return 1;
        }
        if (level < target) {
            return nodesInLevel(current.left, level + 1, target) + nodesInLevel(current.right, level + 1, target);
        } else {
            return 0;
        }
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths(BinaryNode<E> current) {
        if (current == null) {
            return;
        }

        if (current.left == null && current.right == null) {
            String path = current.element.toString();
            while (current.parent != null) {
                path += " " + current.parent.element.toString();
                current = current.parent;
            }
            System.out.println(path);
            return;
        }
        if (current.left != null) {
            printAllPaths(current.left);
        }
        if (current.right != null) {
            printAllPaths(current.right);
        }
    }

    /**
     * Print contents of levels in zig zag order
     */
    public String byLevelZigZag(BinaryNode<E> current) {
        List<List<E>> list = new ArrayList<>();
        List<E> outputList = new ArrayList<>();
        String output = "";
        if (root == null) {
            return output;
        }

        Queue<BinaryNode<E>> queue = new LinkedList<>();

        queue.add(current);

        boolean isZigZag = false;

        while (!queue.isEmpty()) {
            List<E> currentLevel = new ArrayList<E>();
            int queueLength = queue.size();

            for (int i = 0; i < queueLength; i++) {
                BinaryNode<E> node = queue.poll();

                if (isZigZag) {
                    currentLevel.add(0, node.element);
                } else {
                    currentLevel.add(node.element);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(currentLevel);
            isZigZag = !isZigZag;
        }

        for (List<E> subList: list) {
            outputList.addAll(subList);
        }
        Collections.reverse(outputList);
        for (E num: outputList) {
            output += num + " ";
        }
        return output;
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public Integer countBST(BinaryNode<E> current, int count) {
        int counter = count;
        if (current == null) {
            return 0;
        }

        if (isBST(current)) {
            counter += 1;
        }

        counter += countBST(current.left, count) + countBST(current.right, count);

        return counter;



    }

    private boolean isBST(BinaryNode<E> current) {
        if (current == null) {
            return false;
        }


        if (current.left == null && current.right == null) {
            return true;
        }

        if (!isBST(current.left) || !isBST(current.right)) {
            return false;
        }

        if (current.left == null) {
            if (current.right.element.compareTo(current.element) > 0) {
                return true;
            }
        }

        if (current.right == null) {
            if (current.left.element.compareTo(current.element) < 0) {
                return true;
            }
        }

        if (current.left != null && current.right != null) {
            if (current.left.element.compareTo(current.element) < 0 && current.right.element.compareTo(current.element) > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    public void bstInsert(E x) {

        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.(
     * @return true if found.
     */
    public boolean contains(E item) {

        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value

     */
    public BinaryNode<E> prune(BinaryNode<E> current, int min) {
        if (current == null) {
            return null;
        }

        current.left = prune(current.left, min - (Integer) current.element);
        current.right = prune(current.right, min - (Integer) current.element);

        if (isLeaf(current)) {
            if (min > (Integer) current.element) {
                current = null;
            }
        }




        return current;
    }

    private boolean isLeaf(BinaryNode<E> current) {
        if (current == null) {
            return false;
        }

        return current.left == null && current.right == null;
    }

    /**
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */

    public BinaryNode<Integer> buildTreeTraversals(Integer[] inOrder, Integer[] preOrder, int currentIndex, BinaryNode<Integer> parent, String side) {
            int currentValue = preOrder[currentIndex];
            BinaryNode<Integer>  currentNode = new BinaryNode<Integer>(currentValue);

            if (root == null) {
                root = (BinaryNode<E>) currentNode;
            }


            if (search(inOrder, currentValue) == -1) {
                return null;
            }

            int inOrderIndex = search(inOrder, currentValue);
            currentNode.parent = parent;

            Integer[] leftSubTree = new Integer[inOrderIndex];

            Integer[] rightSubTree = new Integer[inOrder.length - (inOrderIndex + 1)];

            for (int i = 0; i < inOrderIndex; i++) {
                leftSubTree[i] = (inOrder[i]);
            }

            int j = 0;
            for (int i = (inOrderIndex + 1); i < inOrder.length; i++) {
                rightSubTree[j] = inOrder[i];
                j += 1;
            }

            currentNode.left = buildTreeTraversals(leftSubTree, preOrder, currentIndex + 1, currentNode, "left");
            currentNode.right = buildTreeTraversals(rightSubTree, preOrder, currentIndex + 1, currentNode, "right");


            return currentNode;

    }

    private int search(Integer[] arr, int value) {
        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(BinaryNode<E> current, E a, E b) {
        if (current == null) return null;

        if(current.element.compareTo(a) > 0 && root.element.compareTo(b) > 0) {
            return lca(current.left, a, b);
        }

        if (current.element.compareTo(a) < 0 && current.element.compareTo(b) < 0) {
            return lca(current.right, a, b);
        }

        return current.element.toString();
    }

    /**
     * Balance the tree
     */
    public void balanceTreeWrapper() {
        ArrayList<Integer> nodes = inOrderTraverse(root, new ArrayList<Integer>());
        root = null;

        balanceTree(nodes, 0, nodes.size() -1, null);



    }

    BinaryNode<Integer> balanceTree(ArrayList<Integer> nodes, int start, int end, BinaryNode<E> parent) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BinaryNode currentNode = new BinaryNode(nodes.get(mid));

        if (root == null) {
            root = currentNode;
        }
        currentNode.parent = parent;

        currentNode.left = balanceTree(nodes, start, mid -1, currentNode);
        currentNode.right = balanceTree(nodes, mid + 1, end, currentNode);

        return currentNode;
    }

    private ArrayList<Integer> inOrderTraverse(BinaryNode<E> current, ArrayList<Integer> array) {
        if (current == null) {
            return array;
        }

        ArrayList<Integer> leftArray = inOrderTraverse(current.left, array);
        array.add((Integer) current.element);
        ArrayList<Integer> rightArray = inOrderTraverse(current.right, array);

        ArrayList<Integer> combined = new ArrayList<Integer>(leftArray);

        for (Integer item: rightArray) {
            if (!combined.contains(item)) {
                combined.add(item);
            }
        }

        return combined;
    }
    /**
     * In a BST, keep only nodes between range
     * @param a lowest value
     * @param b highest value
     */
    public BinaryNode<E> keepRange(BinaryNode<E> current, E a, E b) {
        if (current == null) {
            return null;
        }

        if (current == root) {
            if (root.element.compareTo(a) < 0) {
                if (root.right != null) {
                    BinaryNode<E> rightChild = root.right;
                    root = rightChild;
                    root.parent = null;
                }
            }

            if (root.element.compareTo(b) > 0) {
                if (root.left != null) {
                    BinaryNode<E> leftChild = root.left;
                    root = leftChild;
                    root.parent = null;
                }
            }
        }


        current.left = keepRange(current.left, a, b);
        current.right = keepRange(current.right, a, b);

        if (current.element.compareTo(a) < 0) {
            return current.right;
        }

        if (current.element.compareTo(b) > 0) {
            return current.left;
        }

        return current;
    }

    //PRIVATE

     /**
     * Build a NON BST tree by preorder
     *
     * @param arr    nodes to be added
     * @param height maximum height of tree
     * @param parent parent of subtree to be created
     * @return new tree
     */
    private BinaryNode<E> buildTree(ArrayList<E> arr, int height, BinaryNode<E> parent) {
        if (arr.isEmpty()) return null;
        BinaryNode<E> curr = new BinaryNode<>(arr.remove(0), null, null, parent);
        if (height > 0) {
            curr.left = buildTree(arr, height - 1, curr);
            curr.right = buildTree(arr, height - 1, curr);
        }
        return curr;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }



    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element);
            if (parent == null) {
                sb.append("[No Parent]");
            } else {
                sb.append("[");
                sb.append(parent.element);
                sb.append("]");
            }

            return sb.toString();
        }

    }
}
