.data
    printG : .ascii "g = "
.text
.globl main
    
    main:
        
        li    $v0, 5 
        syscall
        add   $s0, $0, $v0          # g

        li    $v0, 5 
        syscall
        add   $s1, $0, $v0          # h

        bge		$s0, $s1, gGreaterh	# if $s0 >= $s1 then gGreaterh
            addi	$t0, $0, 1			# $t0 = $0 + 1
            sub		$s0, $s0, $t0		# $s0 = $s0 - $t0
            j		Exit				# jump to Exit
        
        gGreaterh:
            addi	$s0, $s0, 1			# $s0 = $s0 + 1
            j		Exit				# jump to Exit
        

    Exit:
        li		$v0, 4		# $v0 = 4
        la		$a0, printG		# 
        syscall

        li		$v0, 1		# $v0 = 1
        move 	$a0, $s0		# $a0 = $s0 
        syscall

        li		$v0, 10		# $v0 = 10
        syscall
            
            
