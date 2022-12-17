package org.example.dir;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Loop3Test {
    ///region Test suites for executable org.example.dir.Loop3.whileLoop
    
    ///region
    
    @Test
    public void testWhileLoop1() {
        Loop3 loop3 = new Loop3();
        
        int actual = loop3.whileLoop(0);
        
        assertEquals(0, actual);
    }
    
    @Test
    public void testWhileLoop2() {
        Loop3 loop3 = new Loop3();
        
        int actual = loop3.whileLoop(134217728);
        
        assertEquals(-67108864, actual);
    }
    
    @Test
    public void testWhileLoop3() {
        Loop3 loop3 = new Loop3();
        
        int actual = loop3.whileLoop(1);
        
        assertEquals(0, actual);
    }
    ///endregion
    
    ///endregion
}

