.data
    charsize    : .asciiz  "enter the array size \n" 
    newLine     : .asciiz   "\n"
    charArray   : .word   0:100
    revArray    : .word    0:100
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
            add     $s3, $0, $v0
            sb	$s3, charArray($t0)		# 
            addi    $t0, $t0, 4
            j   while


    secLoop:
        li		$v0, 4		# $v0 = 4
        la		$a0, newLine		# 
        syscall
        addi	$t0, $0, 0			# $t0 = $0 + 0
        la		$s0, charArray		# 
        addi	$t2, $0, 0			# $t2 = $0 + 0
        addi	$s1, $0, 4			# $s1 = $0 + 4


        li		$v0, 4		# $v0 = 4
        la		$a0, newLine		# 
        syscall
        
        loop:
            blt		$t1, $t0, Print	# if $t1 >= $t0 then Print
                lb		$s0, charArray($t1)		# 
                sb		$s0, revArray($t2)		# 

                sub		$t1, $t1, $s1		# $t1 = $t1 - $s1   --> t0 -= 4
                addi	$t2, $t2, 4			# $t2 = $t2 + 4
                
                j		loop				# jump to loop
        
        Print:
            la		$s2, revArray		# 
            loop2:
                bge		$t0, $t2, Exit	# if $t0 >= $t2 then Exit
                    lb		$s2, revArray($t0)		# 
                    addi	$t0, $t0, 4			# $t0 = $t0 + 4
                
                    li		$v0, 11		# $v0 = 11
                    move 	$a0, $s2		# $a0 = $s2
                    syscall

                    j		loop2				# jump to loop2
        
        Exit:
            li		$v0, 10		# $v0 = 10
            syscall

                

                
        