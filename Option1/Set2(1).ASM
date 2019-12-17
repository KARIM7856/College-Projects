.data
    printg : .ascii "g = "
    printh : .ascii "h = "
    newLine : .ascii "\n"
.text

.globl main
    
    main:
        
        li    $v0, 5 
        syscall
        add   $s0, $0, $v0      # g

        li    $v0, 5 
        syscall
        add   $s1, $0, $v0      # h

        blt		$s0, $s1, secCondition	# if $s0 < $s1 then secCondition
            addi	$s1, $s0, 0			# $s1 = $s0 + 0  --> h = g
            j       Exit
            
        secCondition:
            bgt		$s0, 0, target	# if $s0 > 0 then target
                addi	$s1, $s0, 0			# $s1 = $s0 + 0  --> h = g
                j       Exit
            
            target:
                addi	$s0, $s1, 0			# $s0 = $s1 + 0  --> g = h
                j       Exit

        
    Exit:

        li      $v0, 4
        la		$a0, newLine		# 
        syscall
        
        li      $v0, 4
        la		$a0, printg		# 
        syscall

        li      $v0, 1
        addi	$a0, $s0, 0			# $a0 = $s0 + 0  --> print g
        syscall
                
        li      $v0, 4
        la		$a0, newLine		# 
        syscall

        li      $v0, 4
        la		$a0, printh		# 
        syscall

        li      $v0, 1
        addi	$a0, $s1, 0			# $a0 = $s1 + 0  --> print h
        syscall

        li      $v0, 10
        syscall
