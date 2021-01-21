// Class to define a LinkedList. In our case we will use it to form our Queue object.
public class LinkedList {
    private Node head;
    private int count;

    static class Node {
        WordInfo info;
        Node link;

        // Constructor for node
        Node(WordInfo wordInfo) {
            link = null;
            info = wordInfo;
        }

        // Method to get the information about the word stored in our node.
        public WordInfo getInfo() {
            return this.info;
        }
    }

    // Method to add  a word to our queue
    public void add(WordInfo wordInfo) {
        Node newNode = new Node(wordInfo);
        newNode.link = null;
        this.count++;

        if (this.head == null) {
            this.head = newNode;
        }
        else {
            Node previous = this.head;
            while (previous.link != null) {
                previous = previous.link;
            }

            previous.link = newNode;
        }
    }

    // Get the head object in our queue
    public Node getHead() {
        return this.head;
    }

    // Get the total number of queued items (Counts all that have been queued historically, even those that have been deleted)
    public int getCount() {
        return this.count;
    }



    // Remove top item from our queue.
    public void removeHead()  {
        if (this.head == null) {
            return;
        }

        if (this.head.link == null) {
            this.head = null;
        } else {
            this.head = this.head.link;
        }
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }
}




