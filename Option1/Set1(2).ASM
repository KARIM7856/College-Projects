

.data
    charsize : .asciiz "enter the array size = " 
    numLowerCase : .asciiz "the number of lowercase letters =  "
    numUpperCase : .asciiz "the number of uppercase letters =  "
    newLine : .asciiz "\n"
    charArray : .word 0:100
.text

.globl main
main:

    li  $v0, 4
    la  $a0, charsize
    syscall

    add $t0, $0, 0       # counter

    li  $v0, 5
    syscall
    add $t1, $0, $v0    # size of the array
    mul $t1, $t1, 4     # $t1 = size * 4

    while:
        beq		$t0, $t1, secLoop	# if $t0 == $t1 then secLoop
            li  $v0, 12
            syscall                     # take input from user
            sb	$v0, charArray($t0)		# store input in the array
            addi    $t0, $t0, 4         # $t0 = $t0 + 4
            j   while
    
    secLoop:
        li		$v0, 4		        # $v0 = 4
        la		$a0, newLine		# 
        syscall                     # print new line
        add $t0, $0, 0          # counter
        add $s0, $0, 0          # number of lower case letters
        add $s1, $0, 0          # number of upper case letters
        la	$t2, charArray		# 
        
        while1:
        bgt		$t0, $t1, exit	# if $t0 > $t1 then exit
            lb		$t2, charArray($t0)		    # load input from array
            addi    $t0, $t0, 4                 # $t0 = $t0 + 4

            blt		$t2, 'a', upcase            # if $t2 < a then upcase
                addi     $s0, $s0, 1            # $s0 = $s0 + 1
                j   while1

            upcase:                             
                blt    $t2, 'A', notChar        # if $t2 < A then notchar
                    addi    $s1, $s1, 1         # $s1 = $s1 + 1
                    j while1

                notChar:
                    j while1

    exit:
    li  $v0, 4
    la		$a0, numLowerCase		# 
    syscall

    li  $v0, 1
    move 	$a0, $s0	            # $a0 = $s0
    syscall                         # print number of lower case letters

    li $v0, 4
    la		$a0, newLine
    syscall		# 
    

    li  $v0, 4
    la		$a0, numUpperCase		# 
    syscall

    li  $v0, 1
    move 	$a0, $s1	            # $a0 = $s1
    syscall                         # print number of upper case letters

    li $v0, 4
    la		$a0, newLine
    syscall		# 
    
    li $v0, 10
    syscall
    
.end main
            
        