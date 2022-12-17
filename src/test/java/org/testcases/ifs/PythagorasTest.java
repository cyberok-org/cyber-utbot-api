package org.testcases.ifs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class PythagorasTest {
    ///region Test suites for executable org.testcases.ifs.Pythagoras.fun
    
    ///region
    
    @Test
    public void testFun1() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(0, -255, -255);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun2() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(1, 0, -255);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun3() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(4, 65536, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun4() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(4, 4, 65536);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun5() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(170, 107, 256);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun6() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(32, 60, 68);
        
        assertTrue(actual);
    }
    
    @Test
    public void testFun7() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(65536, 1, 1);
        
        assertFalse(actual);
    }
    
    @Test
    public void testFun8() {
        Pythagoras pythagoras = new Pythagoras();
        
        boolean actual = pythagoras.fun(1, 1, 0);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

