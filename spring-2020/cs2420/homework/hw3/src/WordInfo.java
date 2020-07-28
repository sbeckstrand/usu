
// This method is used to specify the information for each of our words, including the word itself, how many moves it took to get it, the history leading up to the word and its priority.
public class WordInfo implements Comparable<WordInfo> {
    public String word;
    public int moves;
    public String history;
    public Integer priority;

    public WordInfo(String word, int moves, String history) {
        this.word = word;
        this.moves = moves;
        this.history = history;
    }

    public WordInfo(String word, int moves, String history, int priority)  {
        this.word = word;
        this.moves = moves;
        this.history = history;
        this.priority = priority;
    }

    public String toString(){
        return "Word " + word    + " Moves " +moves  + " History ["+history +"]";
    }

    @Override
    public int compareTo(WordInfo o) {
        return this.priority.compareTo(o.priority);
    }

    public int getPriority() {
        return this.priority;
    }

    public String getWord()  {
        return this.word;
    }

    public String getHistory() {
        return this.history;
    }

}
