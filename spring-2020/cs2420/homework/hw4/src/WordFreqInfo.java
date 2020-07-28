import java.util.*;
public class WordFreqInfo {
    public String word;
    public int occurCt;
    ArrayList<Freq> followList;

    // Constructor which tracks the word, count and an array of its following words.
    public WordFreqInfo(String word, int count) {
        this.word = word;
        this.occurCt = count;
        this.followList = new ArrayList<Freq>();
    }

    // Method to print out the word and its followers
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word: " + word);
        sb.append(" [" + occurCt + "] Followed By: ");
        for (Freq f : followList)
            sb.append(f.toString());
        return sb.toString();
    }

    // Update the following array list with the Freq object which tracks the following words and their occurance count.
    public void updateFollows(String follow) {
        boolean updated = false;
        //System.out.println("updateFollows " + word + " " + follow);
        for (Freq f : followList) {
            if (follow.compareTo(f.follow) == 0) {
                f.followCt++;
                updated = true;
            }
        }
        if (!updated)
            followList.add(new Freq(follow, 1));
    }
    public static class Freq {
        String follow;
        int followCt;
        public Freq(String follow, int ct) {
            this.follow = follow;
            this.followCt = ct;
        }
        public String toString() {
            return follow + " [" + followCt + "], ";
        }
        public boolean equals(Freq f2) {
            return this.follow.equals(f2.follow);
        }
    }
}