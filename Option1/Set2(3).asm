.data
    c : .word 0:100
    newLine : .ascii "\n"

.text
.globl main
    main:
        li    $v0, 5 
        syscall
        add   $s0, $0, $v0          # a
        mul   $s0, $s0, 4


        addi    $t0, $0, -4          # i = -4

        loop1:
            beq		$t0, $s0, PrintArray	# if i == a then Exit
                addi     $t0, $t0, 4       # i += 4
                addi    $t1, $0, 0          # j(index) = 0
                addi	$t2, $0, 0			# $t2 = $0 + 0
                addi	$s1, $0, 0		    # $s1 = $0 + 0

                loop2:
                    beq     $t1, $t0, loop1     # if j == i then go to loop1
                    add		$s1, $s1, $t2		# $s1 = $s1 + $t1       --> $s1 += j
                    sw		$s1, c($t0)		    # c[i] = $s1

                    addi	$t1, $t1, 4			# $t1 = $t1 + 4
                    addi	$t2, $t2, 1			# $t2 = $t2 + 1
                    j		loop2				# jump to loop2


        PrintArray:
            
            addi    $t0, $0, 0          # i = 0
            loop3:
                beq		$t0, $s0, Exit	    # if $t0 == $s0 then Exit

                    lw		$s2, c($t0)		# 
                    addi	$t0, $t0, 4		# $t0 = $t0 + 4

                    li		$v0, 1		    # $v0 = 4
                    move 	$a0, $s2		# $a0 = $s2
                    syscall

                    li		$v0, 4		# $v0 = 4
                    la		$a0, newLine		# 
                    syscall

                    j		loop3				# jump to loop3

        Exit:
        li		$v0, 10		# $v0 = 10
        syscall
                
                    
                    

                        