### Three things that would result in more sensible language

With our particular design of our application there are several things that are not accounted for that would help to improve the the overall quality of our generated poems.

1) We are not adhering to rules of the English language, such as what is necessary for a complete sentence, proper punctuation breaks, etc. 
2) There is not any emotion or though behind the words. It is all algorithm based where we are taking from a pool of existing words and generating based on order of occurrence. We are not actively thinking about the meaning behind the words being used
3) We are not able to evulate bridge words or additions that might make the poem make more sense. Instead, we are working soley with the words provided to us where if we introduced a logic to include minor words like a, the, and, etc, we might be able to bridge certain words to make phrases that make more sense.

### Why are some generated poems more coherent than others?

Well, it is important to note that our application is designed to generate poems based on words that follow each other and the words that occur more often are more likely to be used. This suggests that there is a fair chance of forming sentences and words that are similar or the same as the original poem, or at least words are used that make sense together. It seems a poem often loses its quality when lower probability words happen to be selected.

It is also somewhat of a numbers game. Given enough attempts it is likely a higher quality poem will be generated, similar to if you are rolling a dice a high number of times, at some point you will probably roll a 6 if you roll the dice enough.
