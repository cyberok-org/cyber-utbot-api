package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class FermatsLastTheoremTest {
    ///region Test suites for executable org.testcases.ifs.FermatsLastTheorem.fun
    
    ///region
    
    @Test
    public void testFun1() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(0, -255, -255);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(1, 0, -255);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(1, 2048, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun4() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(1, 1, 2048);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun5() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(129, 1, 228);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun6() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(2049, 1, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun7() {
        FermatsLastTheorem fermatsLastTheorem = new FermatsLastTheorem();
        
        boolean actual = fermatsLastTheorem.fun(1, 1, 0);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

