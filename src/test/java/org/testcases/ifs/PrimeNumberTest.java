package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class PrimeNumberTest {
    ///region Test suites for executable org.testcases.ifs.PrimeNumber.fun
    
    ///region
    
    @Test
    public void testFun1() {
        PrimeNumber primeNumber = new PrimeNumber();
        
        boolean actual = primeNumber.fun(-2, -128);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        PrimeNumber primeNumber = new PrimeNumber();
        
        boolean actual = primeNumber.fun(-97, -1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        PrimeNumber primeNumber = new PrimeNumber();
        
        boolean actual = primeNumber.fun(1, 97);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun4() {
        PrimeNumber primeNumber = new PrimeNumber();
        
        boolean actual = primeNumber.fun(23, -1867377081);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun5() {
        PrimeNumber primeNumber = new PrimeNumber();
        
        boolean actual = primeNumber.fun(97, 1);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

