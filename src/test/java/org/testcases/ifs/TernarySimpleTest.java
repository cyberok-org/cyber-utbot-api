package org.testcases.ifs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TernarySimpleTest {
    ///region Test suites for executable org.testcases.ifs.TernarySimple.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int)
    
    /**
    @utbot.classUnderTest {@link TernarySimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernarySimple#fun(int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (x >= y): False}
 * @utbot.returnsFrom {@code return a * b;}
 *  */
    @Test
    @DisplayName("fun: x == y : False -> return a * b")
    public void testFun_XLessThanY() {
        TernarySimple ternarySimple = new TernarySimple();
        
        int actual = ternarySimple.fun(-1, 0);
        
        assertEquals(10, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernarySimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernarySimple#fun(int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.executesCondition {@code (x >= y): True}
 * @utbot.returnsFrom {@code return a * b;}
 *  */
    @Test
    @DisplayName("fun: x == y : True -> return a * b")
    public void testFun_XGreaterOrEqualY() {
        TernarySimple ternarySimple = new TernarySimple();
        
        int actual = ternarySimple.fun(-255, -255);
        
        assertEquals(3, actual);
    }
    ///endregion
    
    ///endregion
}

