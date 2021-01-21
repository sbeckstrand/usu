import java.util.*;
import java.io.File;

public class LadderGame {
    static int maxWordSize = 15;
    ArrayList<String>[] allArrays;

    // Define constructor of our LadderGame class so that we can create game objects. User will be able to choose to either play the game or print out words from our dictionary using this object. In this constructor, we will read in a dictionary file and create an array of arrays. Each child array contains words of the same length.
    public LadderGame(String file) {
        allArrays = new ArrayList[maxWordSize];

        for (int i = 0; i < maxWordSize; i++) allArrays[i] = new ArrayList<String>();

        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNext()) {
                String word = reader.next();
                if (word.length() < maxWordSize) {
                    allArrays[word.length()].add(word);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // With our standard play method, the user will provide two strings and our game will attempt to find a ladder between them.
    public void play(String a, String b, String type) {
        int queueCount = 0;
        Map<String, Integer> alreadyTried = new HashMap<String, Integer>();
        // First, we check that the two words are the same length and that they do not exceed our max size.
        if (a.length() != b.length()) {
            System.out.println("Words are not the same length");
            return;
        }
        if (a.length() >= maxWordSize) {
            System.out.println("Word is larger than maximum allowed (" + maxWordSize + ")");
            return;
        }
        // Next, we create a copy of our array so that we can modify the dictionary to remove words we have found. If both of our words are in our dictionary, proceed with create a node for the first word and adding it to our queue.


        ArrayList<String> dictionary = new ArrayList<String>();
        dictionary.addAll(allArrays[a.length()]);

        if (dictionary.contains(a) && dictionary.contains(b)) {

            // Code that is executed if we specified to use an AVL tree
            if (type == "avl") {
                System.out.println("Seeking a solution from " + a + " -> " + b + " using AVL Tree:");

                AVLTree queue = new AVLTree();
                int priority = priority(0, a, b);
                WordInfo firstWord = new WordInfo(a, 0, a, priority);
                queue.insert(firstWord);
                queueCount += 1;
                alreadyTried.put(a, 0);
                findLink(queue, b, dictionary, alreadyTried, queueCount);
            }

            // Code that is executed if we specify to use a Linked List
            if (type == "linkedList") {
                System.out.println("Seeking a solution from " + a + " -> " + b + " using Linked List");
                LinkedList queue = new LinkedList();
                WordInfo firstWordInfo = new WordInfo(a, 0, a);
                queue.add(firstWordInfo);
                findLink(queue, b, dictionary);
            }


        } else {
            System.out.println("Impossible to find a link between " + a + " -> " + b);
            System.out.println("Please provide two valid words.\n");
        }



    }

    private int priority(int moves, String a, String b) {
        int wordLength = a.length();
        int sameCount = 0;
        for (int i = 0; i < wordLength; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sameCount++;
            }
        }

        return moves + (wordLength - sameCount);

    }

    // Our secondary play constructor will take a length argument and generate two strings on the user's behalf and then try to find a ladder between them.
    private void findLink(LinkedList queue, String target, ArrayList<String> dictionary) {
        String noLink = "Sorry, a link could not be found between these words.\n";
        if (!queue.isEmpty()) {
            LinkedList.Node current = queue.getHead();
            if (current.getInfo().word.equals(target)) {
                System.out.println(current.getInfo().history);
                System.out.println("Number of Moves: " + current.getInfo().moves);
                System.out.println("Queued: " + queue.getCount() + "\n");
                return;
            }
            ArrayList<String> toDelete = new ArrayList<String>();
            for (String word: dictionary) {
                if (isAdjacent(word, current.info.word)) {
                    WordInfo info = new WordInfo(word, current.info.moves + 1, current.info.history + " --> "+ word);
                    queue.add(info);
                    toDelete.add(word);
                }
            }
            for (String word: toDelete) {
                dictionary.remove(word);
            }
            queue.removeHead();
            findLink(queue, target, dictionary);
        }
        else {
            System.out.println(noLink);
        }
    }

    // The meat and potatoes of our application. This method will recursively check each item in our queue and add all adjacent words to the end of the queue. If we find our target word, the method returns the ladder details. If we go through the whole queue without finding the target, we return that there is not a ladder between them.
    private void findLink(AVLTree queue, String target, ArrayList<String> dictionary, Map alreadyTried, int queueCount) {
        String noLink = "Sorry, a link could not be found between these words.\n";

        if (!queue.isEmpty()) {
            WordInfo current = (WordInfo) queue.findMin();
            queue.deleteMin();

            if (current.word.equals(target)) {
                System.out.println(current.history);
                System.out.println("Number of Moves: " + current.moves);
                System.out.println("Queued: " + queueCount);
                System.out.println("Number of items in list: " + dictionary.size() + "\n") ;
                return;
            }



            for (String word: dictionary) {

                boolean cont;
                if (alreadyTried.containsKey(word)) {
                    if ((int) alreadyTried.get(word) > current.moves + 1) {
                        cont = true;
                    } else {
                        cont = false;
                    }
                } else {
                    cont = true;
                }

                if (isAdjacent(word, current.word) && cont) {
                    WordInfo info = new WordInfo(word, current.moves + 1, current.history + " --> "+ word, priority(current.moves, word, target));
                    queue.insert(info);
                    queueCount += 1;
                    alreadyTried.put(word, info.moves);
                }
            }

            findLink(queue, target, dictionary, alreadyTried, queueCount);
        }
        else {
            System.out.println(noLink);
        }
    }

    // This method will output the first x number of words in our dictionary.
    public void listWords(int count, int list) {
        ArrayList<String> fullList = allArrays[list];
        String[] previewList = new String[count];

        for (int i = 0; i < count; i++) {
            previewList[i] = fullList.get(i);
        }
        System.out.println("Printing first " + count + " items in our dictionary");
        System.out.println(Arrays.toString(previewList) + "\n");
    }

    // This method will check to see if two words are adjacent, meaning that there is only one letter difference between them.
    private boolean isAdjacent(String a, String b) {
        int count = 0;
        int wordLength = a.length();
        for (int i = 0; i < wordLength; i++) {
            if ( a.charAt(i) == b.charAt(i)) {
                count += 1;
            }
        }

        if (count == wordLength - 1) {
            return true;
        } else {
            return false;
        }
    }


}
