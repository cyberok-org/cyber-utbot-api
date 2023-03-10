package org.cyber.utils.overrides;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class CyberPath implements Path {
    public static Path of(String first, String... more) {
        return new CyberPath();
    }

    @NotNull
    @Override
    public FileSystem getFileSystem() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isAbsolute() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Path getRoot() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Path getFileName() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Path getParent() {
        return null;
    }

    @Override
    public int getNameCount() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path getName(int index) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path subpath(int beginIndex, int endIndex) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean startsWith(@NotNull Path other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean endsWith(@NotNull Path other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path normalize() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path resolve(@NotNull Path other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path relativize(@NotNull Path other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public URI toUri() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path toAbsolutePath() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public Path toRealPath(@NotNull LinkOption... options) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public WatchKey register(@NotNull WatchService watcher, @NotNull WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(@NotNull Path other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @NotNull
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
