package org.cyber.utils.overrides;

import java.util.Enumeration;

public class CyberEnumeration<T> implements Enumeration<T> {
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean hasMoreElements() {
        return true;
    }

    @Override
    public T nextElement() {
        return null;
    }
}
