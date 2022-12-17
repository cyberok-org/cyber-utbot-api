package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class WithArithmeticTest {
    ///region Test suites for executable org.testcases.ifs.WithArithmetic.fun
    
    ///region
    
    @Test
    public void testFun1() {
        WithArithmetic withArithmetic = new WithArithmetic();
        
        boolean actual = withArithmetic.fun(-9, -7);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun2() {
        WithArithmetic withArithmetic = new WithArithmetic();
        
        boolean actual = withArithmetic.fun(135482997, -134482998);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun3() {
        WithArithmetic withArithmetic = new WithArithmetic();
        
        boolean actual = withArithmetic.fun(-96, -3);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun4() {
        WithArithmetic withArithmetic = new WithArithmetic();
        
        /* This test fails because method [org.testcases.ifs.WithArithmetic.fun] produces [java.lang.ArithmeticException: / by zero]
            org.testcases.ifs.WithArithmetic.fun(WithArithmetic.java:9) */
        withArithmetic.fun(-191, 0);
    }
    ///endregion
    
    ///endregion
}

