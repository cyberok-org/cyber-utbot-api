package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LoopTest {
    ///region Test suites for executable org.example.Loop.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(java.lang.String, int)
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("example")
    public void testFun() {
        Loop loop = new Loop();
        String string = "100";
        
        boolean actual = loop.fun(string, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("example2")
    public void testFun_1() {
        Loop loop = new Loop();
        String string = "FILEEEEEEEE";
        
        boolean actual = loop.fun(string, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("example")
    public void testFun_2() {
        Loop loop = new Loop();
        String string = "1000";
        
        boolean actual = loop.fun(string, -255);
        
        assertTrue(actual);
    }
    ///endregion
    
    ///endregion
}

