package org.example.inter;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Inter2Test {
    ///region Test suites for executable org.example.inter.Inter2.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter2 inter2 = new Inter2();
        
        /* This test fails because method [org.example.inter.Inter2.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter2.doGet(Inter2.java:38) */
        inter2.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter2 inter2 = new Inter2();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter2.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter2.doGet(Inter2.java:40) */
        inter2.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        Inter2 inter2 = new Inter2();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter2.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter2.id(Inter2.java:48)
            org.example.inter.Inter2.doGet(Inter2.java:41) */
        inter2.doGet(httpServletRequestMock, httpServletResponseMock);
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter2 inter2 = new Inter2();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter2.doGet] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@3321afde with arguments [null]]
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
                org.example.inter.Inter2.id(Inter2.java:48)
                org.example.inter.Inter2.doGet(Inter2.java:41) */
            inter2.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter2.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter2 inter2 = new Inter2();
        
        String actual = inter2.getDescription();
        
        String expected = "simple id method call";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter2.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter2 inter2 = new Inter2();
        
        int actual = inter2.getVulnerabilityCount();
        
        assertEquals(2, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter2.id
    
    
    @Test
    public void testId1() throws Throwable  {
        Inter2 inter2 = new Inter2();
        
        /* This test fails because method [org.example.inter.Inter2.id] produces [java.lang.NullPointerException]
            org.example.inter.Inter2.id(Inter2.java:48) */
        Class inter2Clazz = Class.forName("org.example.inter.Inter2");
        Class stringType = Class.forName("java.lang.String");
        Class printWriterType = Class.forName("java.io.PrintWriter");
        Method idMethod = inter2Clazz.getDeclaredMethod("id", stringType, printWriterType);
        idMethod.setAccessible(true);
        java.lang.Object[] idMethodArguments = new java.lang.Object[2];
        idMethodArguments[0] = ((Object) null);
        idMethodArguments[1] = ((Object) null);
        try {
            idMethod.invoke(inter2, idMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testId2() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter2 inter2 = new Inter2();
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            
            /* This test fails because method [org.example.inter.Inter2.id] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@5540583a with arguments [null]]
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
                org.example.inter.Inter2.id(Inter2.java:48) */
            Class inter2Clazz = Class.forName("org.example.inter.Inter2");
            Class stringType = Class.forName("java.lang.String");
            Class printWriterMock1Type = Class.forName("java.io.PrintWriter");
            Method idMethod = inter2Clazz.getDeclaredMethod("id", stringType, printWriterMock1Type);
            idMethod.setAccessible(true);
            java.lang.Object[] idMethodArguments = new java.lang.Object[2];
            idMethodArguments[0] = ((Object) null);
            idMethodArguments[1] = printWriterMock1;
            try {
                idMethod.invoke(inter2, idMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
}

