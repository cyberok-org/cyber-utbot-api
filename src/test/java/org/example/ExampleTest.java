package org.example;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ExampleTest {
    ///region Test suites for executable org.example.Example.fun
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun(java.lang.String, int)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.Example#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("extra vulnerability check")
    public void testFun() {
        Example example = new Example();
        String string = "100";
        
        boolean actual = example.fun(string, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.Example#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("extra vulnerability check")
    public void testFun_1() {
        Example example = new Example();
        String string = "500";
        
        boolean actual = example.fun(string, -255);
        
        assertTrue(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.Example#fun(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("fun: ")
    @org.cyber.utils.VulnerabilityInfo("extra vulnerability check")
    public void testFun_2() {
        Example example = new Example();
        String string = "-999";
        
        boolean actual = example.fun(string, -255);
        
        assertTrue(actual);
    }
    ///endregion
    ///endregion
}

