package org.testcases.taint.sepclasses;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;

public final class SinkClassTest {
    ///region Test suites for executable org.testcases.taint.sepclasses.SinkClass.sink
    
    ///region
    
    @Test
    public void testSink1() {
        SinkClass sinkClass = new SinkClass();
        
        sinkClass.sink(null, null);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.sepclasses.SinkClass.foo
    
    ///region
    
    @Test
    public void testFoo1() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(false);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            
            /* This test fails because method [org.testcases.taint.sepclasses.SinkClass.foo] produces [java.lang.NullPointerException]
                org.testcases.taint.sepclasses.SinkClass.foo(SinkClass.java:19) */
            sinkClass.foo(null, null, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo2() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(false);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            String string = "                     ";
            
            /* This test fails because method [org.testcases.taint.sepclasses.SinkClass.foo] produces [java.lang.NullPointerException]
                org.testcases.taint.sepclasses.SinkClass.foo(SinkClass.java:20) */
            sinkClass.foo(null, null, string);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo3() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(false);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            String string = "                  ";
            
            /* This test fails because method [org.testcases.taint.sepclasses.SinkClass.foo] produces [java.lang.NullPointerException]
                org.testcases.taint.sepclasses.SinkClass.foo(SinkClass.java:20) */
            sinkClass.foo(null, null, string);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo4() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(true);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            
            /* This test fails because method [org.testcases.taint.sepclasses.SinkClass.foo] produces [java.lang.NullPointerException]
                org.testcases.taint.sepclasses.SinkClass.foo(SinkClass.java:10) */
            sinkClass.foo(null, null, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo5() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(true);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            String string = " ";
            
            sinkClass.foo(string, null, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo6() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(false);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            String string = "                     ";
            
            sinkClass.foo(null, string, string);
        } finally {
            mockedConstruction.close();
        }
    }
    
    @Test
    public void testFoo7() {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            java.util.concurrent.atomic.AtomicInteger mockClassCounter = new java.util.concurrent.atomic.AtomicInteger();
            mockedConstruction = mockConstruction(java.util.Random.class, (java.util.Random RandomMock, org.mockito.MockedConstruction.Context context) -> {
                switch (mockClassCounter.get()) {
                    case 0:
                        (when(RandomMock.nextBoolean())).thenReturn(true);
                        break;
                }
                mockClassCounter.getAndIncrement();
            });
            SinkClass sinkClass = new SinkClass();
            String string = "arg1";
            
            sinkClass.foo(string, null, null);
        } finally {
            mockedConstruction.close();
        }
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.testcases.taint.sepclasses.SinkClass.inBetween
    
    ///region
    
    @Test
    public void testInBetween1() {
        SinkClass sinkClass = new SinkClass();
        
        sinkClass.inBetween(false, null, null);
    }
    
    @Test
    public void testInBetween2() {
        SinkClass sinkClass = new SinkClass();
        
        sinkClass.inBetween(true, null, null);
    }
    ///endregion
    
    ///endregion
}

