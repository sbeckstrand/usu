
public class BinarySearchTree<E extends Comparable> {

    Node<E> root;
    int count;
    int leafCount;
    int height;

    // Method used to insert nodes into our binary search tree
    public boolean insert(E value) {

        // Check to see if value already exist, if not, continue with adding it.
        boolean searchResult = search(value);

        if (!searchResult) {
            this.count += 1;

            Node<E> node = new Node<>(value);

            // Check if there is a root value, if not, set the root to our node and then return true. This should only pass with the first node created.
            if (root == null) {
                root = node;
                return true;
            }

            // If a root node does exist, Create a new parent and current node object and appropriately place our argument node into our tree
            Node<E> parent = null;
            Node<E> current = root;
            while (current != null) {
                parent = current;
                if (value.compareTo(current.value) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (value.compareTo(parent.value) < 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            return true;
        }
        return false;
    }

    // Print the argument message to console and then traverse through tree to find each node
    public void display(String message) {
        // Perform an in-order traversal of the tree, displaying the value at each node. Use the message parameter asa header/title for the traversal
        System.out.println(message);
        traversal(root);
    }

    // Traverse through tree and print each node value to console
    private void traversal(Node<E> node) {
        if (node == null) return;

        traversal(node.left);
        System.out.println(node.value);
        traversal(node.right);
    }

    // Method to remove a node from our tree if it exists
    public boolean remove(E value) {
        // delete a value from the tree.
        // If value was in tree and deleted, return true. Otherwise, return false
            if (search(value) == false) {
                return false;
            }

            Node<E> parent = null;
            Node<E> node = root;
            boolean result;

            boolean done = false;
            while(!done) {
                // Has left node
                if (value.compareTo(node.value) < 0) {
                    parent = node;
                    node = node.left;
                }
                // Has right node
                else if (value.compareTo(node.value) > 0) {
                    parent = node;
                    node = node.right;
                }
                // Has no children
                else {
                    done = true;
                }
            }

            if (node.left == null) {
                if (parent == null) {
                    root = node.right;
                    count -= 1;
                    result = true;
                }
                else {
                    if (value.compareTo(parent.value) < 0) {
                        parent.left = node.right;
                        count -= 1;
                        result = true;
                    }
                    else {
                        parent.right = node.right;
                        count -= 1;
                        result = true;
                    }
                }


            }
            else {
                Node<E> parentOfRightMost = node;
                Node<E> rightMost = node.left;
                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right;
                }
                node.value = rightMost.value;
                if (parentOfRightMost.right == rightMost) {
                    parentOfRightMost.right = rightMost.left;
                }
                else {
                    parentOfRightMost.left = rightMost.left;
                }
                count -= 1;
                result = true;

            }

        return result;
    }

    // Method to search through tree to see if value exists.
    public boolean search(E value) {
        Node<E> node = root;
        boolean result = false;
        while (node != null) {

            if (value.compareTo(node.value) == 0) {
                result = true;
            }

            if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return result;
    }

    // Method to return the total number of nodes
    public int numberNodes() {
        // return a count of all nodes in tree
        return this.count;
    }

    // Method used to return the total number of leaf nodes
    public int numberLeafNodes() {
        // return a count of all leaf nodes in tree
        leafCount = findLeafs(root);
        return leafCount;
    }

    // Method to return height of binary tree
    public int height() {
        // return the height of the three

        height = findHeight(root);

        return height;
    }

    // Method used to count the number of leafNodes
    private int findLeafs(Node<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        } else {
            return findLeafs(node.left) + findLeafs(node.right);
        }

    }

    // Method used to calculate the height of our tree
    private int findHeight(Node<E> node) {
        if (node == null) {
            return -1;
        }

        return 1 + Math.max(findHeight(node.left), findHeight(node.right));
    }

    // Class used to instantiate a Node object. 
    class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
        }
    }

}
