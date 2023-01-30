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
 * @utbot.executesCondition {@code (k >= 0): True}
 * @utbot.invokes org.example.Loop#fun2(int)
 *  */
    @Test
    @DisplayName("whileLoop: k >= 0 : True -> LoopFun2")
    public void testWhileLoop_KGreaterOrEqualZero() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(0);
        
        assertEquals(0, actual);
    }
    ///endregion
    
    ///endregion
}

