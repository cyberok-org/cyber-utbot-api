package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ExampleTest {
    ///region Test suites for executable org.example.checks.Example.example
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method example(java.lang.String, int)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(java.lang.String,int)}
 * @utbot.executesCondition {@code (s.length() > 3): False}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("example: s.length() > 3 : False -> return false")
    public void testExample_SLengthLessOrEqual3() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        String string = "";
        
        boolean actual = example.example(string, -255);
        
        assertFalse(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(java.lang.String,int)}
 * @utbot.executesCondition {@code (s.length() > 3): True}
 * @utbot.executesCondition {@code (x > 100): False}
 * @utbot.returnsFrom {@code return false;}
 *  */
    @Test
    @DisplayName("example: x > 100 : False -> return false")
    public void testExample_XLessOrEqual100() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        String string = "    ";
        
        boolean actual = example.example(string, 100);
        
        assertFalse(actual);
    }
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(java.lang.String,int)}
 * @utbot.executesCondition {@code (s.length() > 3): True}
 * @utbot.executesCondition {@code (x > 100): True}
 * @utbot.invokes org.example.checks.Example#internal(java.lang.String,int)
 * @utbot.returnsFrom {@code return true;}
 *  */
    @Test
    @DisplayName("example: x > 100 : True -> return true")
    public void testExample_XGreaterThan100() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        String string = "    ";
        
        boolean actual = example.example(string, 101);
        
        assertTrue(actual);
    }
    ///endregion
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method example(java.lang.String, int)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#example(java.lang.String,int)}
 * @utbot.invokes {@link java.lang.String#length()}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: s.length() > 3 && x > 100
 *  */
    @Test
    @DisplayName("example: s.length() > 3 && x > 100 -> ThrowNullPointerException")
    public void testExample_StringLength() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        
        /* This test fails because method [org.example.checks.Example.example] produces [java.lang.NullPointerException]
            org.example.checks.Example.example(Example.java:46) */
        example.example(null, -255);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.checks.Example.internal
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method internal(java.lang.String, int)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#internal(java.lang.String,int)}
 *  */
    @Test
    @DisplayName("internal: ")
    public void testInternal() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        
        Class exampleClazz = Class.forName("org.example.checks.Example");
        Class stringType = Class.forName("java.lang.String");
        Class intType = int.class;
        Method internalMethod = exampleClazz.getDeclaredMethod("internal", stringType, intType);
        internalMethod.setAccessible(true);
        java.lang.Object[] internalMethodArguments = new java.lang.Object[2];
        internalMethodArguments[0] = ((Object) null);
        internalMethodArguments[1] = -255;
        internalMethod.invoke(example, internalMethodArguments);
    }
    ///endregion
    
    ///endregion
    
    ///region Util methods
    
    private static Object createInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return Class.forName("sun.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class)
            .invoke(getUnsafeInstance(), clazz);
    }
    
    private static Object getUnsafeInstance() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field f = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return f.get(null);
    }
    ///endregion
}

