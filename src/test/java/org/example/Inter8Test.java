package org.example.inter;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Inter8Test {
    ///region Test suites for executable org.example.inter.Inter8.id
    
    
    @Test
    public void testId1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Inter8 inter8 = new Inter8();
        
        Class inter8Clazz = Class.forName("org.example.inter.Inter8");
        Class stringType = Class.forName("java.lang.String");
        Method idMethod = inter8Clazz.getDeclaredMethod("id", stringType);
        idMethod.setAccessible(true);
        java.lang.Object[] idMethodArguments = new java.lang.Object[1];
        idMethodArguments[0] = ((Object) null);
        String actual = ((String) idMethod.invoke(inter8, idMethodArguments));
        
        assertNull(actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter8 inter8 = new Inter8();
        
        /* This test fails because method [org.example.inter.Inter8.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter8.doGet(Inter8.java:38) */
        inter8.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter8 inter8 = new Inter8();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter8.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter8.doGet(Inter8.java:43) */
        inter8.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        Inter8 inter8 = new Inter8();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter8.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter8.doGet(Inter8.java:44) */
        inter8.doGet(httpServletRequestMock, httpServletResponseMock);
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter8 inter8 = new Inter8();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter8.doGet] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@4006b870 with arguments [null]]
                org.mockito.internal.util.reflection.InstrumentationMemberAccessor.invoke(InstrumentationMemberAccessor.java:252)
                org.mockito.internal.util.reflection.ModuleMemberAccessor.invoke(ModuleMemberAccessor.java:55)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.tryInvoke(MockMethodAdvice.java:333)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.access$500(MockMethodAdvice.java:60)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice$RealMethodCall.invoke(MockMethodAdvice.java:253)
                org.mockito.internal.invocation.InterceptedInvocation.callRealMethod(InterceptedInvocation.java:142)
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:110)
                org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)
                org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:34)
                org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:82)
                org.mockito.internal.creation.bytebuddy.MockMethodAdvice.handle(MockMethodAdvice.java:151)
                java.base/java.io.PrintWriter.println(PrintWriter.java:821)
                org.example.inter.Inter8.doGet(Inter8.java:44) */
            inter8.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.foo
    
    
    @Test
    public void testFoo1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Inter8 inter8 = new Inter8();
        
        Class inter8Clazz = Class.forName("org.example.inter.Inter8");
        Class stringType = Class.forName("java.lang.String");
        Method fooMethod = inter8Clazz.getDeclaredMethod("foo", stringType);
        fooMethod.setAccessible(true);
        java.lang.Object[] fooMethodArguments = new java.lang.Object[1];
        fooMethodArguments[0] = ((Object) null);
        String actual = ((String) fooMethod.invoke(inter8, fooMethodArguments));
        
        assertNull(actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter8 inter8 = new Inter8();
        
        String actual = inter8.getDescription();
        
        String expected = "multi-level context sensitivity test";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter8 inter8 = new Inter8();
        
        int actual = inter8.getVulnerabilityCount();
        
        assertEquals(1, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.id2
    
    
    @Test
    public void testId21() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Inter8 inter8 = new Inter8();
        
        Class inter8Clazz = Class.forName("org.example.inter.Inter8");
        Class stringType = Class.forName("java.lang.String");
        Method id2Method = inter8Clazz.getDeclaredMethod("id2", stringType);
        id2Method.setAccessible(true);
        java.lang.Object[] id2MethodArguments = new java.lang.Object[1];
        id2MethodArguments[0] = ((Object) null);
        String actual = ((String) id2Method.invoke(inter8, id2MethodArguments));
        
        assertNull(actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter8.bar
    
    
    @Test
    public void testBar1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Inter8 inter8 = new Inter8();
        
        Class inter8Clazz = Class.forName("org.example.inter.Inter8");
        Class stringType = Class.forName("java.lang.String");
        Method barMethod = inter8Clazz.getDeclaredMethod("bar", stringType);
        barMethod.setAccessible(true);
        java.lang.Object[] barMethodArguments = new java.lang.Object[1];
        barMethodArguments[0] = ((Object) null);
        String actual = ((String) barMethod.invoke(inter8, barMethodArguments));
        
        assertNull(actual);
    }
    
    ///endregion
}

