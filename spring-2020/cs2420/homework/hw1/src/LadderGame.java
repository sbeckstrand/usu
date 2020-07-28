import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class LadderGame {
    static int maxWordSize = 15;
    ArrayList<String>[] allArrays;
    Random random;

    // Define constructor of our LadderGame class so that we can create game objects. User will be able to choose to either play the game or print out words from our dictionary using this object. In this constructor, we will read in a dictionary file and create an array of arrays. Each child array contains words of the same length.
    public LadderGame(String file) {
        random = new Random();
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
    public void play(String a, String b) {

        // First, we check that the two words are the same length and that they do not exceed our max size.
        if (a.length() != b.length()) {
            System.out.println("Words are not the same length");
            return;
        }
        if (a.length() >= maxWordSize) {
            System.out.println("Word is larger than maximum allowed (" + maxWordSize + ")");
            return;
        }

        // Next, we create a copy of our array so that we can modify the dctionary to remove words we have found. If both of our words are in our dictionary, proceed with create a node for the first word and adding it to our queue.
        ArrayList<String> dictionary = new ArrayList<String>();
        dictionary.addAll(allArrays[a.length()]);

        if (dictionary.contains(a) && dictionary.contains(b)) {
            System.out.println("Seeking a solution from " + a + " -> " + b + ":");

            LinkedList queue = new LinkedList();
            WordInfo firstWordInfo = new WordInfo(a, 0, a);
            queue.add(firstWordInfo);

            findLink(queue, b, dictionary);
        } else {
            System.out.println("Impossible to find a link between " + a + " -> " + b);
            System.out.println("Please provide two valid words.\n");
        }



    }

    // Our secondary play constructor will take a length argument and generate two strings on the user's behalf and then try to find a ladder between them.
    public void play(int len) {
        if (len >= maxWordSize) return;
        ArrayList<String> list = allArrays[len];
        String a = list.get(random.nextInt(list.size()));
        String b = list.get(random.nextInt(list.size()));
        play(a, b);

    }

    // The meat and potatoes of our application. This method will recursively check each item in our queue and add all adjacent words to the end of the queue. If we find our target word, the method returns the ladder details. If we go through the whole queue without finding the target, we return that there is not a ladder between them.
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
