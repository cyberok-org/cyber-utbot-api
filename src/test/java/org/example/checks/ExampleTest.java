package org.example.checks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;

public final class ExampleTest {
    ///region Test suites for executable org.example.checks.Example.doGet
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    /**
    @utbot.classUnderTest {@link Example}
 * @utbot.methodUnderTest {@link org.example.checks.Example#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.invokes {@link javax.servlet.http.HttpServletRequest#getParameter(java.lang.String)}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: String s = req.getParameter("name");
 *  */
    @Test
    @DisplayName("doGet: s = req.getParameter(\"name\") : True -> ThrowNullPointerException")
    public void testDoGet_HttpServletRequestGetParameter() throws Exception  {
        Example example = ((Example) createInstance("org.example.checks.Example"));
        
        /* This test fails because method [org.example.checks.Example.doGet] produces [java.lang.NullPointerException]
            org.example.checks.Example.doGet(Example.java:37) */
        example.doGet(null, null);
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

