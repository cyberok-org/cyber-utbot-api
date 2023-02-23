package org.baseExamples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class ArbitraryFileDeletion {
    public void delete(String s) throws IOException {
        Path path = Paths.get(s);
        Files.delete(path);
    }

    public void deleteIfExists(String s) throws IOException {
        Path path = Paths.get(s);
        Files.deleteIfExists(path);
    }

    public void deleteFile(String s) {
        File file = new File(s);
        file.delete();
    }

    public void deleteOnExit(String s) {
        File file = new File(s);
        file.deleteOnExit();
    }

    public void fileSystemProvider_delete(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);
        Path p1 = Paths.get(s);
        fsp.delete(p1);
    }

    public void fileSystemProvider_deleteIfExists(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);
        Path p1 = Paths.get(s);
        fsp.deleteIfExists(p1);
    }
}
