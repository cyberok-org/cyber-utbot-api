package org.testcases.cycle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NestedTest {
    ///region Test suites for executable org.testcases.cycle.Nested.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int)
    
    /**
    @utbot.classUnderTest {@link Nested}
 * @utbot.methodUnderTest {@link org.testcases.cycle.Nested#fun(int)}
 * @utbot.executesCondition {@code (x > 0): True}
 *  */
    @Test
    @DisplayName("fun: x > 0 : True -> return false")
    public void testFun_XGreaterThanZero() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(1);
        
        assertFalse(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Nested}
 * @utbot.methodUnderTest {@link org.testcases.cycle.Nested#fun(int)}
 * @utbot.executesCondition {@code (x > 0): False}
 * @utbot.iterates iterate the loop {@code for(int i = 0; i < 5; i++)} once
 *  */
    @Test
    @DisplayName("fun: x == 125 : True -> return true")
    public void testFun_XEquals125() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(0);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Nested}
 * @utbot.methodUnderTest {@link org.testcases.cycle.Nested#fun(int)}
 * @utbot.executesCondition {@code (x > 0): False}
 * @utbot.iterates iterate the loop {@code for(int i = 0; i < 5; i++)} once
 *  */
    @Test
    @DisplayName("fun: x > 0 : False -> return false")
    public void testFun_ReturnFalse() {
        Nested nested = new Nested();
        
        boolean actual = nested.fun(-256);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

