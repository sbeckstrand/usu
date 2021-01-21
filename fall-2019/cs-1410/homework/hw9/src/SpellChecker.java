import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SpellChecker {
    public static void main(String[] args) throws FileNotFoundException {

        // Step 1: Demonstrate tree capabilities
        testTree();
        System.out.println("\n");

        // Step 2: Read the dictionary and report the tree statistics
        BinarySearchTree<String> dictionary = readDictionary();
        System.out.println("--- Dictionary Binary Tree ---");
        reportTreeStats(dictionary);

        // Step 3: Perform spell checking
        spellCheck(dictionary);
    }

    /**
     * This method is used to help develop the BST, also for the grader to
     * evaluate if the BST is performing correctly.
     */
    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    /**
     * This method is used to report on some stats about the BST
     */
    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

    /**
     * This method reads the dictionary and constructs the BST to be
     * used for the spell checking.
     */
    public static BinarySearchTree<String> readDictionary() throws FileNotFoundException {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> dictionaryArray = new ArrayList<String>();
        File dictionary = new File("Dictionary.txt");
        java.util.Scanner input = new java.util.Scanner(dictionary);
        while (input.hasNextLine()) {
            String word = input.nextLine();
            dictionaryArray.add(word);
        }

        java.util.Collections.shuffle(dictionaryArray, new java.util.Random(System.currentTimeMillis()));
        for (Iterator<String> i = dictionaryArray.iterator(); i.hasNext();) {
            String word = i.next();
            tree.insert(word);
        }

        return tree;
    }

    /**
     * This method is used to read the contents of our letter and check which words exist in our dictionary.
     * Any words that do not exist in the dictionary are output to the console.
     */
    public static void spellCheck(BinarySearchTree<String> dictionary ) throws FileNotFoundException {
        List<String> letterArray = new ArrayList<String>();
        File letter = new File("Letter.txt");
        java.util.Scanner input = new java.util.Scanner(letter);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String w : words) {
                letterArray.add(w);
            }
        }

        System.out.println("\n--- Words not found in dictionary: ---");
        for (String word : letterArray) {
            boolean spellCheck = dictionary.search(word);

            if (!spellCheck) {
                System.out.println(word);
            }
        }
    }
}

