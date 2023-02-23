package org.baseExamples;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.util.Formatter;
import java.util.List;

public class ArbitraryFileModification {
//    file_upload, XMLEncoder

    public void filesMove(String s1, String s2) throws IOException {
        File f1 = new File(s1);
        File f2 = new File(s2);

        Files.move(f1.toPath(), f2.toPath());
    }

    public void filesNewBufferedWriter(String s) throws IOException {
        Path p1 = Paths.get(s);
        BufferedWriter br = Files.newBufferedWriter(p1,	Charset.defaultCharset());
        br.write("somedata");
        br.close();
    }

    public void filesNewByteChannel(String s) throws IOException {
        Path p1 = Paths.get(s);
        ByteChannel bc = Files.newByteChannel(p1, StandardOpenOption.WRITE);
        byte data[] = "Hello World! ".getBytes();
        ByteBuffer bb = ByteBuffer.wrap(data);
        bc.write(bb);
        bc.close();
    }

    public void filesNewOutputStream(String s) throws IOException {
        Path p1 = Paths.get(s);
        OutputStream br = Files.newOutputStream(p1, StandardOpenOption.APPEND);
        br.write(0);
        br.close();
    }

    public void filesSetOwner(String s) throws IOException {
        Path file = Paths.get(s);

        UserPrincipal owner = file.getFileSystem()
                .getUserPrincipalLookupService()
                .lookupPrincipalByName("PTSECURITY\\Vvorobyeva");
        Files.setOwner(file, owner);
    }

    public void filesWrite(String s) throws IOException {
        Path p1 = Paths.get(s);
        byte[] data = new byte[0];
        Files.write(p1, data, StandardOpenOption.APPEND);
    }

    public void fileSystemProviderMove(String s1, String s2) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s1);
        Path p2 = Paths.get(s2);
        fsp.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
    }

    public void newFileOutputStream(String s) throws IOException {
        FileOutputStream out = new FileOutputStream(s);
        out.write(0);
        out.close();
    }

    public void newFileWriter(String s) throws IOException {
        FileWriter f1 = new FileWriter(s);
        f1.write("somedata");
        f1.close();
    }

    public void newFormatter(String s) throws IOException {
        Formatter f = new Formatter(s);
        f.format("some_string");
        f.flush();
        f.close();
    }

    public void newPrintStream(String s) throws IOException {
        PrintStream ps = new PrintStream(s);
        ps.print("somedata");
        ps.close();
    }

    public void newPrintWriter(String s) throws IOException {
        PrintWriter ps = new PrintWriter(s);
        ps.print("somedata");
        ps.close();
    }

    public void newRandomAccessFile(String s) throws IOException {
        RandomAccessFile file1 = new RandomAccessFile(s, "rw");
        file1.writeUTF("somedata");
        file1.close();
    }
}
