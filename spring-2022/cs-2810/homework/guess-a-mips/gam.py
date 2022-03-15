import random

playing = True

while playing:
    randnum = random.randint(1, 100)

    guess = int(input("Guess a number between 1 and 100: "))

    count = 1;

    while guess != randnum:
        if guess > randnum:
            guess = int(input("Too high, guess again: "))
        else:
            guess = int(input("Too low, guess again: "))
        
        count+= 1;

    print("Correct! It took you %d guesses to find %d" % (count, randnum))

    play_prompt = input("Play again? (Y/n): ")

    if play_prompt == "y":
        continue
    elif play_prompt == "n":
        playing = False

print("Goodbye!")
