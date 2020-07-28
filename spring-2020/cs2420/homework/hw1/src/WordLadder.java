public class WordLadder {
    public static void main(String[] args) {
        int randomCount = 7;
        LadderGame game = new LadderGame("dictionary.txt");

        // Test that we properly split the dictionary into arrays of words of the same length. Find the first 10 words of the array of words of 6 letters:
        game.listWords(20,6);

        //Test predefined word combinations
//        game.play("oops", "tots");
//        game.play("ride", "ands");
//        game.play("happily", "angrily");
//        game.play("slow", "fast");
//        game.play("stone", "money");
////        game.play("biff", "axel");
//
//        game.play("kiss", "woof");
//        game.play("kiss", "woof");
        game.play("jura", "such");

//        //   Test using randomly generated words:
//        for (int i = 3; i < randomCount; i++) {
//            game.play(i);
//        }
    }
}
