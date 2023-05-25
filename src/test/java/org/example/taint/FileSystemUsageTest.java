package org.testcases.taint;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class FileSystemUsageTest {
    ///region Test suites for executable org.testcases.taint.FileSystemUsage.main
    
    ///region Errors report for main
    
    public void testMain_errors()
     {
        // Couldn't generate some tests. List of errors:
        // 
        // 8 occurrences of:
        // Concrete execution failed
        
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.FileSystemUsage.source
    
    ///region
    
    @Test
    public void testSource1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Class fileSystemUsageClazz = Class.forName("org.testcases.taint.FileSystemUsage");
        Class intType = int.class;
        Method sourceMethod = fileSystemUsageClazz.getDeclaredMethod("source", intType);
        sourceMethod.setAccessible(true);
        java.lang.Object[] sourceMethodArguments = new java.lang.Object[1];
        sourceMethodArguments[0] = 25;
        String actual = ((String) sourceMethod.invoke(null, sourceMethodArguments));
        
        String expected = "dangerous";
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSource2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        Class fileSystemUsageClazz = Class.forName("org.testcases.taint.FileSystemUsage");
        Class intType = int.class;
        Method sourceMethod = fileSystemUsageClazz.getDeclaredMethod("source", intType);
        sourceMethod.setAccessible(true);
        java.lang.Object[] sourceMethodArguments = new java.lang.Object[1];
        sourceMethodArguments[0] = 24;
        String actual = ((String) sourceMethod.invoke(null, sourceMethodArguments));
        
        String expected = "ok";
        assertEquals(expected, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.FileSystemUsage.foo
    
    ///region Errors report for foo
    
    public void testFoo_errors()
     {
        // Couldn't generate some tests. List of errors:
        // 
        // 12 occurrences of:
        // Concrete execution failed
        
    }
    ///endregion
    
    ///endregion
}

