package org.example.inter;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockConstruction;

public final class BasicTest {
    ///region Test suites for executable org.example.inter.Basic.doGet


    @Test
    public void testDoGet1() throws IOException {
        Basic basic = new Basic();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        
        /* This test fails because method [org.example.inter.Basic.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Basic.doGet(Basic.java:36) */
        basic.doGet(httpServletRequestMock, null);
    }

    @Test
    public void testDoGet2() throws IOException {
        Basic basic = new Basic();
        
        /* This test fails because method [org.example.inter.Basic.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Basic.doGet(Basic.java:35) */
        basic.doGet(null, null);
    }

    @Test
    public void testDoGet3() throws IOException {
        org.mockito.MockedConstruction mockedConstruction = null;
        try {
            mockedConstruction = mockConstruction(PrintWriter.class,
                    (PrintWriter printWriterMock, org.mockito.MockedConstruction.Context context) -> {
                    });
            Basic basic = new Basic();
            HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
            (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
            HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
            PrintWriter printWriterMock1 = mock(PrintWriter.class);
            (when(httpServletResponseMock.getWriter())).thenReturn(printWriterMock1);
            basic.doGet(httpServletRequestMock, httpServletResponseMock);
        } finally {
            mockedConstruction.close();
        }
    }

    @Test
    public void testDoGet4() throws IOException {
        Basic basic = new Basic();
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        (when(httpServletRequestMock.getParameter(any()))).thenReturn(((String) null));
        HttpServletResponse httpServletResponseMock = mock(HttpServletResponse.class);
        (when(httpServletResponseMock.getWriter())).thenReturn(((PrintWriter) null));
        
        /* This test fails because method [org.example.inter.Basic.doGet] produces [java.lang.NullPointerException]
            org.example.inter.Basic.doGet(Basic.java:38) */
        basic.doGet(httpServletRequestMock, httpServletResponseMock);
    }

    ///endregion
}

