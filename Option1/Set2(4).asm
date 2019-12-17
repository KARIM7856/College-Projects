.data
    odd : .ascii "is odd"
    even : .ascii "is even"
    newLine : .ascii "\n"
.text
.globl main
    
    main:
        
        li    $v0, 5 
        syscall
        add   $a0, $0, $v0          # n

        jal		isOdd				# jump to isOdd and save position to $ra
        
        beq		$v1, -1, PrintOdd	# if $v1 == 1 then PrintOdd
            beq		$v1, 0, PrintEven	# if $v0 == 0 then PrintEven
                j		Exit				# jump to Exit
            PrintEven:

                li		$v0, 1		# $v0 = 1
                move 	$a0, $v1		# $a0 = $v1
                syscall

                li		$v0, 4		# $v0 = 4
                la		$a0, even		# 
                syscall
                j		Exit				# jump to Exit
            
        PrintOdd:
            li		$v0, 4		# $v0 = 4
            la		$a0, odd		# 
            syscall
            j		Exit				# jump to Exit


    Exit:
        li  $v0, 10
        syscall
        

        isOdd:

            addi	$sp, $sp, -4			# $sp = $sp + -4
            sw		$ra, ($sp)		# 
            
            
            jal		isEven				# jump to isEven and save position to $ra
            lw		$ra, ($sp)		# 
            addi	$sp, $sp, 4			# $sp = $sp + 4
            
           
            
            addi	$v1, $v0, 0			# $v1 = $v0 + 0
            jr		$ra					# jump to $ra
            
                        
            
        isEven:
            addi	$t1, $a0, 0			# $t1 = $a0 + 0
            addi	$t2, $0, 2			# $t2 = $0 + 2
            
            
            while:
                ble		$t1, 0, target	# if $a0 <= 0 then target
                    sub 	$t1, $t1, $t2			# $a0 = $a0 - 2
                    j while
            target:
                addi	$v0, $t1, 0			# $v0 = $t1 + 0
                jr		$ra					# jump to $ra
            
            

            


            