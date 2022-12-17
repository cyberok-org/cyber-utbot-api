package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ZeroDivisionTest {
    ///region Test suites for executable org.testcases.ifs.ZeroDivision.fun
    
    ///region
    
    @Test
    public void testFun1() {
        ZeroDivision zeroDivision = new ZeroDivision();
        
        boolean actual = zeroDivision.fun(-255, 0);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        ZeroDivision zeroDivision = new ZeroDivision();
        
        boolean actual = zeroDivision.fun(83, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        ZeroDivision zeroDivision = new ZeroDivision();
        
        boolean actual = zeroDivision.fun(-1, 1);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun4() {
        ZeroDivision zeroDivision = new ZeroDivision();
        
        /* This test fails because method [org.testcases.ifs.ZeroDivision.fun] produces [java.lang.ArithmeticException: / by zero]
            org.testcases.ifs.ZeroDivision.fun(ZeroDivision.java:6) */
        zeroDivision.fun(-255, Integer.MAX_VALUE);
    }
    ///endregion
    
    ///endregion
}

