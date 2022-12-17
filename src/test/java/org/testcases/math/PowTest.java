package org.testcases.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class PowTest {
    ///region Test suites for executable org.testcases.math.Pow.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Pow pow = new Pow();
        
        boolean actual = pow.fun(4);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

