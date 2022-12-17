package org.testcases.ifs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TernaryOneVarTest {
    ///region Test suites for executable org.testcases.ifs.TernaryOneVar.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int)
    
    /**
    @utbot.classUnderTest {@link TernaryOneVar}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryOneVar#fun(int)}
 * @utbot.executesCondition {@code (x == 2): False}
 * @utbot.executesCondition {@code (x >= 2): False}
 * @utbot.returnsFrom {@code return a * b;}
 *  */
    @Test
    @DisplayName("fun: x >= 2 : False -> return a * b")
    public void testFun_XLessThan2() {
        TernaryOneVar ternaryOneVar = new TernaryOneVar();
        
        int actual = ternaryOneVar.fun(1);
        
        assertEquals(10, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryOneVar}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryOneVar#fun(int)}
 * @utbot.executesCondition {@code (x == 2): False}
 * @utbot.executesCondition {@code (x >= 2): True}
 * @utbot.returnsFrom {@code return a * b;}
 *  */
    @Test
    @DisplayName("fun: x == 2 : False -> return a * b")
    public void testFun_XGreaterOrEqual2() {
        TernaryOneVar ternaryOneVar = new TernaryOneVar();
        
        int actual = ternaryOneVar.fun(3);
        
        assertEquals(6, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryOneVar}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryOneVar#fun(int)}
 * @utbot.executesCondition {@code (x == 2): True}
 * @utbot.executesCondition {@code (x >= 2): True}
 * @utbot.returnsFrom {@code return a * b;}
 *  */
    @Test
    @DisplayName("fun: x == 2 : True -> return a * b")
    public void testFun_XEquals2() {
        TernaryOneVar ternaryOneVar = new TernaryOneVar();
        
        int actual = ternaryOneVar.fun(2);
        
        assertEquals(3, actual);
    }
    ///endregion
    
    ///endregion
}

