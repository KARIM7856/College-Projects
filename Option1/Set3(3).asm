.data
    newLine : .ascii "\n"
    array : .word 0:100

.text
.globl main
    
    main:
        
        li      $v0, 5 
        syscall
        add     $s0, $0, $v0          # $s0 = a
        mul     $s0, $s0, 4

        addi	$s1, $0, 1			# $s1 = $0 + 1
        addi	$t0, $0, -4			# $t0 = $0 + -4 --> i = -4
        
        
        loop1:
            bgt		$t0, $s0, PrintArray	    # if $t0 < $s0 then PrintArray   --> i < a
            addi	$t0, $t0, 4			# $t0 = $t0 + 4
            #add		$t1, $t0, $0		# $t1 = $t0 + $0    --> j = i
            div     $t1, $t0, 4
            
            loop2:
                blt		$t1, 0, loop1	# if $t1 <= 0 then loop1
                    
                    mul     $s1, $s1, $t1


                    sw		$s1, array($t0)		# 
                    sub		$t1, $t1, 1		    # $t1 = $t1 - 1
                    j		loop2				# jump to loop2
        

        PrintArray:

            li		$v0, 4		# $v0 = 4
            la		$a0, newLine		# 
            syscall

            addi	$t0, $0, 0          # t0 = 0
        
            la		$s1, array		# 

            loop3:
                bgt		$t0, $s0, Exit	        # if $t0 < $s0 then Exit


                    lw		$s1, array($t0)		# 
                    addi	$t0, $t0, 4			# $t0 = $t0 + 4
                    
                    li		$v0, 1		        # $v0 = 1
                    move 	$a0, $s1		    # $a0 = $s1
                    syscall

                    li		$v0, 4		# $v0 = 4
                    la		$a0, newLine		# 
                    syscall

                    j		loop3				# jump to loop3
            
    Exit:
        li		$v0, 10		# $v0 = 10
        syscall
                    
