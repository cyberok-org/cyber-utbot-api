package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class InterproceduralTest {
    ///region Test suites for executable org.example.Interprocedural.lookupSwitch
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method lookupSwitch(int)
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#lookupSwitch(int)}
 * @utbot.executesCondition {@code (x == 4): False}
 * @utbot.returnsFrom {@code return 0;}
 *  */
    @Test
    @DisplayName("lookupSwitch: x == 4 : False -> return 0")
    public void testLookupSwitch_XNotEquals4() {
        Interprocedural interprocedural = new Interprocedural();
        
        int actual = interprocedural.lookupSwitch(-255);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#lookupSwitch(int)}
 * @utbot.executesCondition {@code (x == 4): True}
 * @utbot.returnsFrom {@code return 2;}
 *  */
    @Test
    @DisplayName("lookupSwitch: x == 4 : True -> return 2")
    public void testLookupSwitch_XEquals4() {
        Interprocedural interprocedural = new Interprocedural();
        
        int actual = interprocedural.lookupSwitch(4);
        
        assertEquals(2, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.Interprocedural.foo
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method foo(int)
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#foo(int)}
 * @utbot.executesCondition {@code (smth == 10): True}
 * @utbot.executesCondition {@code (y == 2): False}
 *  */
    @Test
    @DisplayName("foo: smth == 10 : True -> y == 2 : False")
    public void testFoo_YNotEquals2() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.smth = 10;
        
        interprocedural.foo(-255);
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#foo(int)}
 * @utbot.executesCondition {@code (smth == 10): True}
 * @utbot.executesCondition {@code (y == 2): True}
 * @utbot.invokes {@link org.example.Interprocedural#lookupSwitch(int)}
 *  */
    @Test
    @DisplayName("foo: y == 2 : True -> InterproceduralLookupSwitch")
    public void testFoo_YEquals2() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.smth = 10;
        
        interprocedural.foo(2);
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#foo(int)}
 * @utbot.executesCondition {@code (smth == 10): False}
 *  */
    @Test
    @DisplayName("foo: -> smth == 10 : False")
    public void testFoo_SmthNotEquals10() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.smth = -255;
        
        interprocedural.foo(-255);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.Interprocedural.bar
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method bar()
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#bar()}
 * @utbot.executesCondition {@code (some == 2): False}
 *  */
    @Test
    @DisplayName("bar: -> some == 2 : False")
    public void testBar_SomeNotEquals2() {
        Interprocedural interprocedural = new Interprocedural();
        
        interprocedural.bar();
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#bar()}
 * @utbot.executesCondition {@code (some == 2): True}
 *  */
    @Test
    @DisplayName("bar: -> some == 2 : True")
    public void testBar_SomeEquals2() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.some = 2;
        
        interprocedural.bar();
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#bar()}
 * @utbot.executesCondition {@code (some == 2): True}
 *  */
    @Test
    @DisplayName("bar: -> some == 2 : True")
    public void testBar_SomeEquals2_1() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.some = 2;
        interprocedural.smth = 10;
        
        interprocedural.bar();
    }
    
    /**
    @utbot.classUnderTest {@link Interprocedural}
 * @utbot.methodUnderTest {@link org.example.Interprocedural#bar()}
 * @utbot.executesCondition {@code (some == 2): False}
 *  */
    @Test
    @DisplayName("bar: -> some == 2 : False")
    public void testBar_SomeNotEquals2_1() {
        Interprocedural interprocedural = new Interprocedural();
        interprocedural.some = -255;
        interprocedural.smth = 10;
        
        interprocedural.bar();
    }
    ///endregion
    
    ///endregion
}

