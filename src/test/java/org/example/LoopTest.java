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
 *  */
    @Test
    @DisplayName("whileLoop: ")
    public void testWhileLoop() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(0);
        
        assertEquals(0, actual);
    }
    
    /**
    @utbot.classUnderTest {@link Loop}
 * @utbot.methodUnderTest {@link org.example.Loop#whileLoop(int)}
 * @utbot.iterates iterate the loop {@code while(i < k)} 33 times
 * @utbot.returnsFrom {@code return sum;}
 *  */
    @Test
    @DisplayName("whileLoop: -> return sum")
    public void testWhileLoop_ReturnSum() {
        Loop loop = new Loop();
        
        int actual = loop.whileLoop(33);
        
        assertEquals(528, actual);
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

