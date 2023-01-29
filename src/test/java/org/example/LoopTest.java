package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LoopTest {
    ///region Test suites for executable org.example.Loop.whileLoop
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method whileLoop(int)
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.executesCondition {@code (k >= 0): False}
 * @utbot.returnsFrom {@code return sum;}
 *  */
    @Test
    @DisplayName("whileLoop: k >= 0 : False -> return sum")
    public void testWhileLoop_KLessThanZero() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(-1);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.executesCondition {@code (k >= 0): True}
 *  */
    @Test
    @DisplayName("whileLoop: -> k >= 0 : True")
    public void testWhileLoop_KGreaterOrEqualZero() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(0);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.executesCondition {@code (k >= 0): True}
 * @utbot.iterates iterate the loop {@code while(i < k)} 100 times
 * @utbot.returnsFrom {@code return sum;}
 *  */
    @Test
    @DisplayName("whileLoop: k >= 0 : True -> return sum")
    public void testWhileLoop_KGreaterOrEqualZero_1() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(100);
        
        assertEquals(4950, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.Loop.fun2
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method fun2(int)
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#fun2(int)}
 *  */
    @Test
    @DisplayName("fun2: ")
    public void testFun2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Loop loop = new Loop();
        
        Class loopClazz = Class.forName("org.example.Loop");
        Class intType = int.class;
        Method fun2Method = loopClazz.getDeclaredMethod("fun2", intType);
        fun2Method.setAccessible(true);
        java.lang.Object[] fun2MethodArguments = new java.lang.Object[1];
        fun2MethodArguments[0] = -255;
        fun2Method.invoke(loop, fun2MethodArguments);
    }
    ///endregion
    
    ///endregion
}

