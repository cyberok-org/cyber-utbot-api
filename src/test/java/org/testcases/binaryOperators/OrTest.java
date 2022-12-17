package org.testcases.binaryOperators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class OrTest {
    ///region Test suites for executable org.testcases.binaryOperators.Or.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int, int)
    
    /**
    @utbot.classUnderTest {@link Or}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.Or#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.returnsFrom {@code return true;}
 *  */
    @Test
    @DisplayName("fun: x == y : False -> return true")
    public void testFun_XEqualsY() {
        Or or = new Or();
        
        boolean actual = or.fun(-254, -254, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Or}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.Or#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (y == z): False}
 * @utbot.executesCondition {@code (z == x): True}
 * @utbot.returnsFrom {@code return true;}
 *  */
    @Test
    @DisplayName("fun: z == x : True -> return true")
    public void testFun_ZEqualsX() {
        Or or = new Or();
        
        boolean actual = or.fun(-252, 4, -252);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Or}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.Or#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (y == z): True}
 * @utbot.returnsFrom {@code return true;}
 *  */
    @Test
    @DisplayName("fun: y == z : False -> return true")
    public void testFun_YEqualsZ() {
        Or or = new Or();
        
        boolean actual = or.fun(-254, -253, -253);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Or}
 * @utbot.methodUnderTest {@link org.testcases.binaryOperators.Or#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (y == z): False}
 * @utbot.executesCondition {@code (z == x): False}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("fun: z == x : False -> return false")
    public void testFun_ZNotEqualsX() {
        Or or = new Or();
        
        boolean actual = or.fun(-223, -219, -224);
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

