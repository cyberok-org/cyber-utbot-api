package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class ExampleTest {
    ///region Test suites for executable org.example.Example.doGet
    
    
    @Test
    public void testDoGet1() throws IOException  {
        Example example = new Example();
        
        /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
            org.example.Example.doGet(Example.java:15) */
        example.doGet(null, null);
    }
    
    @Test
    public void testDoGet2() throws IOException  {
        Example example = new Example();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
            org.example.Example.doGet(Example.java:16) */
        example.doGet(httpServletRequestMock, null);
    }
    
    @Test
    public void testDoGet3() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(true));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
            
            /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
                org.example.Example.write(Example.java:33)
                org.example.Example.foo(Example.java:22)
                org.example.Example.doGet(Example.java:17) */
            example.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
            
            /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
                org.example.Example.pseudoSanitize(Example.java:29)
                org.example.Example.foo(Example.java:24)
                org.example.Example.doGet(Example.java:17) */
            example.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet5() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            String string = "\u0000";
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
            
            /* This test fails because method [org.example.Example.doGet] produces [java.lang.StringIndexOutOfBoundsException: String index out of range: -1]
                java.base/java.lang.String.substring(String.java:1841)
                org.example.Example.pseudoSanitize(Example.java:29)
                org.example.Example.foo(Example.java:24)
                org.example.Example.doGet(Example.java:17) */
            example.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet6() throws IOException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(true));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock = mock(PrintWriter.class);
            ((doNothing()).when(printWriterMock)).write(org.mockito.ArgumentMatchers.any(String.class));
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock);
            
            PrintWriter initialExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            example.doGet(httpServletRequestMock, httpServletResponseMock);
            
            PrintWriter finalExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            assertFalse(initialExampleWriter == finalExampleWriter);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet7() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            String string = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
            
            /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
                org.example.Example.write(Example.java:33)
                org.example.Example.foo(Example.java:24)
                org.example.Example.doGet(Example.java:17) */
            example.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet8() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            String string = "\u0000\u0000";
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
            
            /* This test fails because method [org.example.Example.doGet] produces [java.lang.NullPointerException]
                org.example.Example.write(Example.java:33)
                org.example.Example.foo(Example.java:24)
                org.example.Example.doGet(Example.java:17) */
            example.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet9() throws Exception  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = ((Example) createInstance("org.example.Example"));
            PrintWriter printWriterMock = mock(PrintWriter.class);
            setField(example, "org.example.Example", "writer", printWriterMock);
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            String string = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            ((doNothing()).when(printWriterMock1)).write(org.mockito.ArgumentMatchers.any(String.class));
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            
            PrintWriter initialExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            example.doGet(httpServletRequestMock, httpServletResponseMock);
            
            PrintWriter finalExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            assertFalse(initialExampleWriter == finalExampleWriter);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testDoGet10() throws IOException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random randomMock, org.mockito.MockedConstruction.Context context) -> (when(randomMock.nextBoolean())).thenReturn(false));
            Example example = new Example();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            String string = "\u0000\u0000";
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(string);
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock = mock(PrintWriter.class);
            ((doNothing()).when(printWriterMock)).write(org.mockito.ArgumentMatchers.any(String.class));
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock);
            
            PrintWriter initialExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            example.doGet(httpServletRequestMock, httpServletResponseMock);
            
            PrintWriter finalExampleWriter = ((PrintWriter) getFieldValue(example, "org.example.Example", "writer"));
            
            assertFalse(initialExampleWriter == finalExampleWriter);
        } finally {
            mockedConstruction.close();
        }
    }
    
    ///endregion
    
    ///region Util methods
    
    private static Object getFieldValue(Object obj, String fieldClassName, String fieldName) throws ClassNotFoundException, NoSuchMethodException, java.lang.reflect.InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = Class.forName(fieldClassName);
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
        
        field.setAccessible(true);
        
        java.lang.reflect.Field modifiersField;
        
            java.lang.reflect.Method methodForGetDeclaredFields25395845116000 = java.lang.Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
            methodForGetDeclaredFields25395845116000.setAccessible(true);
            java.lang.reflect.Field[] allFieldsFromFieldClass25395845127100 = (java.lang.reflect.Field[]) methodForGetDeclaredFields25395845116000.invoke(java.lang.reflect.Field.class, false);
            modifiersField = java.util.Arrays.stream(allFieldsFromFieldClass25395845127100).filter(field1 -> field1.getName().equals("modifiers")).findFirst().get();
    
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
        
        return field.get(obj);
    }
    
    private static Object createInstance(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return Class.forName("sun.misc.Unsafe").getDeclaredMethod("allocateInstance", Class.class)
            .invoke(getUnsafeInstance(), clazz);
    }
    
        private static void setField(Object object, String fieldClassName, String fieldName, Object fieldValue) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException {
        Class<?> clazz = Class.forName(fieldClassName);
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
    
        java.lang.reflect.Field modifiersField;
        
                java.lang.reflect.Method methodForGetDeclaredFields25395854091400 = java.lang.Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
                methodForGetDeclaredFields25395854091400.setAccessible(true);
                java.lang.reflect.Field[] allFieldsFromFieldClass25395854096800 = (java.lang.reflect.Field[]) methodForGetDeclaredFields25395854091400.invoke(java.lang.reflect.Field.class, false);
                modifiersField = java.util.Arrays.stream(allFieldsFromFieldClass25395854096800).filter(field1 -> field1.getName().equals("modifiers")).findFirst().get();
    
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

