
public class LeftistMinHeap<AnyType extends Comparable> {
    Node<AnyType> root = null;

    public boolean isEmpty() {
        boolean isEmpty = (root == null);
        return isEmpty;
    }

    // Our insert method does not need to do much on its own, Instead, it creates a new Node with the value provided and merges it into our heap. Inserting is convenient since it is always checked with the root of the heap and makes it way down until it finds a home.
    public Node<AnyType> insert(AnyType val) {
        Node<AnyType> newNode = new Node(val);
        root = merge(newNode, root);
        return root;
    }

    // Our merge method is used to combine two separate heaps. This can be considered the meat and potatoes of the Leftist Min Heap Logic. It will check to see which node is smaller and add that to the head. It then compares the larger node with the right child of the smaller node until it finds a home. After we have merged, we check the null path length of both children of the smaller node and if the right side is 'heavier' we swap the places
    private Node<AnyType> merge(Node<AnyType> node1, Node<AnyType> node2 ) {
        if (node1 == null) {
            return node2;
        }

        if (node2 == null) {
            return node1;
        }

        Node<AnyType> mergedNode = null;
        Node<AnyType> largerNode = null;

        int compareResult = node1.value.compareTo(node2.value);
        if (compareResult >= 0) {
            mergedNode = node2;
            largerNode = node1;
        } else {
            mergedNode = node1;
            largerNode = node2;
        }


        mergedNode.right = merge(mergedNode.right, largerNode);

        if (mergedNode.left == null) {
            mergedNode.left = mergedNode.right;
            mergedNode.right = null;
        } else {
            if (mergedNode.left.npl < mergedNode.right.npl) {
                Node<AnyType> tempNode = mergedNode.left;
                mergedNode.left = mergedNode.right;
                mergedNode.right = tempNode;
            }

            mergedNode.npl = mergedNode.right.npl + 1;

        }

        return mergedNode;
    }

    // This method removes our root node from the heap, merges the two children and then returns the removed node.
    public AnyType deleteMin() {
        if (!isEmpty()) {
            AnyType minValue = root.value;
            root = merge(root.left, root.right);
            return minValue;
        } else {
            return null;
        }
    }

    // Method used to print our heap/tree as a string.
    public String toString() {

        return toString(root, 0);
    }

    private String toString(Node<AnyType> current, int depth) {
        String result = "";

        if (current == null) {
            return "Queue is empty";
        }

        if (current.right != null) {
            result += toString(current.right, depth + 1);
        }

        String spaces = "";
        for (int i = 0; i < depth; i++) {
            spaces += " ";
        }

        result += spaces + current.value + " (" + current.npl + ") \n";

        if (current.left != null) {
            result += toString(current.left, depth +1);
        }

        return result;
    }


    // Class used to build our node object which stores our tasks and the details about its position in the tree.
    public class Node<AnyType> {
        AnyType value;
        Node<AnyType> left;
        Node<AnyType> right;
        int npl;


        Node(AnyType val) {
            this(val, null, null);
        }

        Node(AnyType val, Node<AnyType> leftTree, Node<AnyType> rightTree) {
            value = val;
            left = leftTree;
            right = rightTree;
            npl = 0;
        }
    }
}


