package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LoopTest {
    ///region Test suites for executable org.example.Loop.whileLoop
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method whileLoop(int)
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.returnsFrom {@code return sum;}
 *  */
    @Test
    @DisplayName("whileLoop: -> return sum")
    public void testWhileLoop_ReturnSum() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(0);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.iterates iterate the loop {@code while(i < k)} once
 * @utbot.returnsFrom {@code return sum;}
 *  */
    @Test
    @DisplayName("whileLoop: -> return sum")
    public void testWhileLoop_IterateWhileLoop() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(1);
        
        assertEquals(0, actual);
    }
    ///endregion
    
    ///endregion
}

