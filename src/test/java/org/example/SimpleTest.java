package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public final class SimpleTest {
    ///region Test suites for executable org.example.Simple.main
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method main(java.lang.String)
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain() {
        Simple simple = new Simple();
        String string = "a";
        
        simple.main(string);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain_1() {
        Simple simple = new Simple();
        String string = "  !  !  ! ";
        
        simple.main(string);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain_2() {
        Simple simple = new Simple();
        String string = "zz                            ";
        
        simple.main(string);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain_3() {
        Simple simple = new Simple();
        String string = "zzy";
        
        simple.main(string);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain_4() {
        Simple simple = new Simple();
        String string = "zzzz                     ! ";
        
        simple.main(string);
    }
    
    /**
    @utbot.classUnderTest {@link Simple}
 * @utbot.methodUnderTest {@link org.example.Simple#main(java.lang.String)}
 *  */
    @Test
    @DisplayName("main: ")
    @org.cyber.utils.VulnerabilityInfo("simple")
    public void testMain_5() {
        Simple simple = new Simple();
        String string = "zzzzzzz                     ";
        
        simple.main(string);
    }
    ///endregion
    
    ///endregion
}

