import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {


    public String WritePoem(String textFile, String startingWord, int poemLength, Boolean printHash) {
        HashTable<String,WordFreqInfo> wordTable = new HashTable<>();
        String generatedPoem = "";
        Random random = new Random();

        // Read in file and track two words at a time. For the first word, we will send to our processWord method to update our hash table, the second word is added as its 'follower'. Once processed, change the following work to the current work and check the next follower.
        try {
            Scanner reader = new Scanner(new File(textFile));
            String first = null;
            String current = null;
            String next = null;
            while (reader.hasNext()) {
                String word = reader.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                if (first != null && next == null) {
                    next = word;
                }

                if (first == null) {
                    first = word;
                    current = first;
                }

                if (next != null) {
                    processWord(current, next, wordTable);
                    current = next;
                    next = null;
                }

                if (!reader.hasNext()) {
                    processWord(current,first, wordTable);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Take the provided starting word and generate a poem from it by randomly generating a number and using it to select one of the words following our word. Words that occur more often than others will have a higher chance of being included as the next word. Run through this loop as many times as requested by user.
        String currentWord = startingWord;
        for (int i = 0; i < poemLength; i++) {
            WordFreqInfo wordInfo = wordTable.find(currentWord);
            generatedPoem += currentWord + " ";
            int randomNumber = random.nextInt(wordInfo.occurCt) + 1;
            int currentIndex = 0;
            while (randomNumber > 0) {
                randomNumber = randomNumber - wordInfo.followList.get(currentIndex).followCt;
                if (randomNumber > 0) {
                    currentIndex += 1;
                }
            }
            currentWord = wordInfo.followList.get(currentIndex).follow;
        }

        System.out.println(String.format("################# %s #################", textFile));
        if (printHash) {
            return "\nGenerated Poem: " + generatedPoem + "\n\n" + wordTable.toString(wordTable.size());
        }
        return "Generated Poem: " + generatedPoem + "\n";
    }

    // This method will add our word to our hash table. If the word already exists as a key, it will increase its occurance count and add the following word. If the word does not exist as a key, it adds it with an occurance of 1 and adds the following word.
    private void processWord(String word, String next, HashTable<String, WordFreqInfo> wordTable) {
        if (wordTable.contains(word)) {
            WordFreqInfo currentWordInfo = wordTable.find(word);
            currentWordInfo.occurCt += 1;
            currentWordInfo.updateFollows(next);
        } else {
            WordFreqInfo currentWordInfo = new WordFreqInfo(word, 1);
            wordTable.insert(word, currentWordInfo);
            currentWordInfo.updateFollows(next);

        }
    }
}
