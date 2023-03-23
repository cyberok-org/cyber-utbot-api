package org.cyber.utils.overrides;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;

public class CyberPath implements Path {
    public static Path of(String first, String... more) {
        return new CyberPath(first, more);
    }

    public static Path of(URI uri) {
        return new CyberPath(uri);
    }

    public CyberPath(String first, String... more) {
    }

    public CyberPath(URI uri) {
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @NotNull
    @Override
    public FileSystem getFileSystem() {
        return null;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }

    @Override
    public Path getRoot() {
        return null;
    }

    @Override
    public Path getFileName() {
        return null;
    }

    @Override
    public Path getParent() {
        return null;
    }

    @Override
    public int getNameCount() {
        return 0;
    }

    @NotNull
    @Override
    public Path getName(int i) {
        return null;
    }

    @NotNull
    @Override
    public Path subpath(int i, int i1) {
        return null;
    }

    @Override
    public boolean startsWith(@NotNull Path path) {
        return false;
    }

    @Override
    public boolean startsWith(@NotNull String other) {
        return Path.super.startsWith(other);
    }

    @Override
    public boolean endsWith(@NotNull Path path) {
        return false;
    }

    @Override
    public boolean endsWith(@NotNull String other) {
        return Path.super.endsWith(other);
    }

    @NotNull
    @Override
    public Path normalize() {
        return null;
    }

    @NotNull
    @Override
    public Path resolve(@NotNull Path path) {
        return null;
    }

    @NotNull
    @Override
    public Path resolve(@NotNull String other) {
        return Path.super.resolve(other);
    }

    @NotNull
    @Override
    public Path resolveSibling(@NotNull Path other) {
        return Path.super.resolveSibling(other);
    }

    @NotNull
    @Override
    public Path resolveSibling(@NotNull String other) {
        return Path.super.resolveSibling(other);
    }

    @NotNull
    @Override
    public Path relativize(@NotNull Path path) {
        return null;
    }

    @NotNull
    @Override
    public URI toUri() {
        return null;
    }

    @NotNull
    @Override
    public Path toAbsolutePath() {
        return null;
    }

    @NotNull
    @Override
    public Path toRealPath(@NotNull LinkOption... linkOptions) throws IOException {
        return null;
    }

    @NotNull
    @Override
    public File toFile() {
        return Path.super.toFile();
    }

    @NotNull
    @Override
    public WatchKey register(@NotNull WatchService watchService, @NotNull WatchEvent.Kind<?>[] kinds, WatchEvent.Modifier... modifiers) throws IOException {
        return null;
    }

    @NotNull
    @Override
    public WatchKey register(@NotNull WatchService watcher, @NotNull WatchEvent.Kind<?>... events) throws IOException {
        return Path.super.register(watcher, events);
    }

    @NotNull
    @Override
    public Iterator<Path> iterator() {
        return Path.super.iterator();
    }

    @Override
    public int compareTo(@NotNull Path path) {
        return 0;
    }
}
