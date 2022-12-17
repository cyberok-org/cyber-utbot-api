package org.testcases.ifs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TernaryManyVarsSimpleTest {
    ///region Test suites for executable org.testcases.ifs.TernaryManyVarsSimple.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(int, int, int)
    
    /**
    @utbot.classUnderTest {@link TernaryManyVarsSimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryManyVarsSimple#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.executesCondition {@code (x == z): False}
 * @utbot.executesCondition {@code (z == y): False}
 * @utbot.activatesSwitch {@code case 55}
 * @utbot.returnsFrom {@code return result;}
 *  */
    @Test
    @DisplayName("fun: switch(a * b * c) case: 55 -> return result")
    public void testFun_SwitchAbcCase55() {
        TernaryManyVarsSimple ternaryManyVarsSimple = new TernaryManyVarsSimple();
        
        int actual = ternaryManyVarsSimple.fun(-254, -254, -253);
        
        assertEquals(53, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryManyVarsSimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryManyVarsSimple#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (x == z): False}
 * @utbot.executesCondition {@code (z == y): False}
 * @utbot.activatesSwitch {@code case 110}
 * @utbot.returnsFrom {@code return result;}
 *  */
    @Test
    @DisplayName("fun: switch(a * b * c) case: 110 -> return result")
    public void testFun_SwitchAbcCase110() {
        TernaryManyVarsSimple ternaryManyVarsSimple = new TernaryManyVarsSimple();
        
        int actual = ternaryManyVarsSimple.fun(-189, -253, -254);
        
        assertEquals(105, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryManyVarsSimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryManyVarsSimple#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (x == z): False}
 * @utbot.executesCondition {@code (z == y): True}
 * @utbot.activatesSwitch {@code case 70}
 * @utbot.returnsFrom {@code return result;}
 *  */
    @Test
    @DisplayName("fun: switch(a * b * c) case: 70 -> return result")
    public void testFun_SwitchAbcCase70() {
        TernaryManyVarsSimple ternaryManyVarsSimple = new TernaryManyVarsSimple();
        
        int actual = ternaryManyVarsSimple.fun(-254, -253, -253);
        
        assertEquals(66, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryManyVarsSimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryManyVarsSimple#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): True}
 * @utbot.executesCondition {@code (x == z): True}
 * @utbot.executesCondition {@code (z == y): True}
 * @utbot.activatesSwitch {@code case 21}
 * @utbot.returnsFrom {@code return result;}
 *  */
    @Test
    @DisplayName("fun: switch(a * b * c) case: 21 -> return result")
    public void testFun_SwitchAbcCase21() {
        TernaryManyVarsSimple ternaryManyVarsSimple = new TernaryManyVarsSimple();
        
        int actual = ternaryManyVarsSimple.fun(-255, -255, -255);
        
        assertEquals(20, actual);
    }
    
    /**
    @utbot.classUnderTest {@link TernaryManyVarsSimple}
 * @utbot.methodUnderTest {@link org.testcases.ifs.TernaryManyVarsSimple#fun(int,int,int)}
 * @utbot.executesCondition {@code (x == y): False}
 * @utbot.executesCondition {@code (x == z): True}
 * @utbot.executesCondition {@code (z == y): False}
 * @utbot.activatesSwitch {@code case 66}
 * @utbot.returnsFrom {@code return result;}
 *  */
    @Test
    @DisplayName("fun: switch(a * b * c) case: 66 -> return result")
    public void testFun_SwitchAbcCase66() {
        TernaryManyVarsSimple ternaryManyVarsSimple = new TernaryManyVarsSimple();
        
        int actual = ternaryManyVarsSimple.fun(-254, -253, -254);
        
        assertEquals(63, actual);
    }
    ///endregion
    
    ///endregion
}

