public class hw3 {

    public static void main(String args[]) {
        // Define two Objects to use with our LadderGame. One will be used to generate an AVL tree, the other will be used to create a Linked List.
        LadderGame game = new LadderGame("dictionary.txt");
        LadderGame gameLL = new LadderGame("dictionary.txt");


        // Try to find links between the provided Words and provide output for them. While attempting to compare the results between the new AVL tree method and the old Linked List I discovered that a StackOverflow error will occur with my old Linked List code in certain cases but I am unable to determine exactly why. The new code hw3 for AVL tree works as intended. The first couple examples only use the AVL tree queue while the rest will impliment the Linked List.
        System.out.println("#######################################");
        game.play("kiss", "woof", "avl");

        System.out.println("#######################################");
        game.play("cock", "numb", "avl");

        System.out.println("#######################################");
        game.play("jura", "such", "avl");
        gameLL.play("jura", "such", "linkedList");

        System.out.println("#######################################");
        game.play("stet", "whey", "avl");
        gameLL.play("stet", "whey", "linkedList");

        System.out.println("#######################################");
        game.play("rums", "numb", "avl");
        gameLL.play("rums", "numb", "linkedList");

        System.out.println("#######################################");
        game.play("stone", "money", "avl");
        gameLL.play("stone", "money", "linkedList");

    }
}
