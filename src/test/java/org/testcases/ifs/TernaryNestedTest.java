package org.testcases.ifs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TernaryNestedTest {
    ///region Test suites for executable org.testcases.ifs.TernaryNested.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int, int)
    
    /**
    @utbot.classUnderTest {@link TernaryNested}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryNested#fun(int,int,int)}
 * @utbot.executesCondition {@code (z == 4): False}
 * @utbot.returnsFrom {@code return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5);}
 *  */
    @Test
    @DisplayName("fun: z == 4 : False -> return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5)")
    public void testFun_ZNotEquals4() {
        TernaryNested ternaryNested = new TernaryNested();
        
        int actual = ternaryNested.fun(-255, -255, -255);
        
        assertEquals(5, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryNested}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryNested#fun(int,int,int)}
 * @utbot.returnsFrom {@code return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5);}
 *  */
    @Test
    @DisplayName("fun: -> return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5)")
    public void testFun_XNotEquals2() {
        TernaryNested ternaryNested = new TernaryNested();
        
        int actual = ternaryNested.fun(2, -255, -255);
        
        assertEquals(2, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryNested}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryNested#fun(int,int,int)}
 * @utbot.returnsFrom {@code return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5);}
 *  */
    @Test
    @DisplayName("fun: -> return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5)")
    public void testFun_XEquals2() {
        TernaryNested ternaryNested = new TernaryNested();
        
        int actual = ternaryNested.fun(2, 3, -255);
        
        assertEquals(1, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryNested}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryNested#fun(int,int,int)}
 * @utbot.executesCondition {@code (z == 4): True}
 * @utbot.returnsFrom {@code return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5);}
 *  */
    @Test
    @DisplayName("fun: z == 4 : True -> return x == 2 ? (y == 3 ? 1 : 2) : (z == 4 ? 3 : 5)")
    public void testFun_ZEquals4() {
        TernaryNested ternaryNested = new TernaryNested();
        
        int actual = ternaryNested.fun(-255, -255, 4);
        
        assertEquals(3, actual);
    }
    ///endregion
    
    ///endregion
}

