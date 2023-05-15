package org.example.inter;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Inter4Test {
    ///region Test suites for executable org.example.inter.Inter4.f
    
    
    @Test
    public void testF1() throws Throwable  {
        Inter4 inter4 = new Inter4();
        
        /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.NullPointerException]
            org.example.inter.Inter4.f(Inter4.java:48) */
        Class inter4Clazz = Class.forName("org.example.inter.Inter4");
        Class servletResponseType = Class.forName("javax.servlet.ServletResponse");
        Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseType);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[1];
        fMethodArguments[0] = ((Object) null);
        try {
            fMethod.invoke(inter4, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF2() throws Throwable  {
        Inter4 inter4 = new Inter4();
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@370e682f with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            org.example.inter.Inter4.f(Inter4.java:48) */
        Class inter4Clazz = Class.forName("org.example.inter.Inter4");
        Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[1];
        fMethodArguments[0] = noBodyResponseMock;
        try {
            fMethod.invoke(inter4, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF3() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter4 inter4 = new Inter4();
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@3e77cc32 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                org.example.inter.Inter4.f(Inter4.java:48) */
            Class inter4Clazz = Class.forName("org.example.inter.Inter4");
            Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[1];
            fMethodArguments[0] = noBodyResponseMock;
            try {
                fMethod.invoke(inter4, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF4() throws Throwable  {
        Inter4 inter4 = new Inter4();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        
        /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@62fc2748 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter4.f(Inter4.java:48) */
        Class inter4Clazz = Class.forName("org.example.inter.Inter4");
        Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[1];
        fMethodArguments[0] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter4, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF5() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter4 inter4 = new Inter4();
            HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            
            /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@bcc31f6 with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter4.f(Inter4.java:48) */
            Class inter4Clazz = Class.forName("org.example.inter.Inter4");
            Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[1];
            fMethodArguments[0] = httpServletResponseWrapper;
            try {
                fMethod.invoke(inter4, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testF6() throws Throwable  {
        Inter4 inter4 = new Inter4();
        HttpServletResponseWrapper httpServletResponseWrapper = ((HttpServletResponseWrapper) createInstance("javax.servlet.http.HttpServletResponseWrapper"));
        ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
        Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
        Object noBodyResponseMock = mock(noBodyResponseClazz);
        Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
        Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
        getWriterMethod.setAccessible(true);
        java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
        (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(((PrintWriter) null));
        setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
        setField(httpServletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper);
        
        /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@7bfed866 with arguments []]
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
            javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
            org.example.inter.Inter4.f(Inter4.java:48) */
        Class inter4Clazz = Class.forName("org.example.inter.Inter4");
        Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
        fMethod.setAccessible(true);
        java.lang.Object[] fMethodArguments = new java.lang.Object[1];
        fMethodArguments[0] = httpServletResponseWrapper;
        try {
            fMethod.invoke(inter4, fMethodArguments);
        } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getTargetException();
        }
    }
    
    @Test
    public void testF7() throws Throwable  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter4 inter4 = new Inter4();
            ServletResponseWrapper servletResponseWrapper = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            ServletResponseWrapper servletResponseWrapper1 = ((ServletResponseWrapper) createInstance("javax.servlet.ServletResponseWrapper"));
            Class noBodyResponseClazz = Class.forName("javax.servlet.http.NoBodyResponse");
            Object noBodyResponseMock = mock(noBodyResponseClazz);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            Class servletResponseClazz = Class.forName("javax.servlet.ServletResponse");
            Method getWriterMethod = servletResponseClazz.getDeclaredMethod("getWriter");
            getWriterMethod.setAccessible(true);
            java.lang.Object[] getWriterMethodArguments = new java.lang.Object[0];
            (when(getWriterMethod.invoke(noBodyResponseMock, getWriterMethodArguments))).thenReturn(printWriterMock1);
            setField(servletResponseWrapper1, "javax.servlet.ServletResponseWrapper", "response", noBodyResponseMock);
            setField(servletResponseWrapper, "javax.servlet.ServletResponseWrapper", "response", servletResponseWrapper1);
            
            /* This test fails because method [org.example.inter.Inter4.f] produces [java.lang.IllegalStateException: Could not invoke public java.io.PrintWriter javax.servlet.http.NoBodyResponse.getWriter() throws java.io.UnsupportedEncodingException on javax.servlet.http.NoBodyResponse@2aa2662a with arguments []]
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
                javax.servlet.http.NoBodyResponse.getWriter(HttpServlet.java:885)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                javax.servlet.ServletResponseWrapper.getWriter(ServletResponseWrapper.java:152)
                org.example.inter.Inter4.f(Inter4.java:48) */
            Class inter4Clazz = Class.forName("org.example.inter.Inter4");
            Method fMethod = inter4Clazz.getDeclaredMethod("f", servletResponseClazz);
            fMethod.setAccessible(true);
            java.lang.Object[] fMethodArguments = new java.lang.Object[1];
            fMethodArguments[0] = servletResponseWrapper;
            try {
                fMethod.invoke(inter4, fMethodArguments);
            } catch (java.lang.reflect.InvocationTargetException invocationTargetException) {
                throw invocationTargetException.getTargetException();
            }
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter4.sink
    
    
    @Test
    public void testSink1() {
        Inter4 inter4 = new Inter4();
        
        inter4.sink(null);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter4.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Inter4 inter4 = new Inter4();
        
        /* This test fails because method [org.example.inter.Inter4.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter4.doGet(Inter4.java:42) */
        inter4.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Inter4 inter4 = new Inter4();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Inter4.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter4.f(Inter4.java:49)
            org.example.inter.Inter4.doGet(Inter4.java:44) */
        inter4.doGet(httpServletRequestMock, httpServletResponseMock);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        Inter4 inter4 = new Inter4();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Inter4.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Inter4.f(Inter4.java:48)
            org.example.inter.Inter4.doGet(Inter4.java:44) */
        inter4.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class, (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
            });
            Inter4 inter4 = new Inter4();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            /* This test fails because method [org.example.inter.Inter4.doGet] produces [java.lang.IllegalStateException: Could not invoke public void java.io.PrintWriter.println(java.lang.String) on java.io.PrintWriter@537736ae with arguments [null]]
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
                org.example.inter.Inter4.f(Inter4.java:49)
                org.example.inter.Inter4.doGet(Inter4.java:44) */
            inter4.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter4.getDescription
    
    
    @Test
    public void testGetDescription1() {
        Inter4 inter4 = new Inter4();
        
        String actual = inter4.getDescription();
        
        String expected = "store stuff in a field";
        assertEquals(expected, actual);
    }
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.Inter4.getVulnerabilityCount
    
    
    @Test
    public void testGetVulnerabilityCount1() {
        Inter4 inter4 = new Inter4();
        
        int actual = inter4.getVulnerabilityCount();
        
        assertEquals(2, actual);
    }
    
    ///endregion
    
    ///region Util methods
    
    private static Object createInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return Class.forName("sun.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class)
            .invoke(getUnsafeInstance(), clazz);
    }
    
        private static void setField(Object object, String fieldClassName, String fieldName, Object fieldValue) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class<?> clazz = Class.forName(fieldClassName);
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
    
        java.lang.reflect.Field modifiersField;
        
                java.lang.reflect.Method methodForGetDeclaredFields25110367162300 = java.lang.Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
                methodForGetDeclaredFields25110367162300.setAccessible(true);
                java.lang.reflect.Field[] allFieldsFromFieldClass25110367171500 = (java.lang.reflect.Field[]) methodForGetDeclaredFields25110367162300.invoke(java.lang.reflect.Field.class, false);
                modifiersField = java.util.Arrays.stream(allFieldsFromFieldClass25110367171500).filter(field1 -> field1.getName().equals("modifiers")).findFirst().get();
    
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
    
        field.setAccessible(true);
        field.set(object, fieldValue);
    }
    
    private static Object getUnsafeInstance() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field f = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return f.get(null);
    }
    ///endregion
}

