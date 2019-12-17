.data

.text
.globl main
    
    main:
        
        li    $v0, 5 
        syscall
        add   $a0, $0, $v0          # n

        jal		sumOdd				# jump to sumOdd and save position to $ra
        
        li      $v0, 1
        addi	$a0, $v1, 0			# $a0 = $v1 + 0
        syscall

    li  $v0, 10
    syscall
        

        sumOdd:

            addi	$t0, $zero, 0			# $t0 = $zero + 0 --> i
            addi    $t1, $zero, 0           # $t1 = $zero + 0 --> result
            addi	$s0, $a0, 0			    # $s0 = $a0 + 0

            while:
                beq		$t0, $s0, return	# if $t0 == $s0 then return
                    rem     $t2, $t0, 2         # $t2 = $t0 % 2

                    beq		$t2, 1, incResult	# if $t2 == 1 then incResult
                        addi    $t0, $t0, 1         # i += 1
                        j   while
                    incResult:
                        add     $t1, $t1, $t0       # result += i
                        addi    $t0, $t0, 1         # i += 1
                        j while
            return:
                addi	$v1, $t1, 0			# $v1 = $t1 + 0
                jr		$ra					# jump to $ra
            


            