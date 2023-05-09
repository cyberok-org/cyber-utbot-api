package org.cyber.exploitBase;

import org.cyber.exploitBase.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public final class FilesTestSaved {
    ///region Test suites for executable org.cyber.exploitBase.Files.pathCheckStringWithAccess
    
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: s = '', access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyStrings() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("", "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: s = '..%255c', access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStringAndEmptyString() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("file..%255c", "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: s = 'etc/passwd', access = 'value2' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStrings() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("etc/passwd", "value2");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: s = 'etc/passwd...', access = 'value2'")
    @org.junit.jupiter.api.Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testPathCheckStringWithAccessWithNonEmptyStrings() {
        Files files = new Files();
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> files.pathCheckStringWithAccess("etc/passwd%25c0%25ae%25c0%25ae\\", "value2"));
    }
    
    ///region FUZZER: SUCCESSFUL EXECUTIONS for method pathCheckStringWithAccess(java.lang.String, java.lang.String)
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: s = '', access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyStrings1() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("", "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: s = '..%255c', access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStringAndEmptyString1() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("..%255c", "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: s = 'etc/passwd', access = 'value2' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStrings1() {
        Files files = new Files();
        
        boolean actual = files.pathCheckStringWithAccess("etc/passwd", "value2");
        
        assertFalse(actual);
    }
    ///endregion
    
    ///region FUZZER: TIMEOUTS for method pathCheckStringWithAccess(java.lang.String, java.lang.String)
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(String, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: s = 'etc/passwd...', access = 'value2'")
    @org.junit.jupiter.api.Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testPathCheckStringWithAccessWithNonEmptyStrings1() {
        Files files = new Files();
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> files.pathCheckStringWithAccess("etc/passwd%25c0%25ae%25c0%25ae\\", "value2"));
    }
    ///endregion
    
    ///endregion
    
    ///region Test suites for executable org.cyber.exploitBase.Files.pathCheckStringWithAccess
    
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: f = File(String, String), access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyString() {
        Files files = new Files();
        File file = new File("", "");
        
        boolean actual = files.pathCheckStringWithAccess(file, "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: f = File(String, String), access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyString1() {
        Files files = new Files();
        File file = new File("", "%5c%2e%2e");
        
        boolean actual = files.pathCheckStringWithAccess(file, "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: f = File(String, String), access = 'value2' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyString() {
        Files files = new Files();
        File file = new File("etc/passwd", "value2");
        
        boolean actual = files.pathCheckStringWithAccess(file, "value2");
        
        assertFalse(actual);
    }
    
    ///region FUZZER: SUCCESSFUL EXECUTIONS for method pathCheckStringWithAccess(java.io.File, java.lang.String)
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: f = File(String, String), access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyString2() {
        Files files = new Files();
        File file = new File("", "");
        
        boolean actual = files.pathCheckStringWithAccess(file, "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: f = File(String, String), access = '' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithEmptyString3() {
        Files files = new Files();
        File file = new File("", "%5c%2e%2e");
        
        boolean actual = files.pathCheckStringWithAccess(file, "");
        
        assertFalse(actual);
    }
    
    /**
     * @utbot.classUnderTest {@link Files}
     * @utbot.methodUnderTest {@link Files#pathCheckStringWithAccess(File, String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: f = File(String, String), access = 'value2' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyString1() {
        Files files = new Files();
        File file = new File("etc/passwd", "value2");
        
        boolean actual = files.pathCheckStringWithAccess(file, "value2");
        
        assertFalse(actual);
    }
    ///endregion
    
    ///endregion
}

