package org.cyber.utils;

import java.security.InvalidParameterException;

public class Utils {
    public static void vulnerabilityAssert(boolean predicateResult, String message) throws VulnerabilityException {
        if (!predicateResult) {
            throw new VulnerabilityException(message, new InvalidParameterException("these parameters lead to vulnerability"));
        }
    }
}
