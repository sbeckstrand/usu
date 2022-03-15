.data 
first: 		.asciiz "\nGuess a number between 1 and 100: "
low: 		.asciiz "\nToo low, guess again: "
high: 		.asciiz "\nToo high, guess again: "
correct1: 	.asciiz "\nCorrect! It took you "
correct2:	.asciiz " guesses to find "
period:		.asciiz "."
playagain:		.asciiz "\nWould you like to play again? (Y/N): "
goodbye:	.asciiz "\nGoodbye!"

random: 	.word 0
guess:		.word 0
count:		.word 0

.text
# Main Loop
main: 	
	# Set Random Number
	li $a0, 1
	li $a1, 100
	li $v0, 42
	syscall
	sw $a0, random
	
	# First Guess
	li $v0, 4
	la $a0, first
	syscall
	li $v0, 5
	syscall
	sw $v0, guess
	lw $t0, count
	add $t0, $t0, 1
	sw $t0, count
	
guesscheck: 	
	# Check if guess is greater than, less than, or correct and jump to label
	lw $t0, guess
	lw $t1, random
	blt $t0, $t1, less
	bgt $t0, $t1, greater
	j correct
	
	# If guess was less than the random number, guess again 
	less:
	li $v0, 4
	la $a0, low
	syscall
	li $v0, 5
	syscall
	sw $v0, guess
	j increment

	# If guess was greater than the random number, guess again 
	greater:
	li $v0, 4
	la $a0, high
	syscall
	li $v0, 5
	syscall
	sw $v0, guess
	
	# Increment guess count and then loop back to checking if guess matches the random number
	increment:
	lw $t0, count
	add $t0, $t0, 1
	sw $t0, count
	j guesscheck
	
correct:
	# If answer was correct, print winning message
	li $v0, 4
	la $a0, correct1
	syscall
	li $v0, 1
	lw $a0, count
	syscall 
	li $v0, 4
	la $a0, correct2
	syscall
	li $v0, 1
	lw $a0, random
	syscall 
	li $v0, 4
	la $a0, period
	syscall

prompt:
	# Prompt to play again
	li $v0, 4
	la $a0, playagain
	syscall
	li $v0, 12
	syscall

	beq $v0, 'Y', main
	beq $v0, 'y', main
	beq $v0, 'N', done
	beq $v0, 'n', done
	j prompt

done:
	# If answered no, print exit message and exit
	li $v0, 4
	la $a0, goodbye
	syscall
	
	li $v0, 10
    	syscall
	
	
 
