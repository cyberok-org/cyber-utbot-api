package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public final class LoopTest {
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

