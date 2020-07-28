#### Assignment 1: Word Ladder

Your assignment is to build an application that discovers word ladders.   The word ladder application accepts two words of the same length and then attempts to find the shortest word ladder connecting the two. Each word in the ladder should differ from the previous one by just one letter, and every single rung should be some word in the dictionary. If there are two or more shortest paths, then you only need to print one of them.

Input: use the test cases shown in `TestLadder.java`.  You will have two options:
- a.   Find a ladder between two specific words.
- b.   Find a ladder between two randomly selected words of a given length.

Input: dictionary.txt contains a list of possible words.  Read them in and separate them into lists of different word lengths.

Output:slow->fast    6 Moves  [slow flow flaw flat fiat fist fast ] total enqueues 1512

#### Implementation

The word ladder application should use a standard algorithm known as breadth-first search. Explore all words that are one away from the first word.  Then explore all words that are two away from the first word.  And so forth.

Use a linked list as a queue to store partial ladders that represent possibilities to explore. Each node of the queue is a partial ladder.  For example, in finding the word ladder from slow->fast, after  twenty  enqueues (and two dequeues) , my queue looks like:

```
1.   Word flow Moves 1 History [slow flow ]
2.   Word glow Moves 1 History [slow glow ]
3.   Word plow Moves 1 History [slow plow ]
4.   Word scow Moves 1 History [slow scow ]
5.   Word show Moves 1 History [slow show ]
6.   Word slaw Moves 1 History [slow slaw ]
7.   Word slew Moves 1 History [slow slew ]
8.   Word slob Moves 1 History [slow slob ]
9.   Word sloe Moves 1 History [slow sloe ]
10.  Word slog Moves 1 History [slow slog ]
11.  Word slop Moves 1 History [slow slop ]
12.  Word slot Moves 1 History [slow slot ]
13.  Word snow Moves 1 History [slow snow ]
14.  Word stow Moves 1 History [slow stow ]
15.  Word aloe Moves 2 History [slow alow aloe ]
16.  Word avow Moves 2 History [slow alow avow ]
17.  Word slow Moves 2 History [slow alow slow ]
18.  Word blaw Moves 2 History [slow blow blaw ]
```

The ladders are appended in order of length so that as you remove from the front, you will consider shorter ladders before longer ones.  After another five enqueues, my queue looks like:

```
1.   Word flow Moves 1 History [slow flow ]
2.   Word glow Moves 1 History [slow glow ]
3.   Word plow Moves 1 History [slow plow ]
4.   Word scow Moves 1 History [slow scow ]
5.   Word show Moves 1 History [slow show ]
6.   Word slaw Moves 1 History [slow slaw ]
7.   Word slew Moves 1 History [slow slew ]
8.   Word slob Moves 1 History [slow slob ]
9.   Word sloe Moves 1 History [slow sloe ]
10.  Word slog Moves 1 History [slow slog ]
11.  Word slop Moves 1 History [slow slop ]
12.  Word slot Moves 1 History [slow slot ]
13.  Word snow Moves 1 History [slow snow ]
14.  Word stow Moves 1 History [slow stow ]
15.  Word aloe Moves 2 History [slow alow aloe ]
16.  Word avow Moves 2 History [slow alow avow ]
17.  Word slow Moves 2 History [slow alow slow ]
18.  Word blaw Moves 2 History [slow blow blaw ]
19.  Word blew Moves 2 History [slow blow blew ]
20.  Word blob Moves 2 History [slow blow blob ]
21.  Word bloc Moves 2 History [slow blow bloc ]
22.  Word blot Moves 2 History [slow blow blot ]
23.  Word brow Moves 2 History [slow blow brow ]
```

If the last word for a ladder is the target destination word, your job is done and you have found a complete ladder. It must be the shortest one because you appended ladders in order of increasing length.

If you exhaust the queue of possibilities without having found a completed ladder, then no ladder exists. (Note that two words of differing length can't possibly have a ladder, so you can eliminate this case without searching.)

Let's make the algorithm a bit more concrete with some pseudo-code:

```
Void findLadder(String start, String final){
  create initial ladder (just start word) and enqueue it on the partialSolution queue
  while (!queue.isEmpty() && !done){       
    extract front ladder from queue (this is shortest partial ladder)       for each unused word in dictionary that differs by one char from last word     {
      if (word ==final) {
        done=true;  
        print solution;
        break;         
      }        

      extend the current ladder by appending new word        
      append this ladder onto end of the partialSolution queue     
    }      
  }
}
```

#### Implementation Requirements:

You must implement a queue using a linked list (using next pointers) for the list of partial solutions.

You must parameterize similar methods so you have one version that has multiple behaviors rather than multiple versions of similar code.

You must have enough outputting capabilities to do the following:o    Determine how many words were read from dictionary.txt of a given size.   

Be able to display all the words which are one-away from a given word

Be able to display the contents of  the partialSolution list.

This is critical for your own sanity. If you come to me for help, I will ask what is working by relying on this information.
- You must use the style guidelines.
- Consider using toString to allow every data structure to print itself easily.

#### Input/OutputInput:

use the test cases shown in TestLadder.java.Output

If a ladder is possible, show the shortest ladder found.

If a ladder is impossible, state why:
- Words not found in dictionaryo    
- Words not the same lengtho    
-No ladder was found

#### Hints:

I found words that were one away, by simply going through the dictionary and checking if the word was one away from the word in question.  

I also cloned the word list I was to use and deleted a word once it was part of a word ladder.  

Since I considered word ladders in order or length, there would never be a shorter word ladder that needed the same word.

When I removed an item from my word list, I had to make sure I didn’t miss the next word (as everything shifts).
