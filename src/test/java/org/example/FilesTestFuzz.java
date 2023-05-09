package org.cyber.exploitBase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;

public final class FilesTest {
    ///region Test suites for executable org.cyber.exploitBase.Files.pathCheckStringWithAccess


    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Files}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Files#pathCheckStringWithAccess(java.io.File,java.lang.String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: f = File(String, String), access = 'some_file' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyString() {
        Files files = new Files();
        File file = new File("some_file", "etc/passwd");

        boolean actual = files.pathCheckStringWithAccess(file, "some_file");

        assertFalse(actual);
    }

    ///region FUZZER: SUCCESSFUL EXECUTIONS for method pathCheckStringWithAccess(java.io.File, java.lang.String)

    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Files}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Files#pathCheckStringWithAccess(java.io.File,java.lang.String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: f = File(String, String), access = 'some_file' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyString1() {
        Files files = new Files();
        File file = new File("some_file", "etc/passwd");

        boolean actual = files.pathCheckStringWithAccess(file, "some_file");

        assertFalse(actual);
    }
    ///endregion

    ///endregion

    ///region Test suites for executable org.cyber.exploitBase.Files.pathCheckStringWithAccess


    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Files}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Files#pathCheckStringWithAccess(java.lang.String,java.lang.String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: s = 'some_file', access = 'etc/passwd' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStrings() {
        Files files = new Files();

        boolean actual = files.pathCheckStringWithAccess("some_file", "etc/passwd");

        assertFalse(actual);
    }

    ///region FUZZER: SUCCESSFUL EXECUTIONS for method pathCheckStringWithAccess(java.lang.String, java.lang.String)

    /**
     * @utbot.classUnderTest {@link org.cyber.exploitBase.Files}
     * @utbot.methodUnderTest {@link org.cyber.exploitBase.Files#pathCheckStringWithAccess(java.lang.String,java.lang.String)}
     */
    @Test
    @DisplayName("pathCheckStringWithAccess: pathCheckStringWithAccess: s = 'some_file', access = 'etc/passwd' -> return false")
    public void testPathCheckStringWithAccessReturnsFalseWithNonEmptyStrings1() {
        Files files = new Files();

        boolean actual = files.pathCheckStringWithAccess("some_file", "etc/passwd");

        assertFalse(actual);
    }
    ///endregion

    ///endregion
}

