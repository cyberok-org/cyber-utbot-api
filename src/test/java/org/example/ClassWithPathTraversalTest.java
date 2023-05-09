package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;

public final class ClassWithPathTraversalTest {
    ///region Test suites for executable org.example.ClassWithPathTraversal.RAFCreator
    
    
    @Test
    public void testRAFCreator1() throws IOException  {
        ClassWithPathTraversal classWithPathTraversal = new ClassWithPathTraversal();
        
        /* This test fails because method [org.example.ClassWithPathTraversal.RAFCreator] produces [java.lang.NullPointerException]
            org.example.ClassWithPathTraversal.RAFCreator(ClassWithPathTraversal.java:10) */
        classWithPathTraversal.RAFCreator(null);
    }
    
    ///region Errors report for RAFCreator
    
    public void testRAFCreator_errors()
     {
        // Couldn't generate some tests. List of errors:
        // 
        // 1 occurrences of:
        /* No targets for Invocation(instance=ObjectValue(typeStorage=org.cyber.exploitBase.Files, addr=addr: Int32 8), method=<org.cyber.exploitBase.Files: boolean pathCheckStringWithAccess(java.lang.String,java.lang.String)>,
        parameters=[ObjectValue(typeStorage=java.lang.String, addr=addr: (BVInt32 mock#1), concrete=Concrete(value=org.utbot.engine.StringWrapper@61f18997)), ObjectValue(typeStorage=java.lang.String, addr=addr: Int32 6, concrete=Concrete(value=org.utbot.engine.StringWrapper@a712344))],
        generator=() -> kotlin.collections.List<org.utbot.engine.InvocationTarget>) */
        
    }
    ///endregion
    
    ///endregion
}

