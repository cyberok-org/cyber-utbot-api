package org.baseExamples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public final class ArbitraryFileCreationTest {
    ///region Test suites for executable org.baseExamples.ArbitraryFileCreation.newFileWriter
    
    ///region SYMBOLIC EXECUTION: SECURITY for method newFileWriter(java.lang.String)
    
    /**
    @utbot.classUnderTest {@link ArbitraryFileCreation}
 * @utbot.methodUnderTest {@link org.baseExamples.ArbitraryFileCreation#newFileWriter(java.lang.String)}
 * @utbot.throwsException {@link java.security.AccessControlException} in: FileWriter f1 = new FileWriter(s);
 *  */
    @Test
    @DisplayName("newFileWriter: f1 = new FileWriter(s) -> ThrowAccessControlException")
    @org.junit.jupiter.api.Disabled(value = "Disabled due to sandbox")
    @org.cyber.utils.VulnerabilityInfo("arbitrary file modification")
    public void testNewFileWriter_ThrowAccessControlException() {
        ArbitraryFileCreation arbitraryFileCreation = new ArbitraryFileCreation();
        String string = "../../../../../../../../../../etc/passwd";
        
        /* This test fails because method [org.baseExamples.ArbitraryFileCreation.newFileWriter] produces [java.security.AccessControlException: access denied ("java.io.FilePermission" "../../../../../../../../../../etc/passwd" "write")]
            java.base/java.security.AccessControlContext.checkPermission(AccessControlContext.java:485)
            java.base/java.security.AccessController.checkPermission(AccessController.java:1068)
            java.base/java.lang.SecurityManager.checkPermission(SecurityManager.java:416)
            java.base/java.lang.SecurityManager.checkWrite(SecurityManager.java:847)
            java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:223)
            java.base/java.io.FileOutputStream.<init>(FileOutputStream.java:123)
            java.base/java.io.FileWriter.<init>(FileWriter.java:66)
            org.baseExamples.ArbitraryFileCreation.newFileWriter(ArbitraryFileCreation.java:63) */
    }
    ///endregion
    
    ///endregion
}

