package org.testcases.formalGen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class SimpleTest {
    ///region Test suites for executable org.testcases.formalGen.Simple.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int)
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.testcases.formalGen.Simple#fun(int,int)}
 * @utbot.executesCondition {@code (x > 0): False}
 * @utbot.returnsFrom {@code return a + x;}
 *  */
    @Test
    @DisplayName("fun: x > 0 : False -> return a + x")
    public void testFun_XLessOrEqualZero() {
        Simple simple = new Simple();
        
        int actual = simple.fun(0, -255);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.testcases.formalGen.Simple#fun(int,int)}
 * @utbot.executesCondition {@code (x > 0): True}
 * @utbot.executesCondition {@code (x * 2 < 0): True}
 * @utbot.returnsFrom {@code return a + x;}
 *  */
    @Test
    @DisplayName("fun: x * 2 < 0 : True -> return a + x")
    public void testFun_XMultiply2LessThanZero() {
        Simple simple = new Simple();
        
        int actual = simple.fun(1073741824, -255);
        
        assertEquals(1073741569, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.testcases.formalGen.Simple#fun(int,int)}
 * @utbot.executesCondition {@code (x > 0): True}
 * @utbot.executesCondition {@code (x * 2 < 0): False}
 * @utbot.returnsFrom {@code return a + x;}
 *  */
    @Test
    @DisplayName("fun: x * 2 < 0 : False -> return a + x")
    public void testFun_XMultiply2GreaterOrEqualZero() {
        Simple simple = new Simple();
        
        int actual = simple.fun(1, -255);
        
        assertEquals(2, actual);
    }
    ///endregion
    
    ///endregion
}

