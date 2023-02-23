package org.baseExamples;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;


public class ArbitraryFileCreation {
    public void createFile(String s) throws IOException {
        Path path = Paths.get(s);
        Files.createDirectories(path.getParent());
        Files.createFile(path);
    }

    public void createFileObjectCreateNewFile(String s) throws IOException {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File file = fsv.createFileObject(s);
        file.createNewFile();
    }

    public void newFile(String s) throws IOException {
        File file = new File(s);
        file.createNewFile();
    }

    public void createTempFile(String pref, String suff, FileAttribute<?>... attrs) throws IOException {
        Files.createTempFile(pref, suff);
    }

    public void filesCopy(String from, String to) throws IOException {
        File f1 = new File(from);
        File f2 = new File(to);
        Files.copy(f1.toPath(), f2.toPath());
    }

    public void filesNewOutputStream(String s) throws IOException {
        Path p1 = Paths.get(s);
        OutputStream br = Files.newOutputStream(p1, StandardOpenOption.CREATE);
        br.close();
    }

    public void fileSystemProviderCopy(String from, String to) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(from);
        Path p2 = Paths.get(to);
        fsp.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
    }

    public void fileSystemProviderNewOutputStream(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s);
        fsp.newOutputStream(p1, StandardOpenOption.CREATE);
    }

    public void newFileWriter(String s)  throws IOException {
        FileWriter f1 = new FileWriter(s);
        f1.write("somedata");
        f1.close();
    }

    public void newPrintWriter(String s)  throws IOException {
        PrintWriter ps = new PrintWriter(s);
        ps.print("somedata");
        ps.close();
    }
}
