package org.cyber.utils;

public class Utils {
    public static void vulnerabilityAssert(boolean predicateResult, String message) throws VulnerabilityException {
        if (!predicateResult) {
            throw new VulnerabilityException(message);
        }
    }

    public static void vulnerabilityAssertByMsg(String message) throws VulnerabilityException {
        throw new VulnerabilityException(message);
    }
}
