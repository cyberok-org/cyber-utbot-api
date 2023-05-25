package org.example.inter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class PathTraversalTest {
    ///region Test suites for executable org.example.inter.PathTraversal.doGet
    
    ///region SYMBOLIC EXECUTION: ERROR SUITE for method doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link java.lang.NullPointerException} in: String s = req.getParameter(FIELD_NAME);
 *  */
    @Test
    @DisplayName("doGet: s = req.getParameter(FIELD_NAME) : True -> ThrowNullPointerException")
    public void testDoGet_ThrowNullPointerException() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [java.lang.NullPointerException]
            org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
        pathTraversal.doGet(null, null);
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_1() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_2() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_3() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_4() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_5() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_6() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_7() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_8() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_9() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_10() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_11() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_12() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("et\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_13() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/p\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_14() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/pa\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_15() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_16() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_17() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eet\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_18() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_19() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/pas\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_20() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_21() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2ee\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_22() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_23() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/p\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_24() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/pass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_25() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passw\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_26() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_27() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/pa\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_28() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/pas\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_29() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file modification")
    public void testDoGet_ThrowMockitoException_30() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/passwd");
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_31() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/pass\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_32() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_33() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35%\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_34() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%3\u0000\u0000\u0000\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_35() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("%5c%2e%2eetc/passw\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_36() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35%6\u0000\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_37() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35%63\u0000\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file modification")
    public void testDoGet_ThrowMockitoException_38() throws IOException  {
        PathTraversal pathTraversal = new PathTraversal();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35%63..");
        
        /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
            org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
            org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
        pathTraversal.doGet(httpServletRequestMock, null);
    }
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#doGet(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)}
 * @utbot.throwsException {@link org.mockito.exceptions.base.MockitoException} in: RandomAccessFile raf = new RandomAccessFile(name, "rw");
 *  */
    @Test
    @DisplayName("doGet: raf = new RandomAccessFile(name, \"rw\") -> ThrowMockitoException")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file creation")
    public void testDoGet_ThrowMockitoException_39() throws IOException  {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(java.io.File.class, (java.io.File fileMock, org.mockito.MockedConstruction.Context context) -> {
            });
            PathTraversal pathTraversal = new PathTraversal();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (org.mockito.Mockito.when(httpServletRequestMock.getParameter("name"))).thenReturn("etc/passwd%35%63.\u0000");
            
            /* This test fails because method [org.example.inter.PathTraversal.doGet] produces [org.mockito.exceptions.base.MockitoException: \nCannot call abstract real method on java object!\nCalling real methods is only possible when mocking non abstract method.\n  //correct example:\n  when(mockOfConcreteClass.nonAbstractMethod()).thenCallRealMethod();]
                org.utbot.instrumentation.instrumentation.execution.constructors.MockValueConstructor$generateMockitoAnswer$1.answer(MockValueConstructor.kt:228)
                org.example.inter.PathTraversal.doGet(PathTraversal.java:38) */
            pathTraversal.doGet(httpServletRequestMock, null);
        } finally {
            mockedConstruction.close();
        }
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.PathTraversal.getDescription
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method getDescription()
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#getDescription()}
 * @utbot.returnsFrom {@code return "path traversal";}
 *  */
    @Test
    @DisplayName("getDescription: -> return \"path traversal\"")
    public void testGetDescription_ReturnPathtraversal() {
        PathTraversal pathTraversal = new PathTraversal();
        
        String actual = pathTraversal.getDescription();
        
        String expected = "path traversal";
        assertEquals(expected, actual);
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.example.inter.PathTraversal.getVulnerabilityCount
    
    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method getVulnerabilityCount()
    
    /**
    @utbot.classUnderTest {@link PathTraversal}
 * @utbot.methodUnderTest {@link org.example.inter.PathTraversal#getVulnerabilityCount()}
 * @utbot.returnsFrom {@code return 2;}
 *  */
    @Test
    @DisplayName("getVulnerabilityCount: -> return 2")
    public void testGetVulnerabilityCount_Return2() {
        PathTraversal pathTraversal = new PathTraversal();
        
        int actual = pathTraversal.getVulnerabilityCount();
        
        assertEquals(2, actual);
    }
    ///endregion
    
    ///endregion
}

