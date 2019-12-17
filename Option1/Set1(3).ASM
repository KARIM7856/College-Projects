.data
    array : .word 0:100
    newLine : .ascii "\n"

.text
.globl main
    main:
        li    $v0, 5 
        syscall
        add   $s0, $0, $v0          # $s0 = a

        li    $v0, 5 
        syscall
        add   $s1, $0, $v0          # $s1 = b

        addi	$t0, $0, -4			# $t0 = $t0 + 0 --> i = -4
        addi    $t3, $0, -1         # i sub = 0
        mul     $s0, $s0, 4

        loop1:
            beq		$t0, $s0, PrintArray	# if i == a then PrintArray
                addi    $t0, $t0, 4         # i(index) += 4
                addi    $t3, $t3, 1          # i sub += 1
                mul     $t2, $t0, 2       # array index = i * 2
                addi    $t1, $0, 0         # j = 0

                loop2:
                    beq     $t1, $s1, loop1     # if j == b then go to loop1
                        sub		$s2, $t3, $t1		# $s2 = $t3 - $t1       --> $s2 = i - j
                        sw		$s2, array($t2)		# put $s2 in array index
                        addi    $t1, $t1, 1         # j += 1
                        j       loop2
                        

        PrintArray:
            addi	$t0, $0, 0     # i = 0
            addi    $t2, $2, 0     # index = 0

            while:
                beq		$t0, $s0, Exit
                    mul     $t2, $t0, 2
                    lw      $s2, array($t2)

                    addi    $t0, $t0, 4

                    li      $v0, 1
                    move 	$a0, $s2		# $a0 = $s2
                    syscall

                    li		$v0, 4		    # $v0 = 4
                    la		$a0, newLine		# 
                    syscall

                    j       while
        
        Exit:
            li  $v0, 10
            syscall



            
        