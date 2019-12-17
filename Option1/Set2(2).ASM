.data
    charsize : .asciiz "enter the array size \n" 
    newLine : .asciiz "\n"
    charArray : .word 0:100
    lowCaseVowels : .word 'a', 'e', 'i', 'o', 'u'
    upCaseVowels : .word 'A', 'E', 'I', 'O', 'U'
    numOfVowels : .asciiz "the number of vowels = "
.text

.globl main
main:

    li  $v0, 4
    la  $a0, charsize
    syscall

    add $t0, $0, 0      # counter

    li  $v0, 5
    syscall
    add $t1, $0, $v0    # size of the array
    mul $t1, $t1, 4

    while:
        beq		$t0, $t1, secLoop	# if $t0 > $t1 then secLoop
            li  $v0, 12
            syscall
            sb	$v0, charArray($t0)		# 
            addi    $t0, $t0, 4
            j   while

    
     secLoop:
        add     $t0, $0, 0              # counter
        add     $s0, $0, 0              # number of vowels
        la	    $t2, charArray		    # array of characters
        la		$s1, lowCaseVowels		# array of lower case vowels
        la		$s2, lowCaseVowels		# array of upper case vowels
        
        while1:
        
        bgt		$t0, $t1, Exit	# if $t0 == $t1 then Exit
            lb		$t2, charArray($t0)		# 
            
            addi    $t0, $t0, 4
            
            add     $t3, $0, 0              # counter 2

            check_Vowel_Loop:

                bgt		$t3, 20, while1	# if $t3 == $t1 then while1
                
                lb      $s1, lowCaseVowels($t3)
                lb      $s2, upCaseVowels($t3)

                beq		$t2, $s1, lowCase       # if char in char array == char in lowCaseVowels then lowCase
                    beq		$t2, $s2, upCase	# if $t2 == $s2 (upCaseVowels) then upCase
                        addi	$t3, $t3, 4			# $t3 = $t3 + 4
                        j       check_Vowel_Loop
            
                lowCase:
                    addi    $s0, $s0, 1         # increment number of vowels by 1
                    j       while1

                upCase:
                    addi    $s0, $s0, 1         # increment number of vowels by 1
                    j       while1


    Exit:
        li		$v0, 4		# $v0 = 4
        la		$a0, numOfVowels		# 
        syscall

        li		$v0, 1		# $v0 = 1
        move 	$a0, $s0		# $a0 = $s0
        syscall

        li		$v0, 10		# $v0 = 10
        syscall
        