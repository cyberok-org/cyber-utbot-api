package org.testcases.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class PowOverlapTest {
    ///region Test suites for executable org.testcases.math.PowOverlap.fun
    
    ///region
    
    @Test
    public void testFun1() {
        PowOverlap powOverlap = new PowOverlap();
        
        boolean actual = powOverlap.fun(0);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

