.data 
msg: .ascii "\nHello, World!, My name is Stephen Beckstrand and my major is computer science!\n"

.text
main:
li $v0, 4
la $a0, msg
syscall

li $v0, 10
syscall