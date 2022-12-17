package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NestedTest {
    ///region Test suites for executable org.testcases.ifs.Nested.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(256, 8);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(149, -130);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(182, 4);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun4() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(686329874, 686329874);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun5() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(20, 54);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun6() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(20, 4);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

