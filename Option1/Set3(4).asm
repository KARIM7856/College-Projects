.data

    newLine : .ascii "\n"

.text
.globl main
    
    main:
        
        li    $v0, 5 
        syscall
        add   $a0, $0, $v0          # n

        jal		fact				# jump to fact and save position to $ra
        add		$s0, $v0, $0		# $s0 = $v0 + $0
        
        li    $v0, 5 
        syscall
        add   $a0, $0, $v0          # n

        jal		fact				# jump to fact and save position to $ra
        add		$s1, $v0, $0		# $s1 = $v0 + $0

        add		$s2, $s0, $s1		# $s2 = $s0 + $s1
        
        li		$v0, 1		# $v0 = 1
        move 	$a0, $s2		# $a0 = $s2
        syscall

        li		$v0, 10		# $v0 = 10
        syscall
    
.end main

        fact:
            add		$t0, $a0, $0		# $t0 = $a0 + $0    --> i = n
            addi	$t1, $0, 1			# $t1 = $t1 + 1     --> result = 1

            loop:
                beq		$t0, 1, return	# if $t0 == 1 then return
                    mul     $t1, $t1, $t0
                    sub		$t0, $t0, 1		# $t0 = $t0 - 1
                    j		loop				# jump to loop
                    
                
                return:
                    add		$v0, $t1, $0		# $v0 = $t1 + $0
                    jr		$ra					# jump to $ra
                    
            
            