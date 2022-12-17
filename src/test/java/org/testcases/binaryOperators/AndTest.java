package org.testcases.binaryOperators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class AndTest {
    ///region Test suites for executable org.testcases.binaryOperators.And.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int, int)
    
    /**
    @utbot.classUnderTest {@link And}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.And#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: x == y : False -> return false")
    public void testFun_XNotEqualsY() {
        And and = new And();
        
        boolean actual = and.fun(1, -128, -192);
        
        assertFalse(actual);
    }
    
    /**
    @utbot.classUnderTest {@link And}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.And#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.executesCondition {@code (y == z): True}
 * @utbot.returnsFrom {@code return true;}
 *  */
    @Test
    @DisplayName("fun: y == z : True -> return true")
    public void testFun_YEqualsZ() {
        And and = new And();
        
        boolean actual = and.fun(-255, -255, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link And}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.And#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.executesCondition {@code (y == z): False}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: y == z : False -> return false")
    public void testFun_YNotEqualsZ() {
        And and = new And();
        
        boolean actual = and.fun(-191, -191, -255);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

