

.data

.text
.globl main
    main:
        li    $v0, 5 
        syscall
        add   $s0, $0, $v0              # $s0 = g

        li    $v0, 5 
        syscall
        add   $s1, $0, $v0              # $s1 = h

        bgt   $s0, $s1, gGreaterh	    # if $s0 > s1 then gGreaterh

         blt    $s0, $s1, gLessh	    # if $s0 < s1 then gLessh
             mul $s0, $s0, $s1	        # $s0 = s0 * s1
             j		Exit				# jump to Exit
        
        gLessh:
            sub	$s0, $s0, $s1		    # $s0 = s0 - s1
            j		Exit				# jump to Exit

        gGreaterh:
             add    $s0, $s0, $s1       # $s0 = $s0 + $s1
             j		Exit				# jump to Exit

        Exit:
             li  $v0, 1		
             move	$a0, $s0            # print g
             syscall

             li		$v0, 10		# $v0 = 10
             syscall

.end main
  