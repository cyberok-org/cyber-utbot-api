package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Loop2Test {
    ///region Test suites for executable org.example.Loop2.whileLoop
    
    ///region
    
    @Test
    public void testWhileLoop1() {
        Loop2 loop2 = new Loop2();
        
        int actual = loop2.whileLoop(0);
        
        assertEquals(0, actual);
    }
    
    @Test
    public void testWhileLoop2() {
        Loop2 loop2 = new Loop2();
        
        int actual = loop2.whileLoop(1);
        
        assertEquals(0, actual);
    }
    ///endregion
    
    ///endregion
}

