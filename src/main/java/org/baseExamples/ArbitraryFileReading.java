package org.baseExamples;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipFile;

public class ArbitraryFileReading {
    // NoVulnReadFileParentChild4, NoVulnReadFileURIAuthority

    public void classLoaderGetResourceAsStream(String s) throws IOException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        InputStream is = cl.getResourceAsStream(s);
        byte[] bs = new byte[100];
        is.read(bs);
        is.close();
        String s2 = new String(bs);
        System.out.println(s2);
    }

    public void classLoaderGetSystemResourceAsStream(String s) throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream(s);
        byte[] bs = new byte[100];
        is.read(bs);
        is.close();
        String s2 = new String(bs);
        System.out.println(s2);
    }

    public void filesNewBufferedReader(String s) throws IOException {
        Path p1 = Paths.get(s);
        BufferedReader br = Files.newBufferedReader(p1,	Charset.defaultCharset());
        String l = br.readLine();
        System.out.println(l);
        br.close();
    }

    public void filesNewByteChannel(String s) throws IOException {
        Path p1 = Paths.get(s);
        ByteChannel sbc = Files.newByteChannel(p1, StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(8);

        sbc.read(buf);
        buf.rewind();
        System.out.print(Charset.forName(System.getProperty("file.encoding"))
                .decode(buf));
        buf.flip();
    }

    public void filesNewInputStream(String s) throws IOException {
        Path p1 = Paths.get(s);
        InputStream br = Files.newInputStream(p1, StandardOpenOption.READ);
        byte[] bs = new byte[8];
        br.read(bs);
        br.close();
        System.out.println(bs);
    }

    public void filesReadAllBytes(String s) throws IOException {
        Path p1 = Paths.get(s);
        byte[] bs = Files.readAllBytes(p1);
        System.out.println(bs);
    }

    public void filesReadAllLines(String s) throws IOException {
        Path p1 = Paths.get(s);
        List<String> lines = Files.readAllLines(p1,	Charset.defaultCharset());
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void fileSystemGetPath(String s) throws IOException {
        FileSystem fs = FileSystems.getDefault();
        Path p = fs.getPath(s);
        System.out.println(p.toAbsolutePath());
    }

    public void fileSystemProvidernewAsynchronousFileChannel(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s);
        HashSet<StandardOpenOption> set = new HashSet<StandardOpenOption>();
        set.add(StandardOpenOption.READ);
        AsynchronousFileChannel afc = fsp.newAsynchronousFileChannel(p1, set, null);
        ByteBuffer bb = ByteBuffer.allocate(10);
        afc.read(bb, 10);
        afc.close();
        System.out.println(bb.array());
    }

    public void fileSystemProvidernewByteChannel(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s);
        HashSet<StandardOpenOption> set = new HashSet<StandardOpenOption>();
        set.add(StandardOpenOption.READ);
        SeekableByteChannel bc = fsp.newByteChannel(p1, set);
        ByteBuffer bb = ByteBuffer.allocate(10);
        bc.read(bb);
        bc.close();
        System.out.println(bb.array().toString());
    }

    public void fileSystemProvidernewFileChannel(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s);
        HashSet<StandardOpenOption> set = new HashSet<StandardOpenOption>();
        set.add(StandardOpenOption.READ);
        FileChannel bc = fsp.newFileChannel(p1, set);
        ByteBuffer bb = ByteBuffer.allocate(10);
        bc.read(bb);
        bc.close();
        System.out.println(bb.array().toString());
    }

    public void fileSystemProvidernewInputStream(String s) throws IOException {
        List<FileSystemProvider> fsps = FileSystemProvider.installedProviders();
        FileSystemProvider fsp = fsps.get(0);

        Path p1 = Paths.get(s);
        InputStream fs = fsp.newInputStream(p1, StandardOpenOption.READ);
        byte[] bs = new byte[10];
        System.out.println(fs.read(bs));
        fs.close();
    }

    public void newFile(String s) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(s));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    public void newFileInputStream(String s) throws IOException {
        FileInputStream in = new FileInputStream(s);
        System.out.println(in.read());
        in.close();
    }

    public void newFileReader(String s) throws IOException {
        FileReader fr = new FileReader(s);
        BufferedReader in = new BufferedReader(fr);
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    public void newMimetypesFileTypeMap(String s) throws IOException {
        MimetypesFileTypeMap mm = new MimetypesFileTypeMap(s);
        System.out.println(mm.getContentType(s));
    }

    public void newRandomAccessFile(String s) throws IOException {
        File f1 = new File(s);
        RandomAccessFile file1 = new RandomAccessFile(f1, "r");
        String line = file1.readLine();
        System.out.println(line);
        file1.close();
    }

    public void newURI(String s) throws IOException, URISyntaxException {
        URI uri = new URI(s);
        BufferedReader in = new BufferedReader(new InputStreamReader(uri
                .toURL().openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    public void newURL(String s) throws IOException {
        URL url = new URL(s);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }

    public void newZipFile(String s) throws IOException {
        ZipFile zf = new ZipFile(s);
        System.out.println(zf.size());
        System.out.println(s);
    }

    public void pathsGet(String s) throws IOException {
        Path p = Paths.get(s);
        System.out.println(p);
    }

    public void readFileParentChild(String parent, String child) throws IOException {
        File file = new File(parent, child);/*entry points*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit points*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileParentChild2(String child) throws IOException {
        String parent = null;
        File file = new File(parent, child);/*entry point*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileParentChild3(String child) throws IOException {
        String parent = "";
        File file = new File(parent, child);/*entry point*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileParentChild5(String parent) throws IOException {
        String child = "";
        File file = new File(parent, child);/*entry points*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit points*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileParentChild6(String child) throws IOException {
        String parent = "dir";
        File file = new File(parent, child);/*entry point*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        //responseWriter.println(System.getProperty("user.dir"));
        System.out.println("File: " + file.getAbsolutePath());
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileParentChild7(String child) throws IOException {
        String parent = "/dir";
        File file = new File(parent, child);/*entry point*/
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void ReadFileURI(String s) throws IOException, URISyntaxException {
        URI uri = new URI(s);/*entry point*/
        File file = new File(uri);
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileURIHost(String host) throws IOException, URISyntaxException {
        URI uri = new URI("file", host, null, null);/*entry point*/
        //System.out.println(uri.toString());
        File file = new File(uri);
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileURIPath(String path) throws IOException, URISyntaxException {
        URI uri = new URI("file", null, path, null);/*entry point*/
        //System.out.println(uri.toString());
        File file = new File(uri);
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileURIPath2(String path) throws IOException, URISyntaxException {
        URI uri = new URI("file", null, null, -1, path, null, null);/*entry point*/
        System.out.println(uri.toString());
        File file = new File(uri);
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }

    public void readFileURISsp(String ssp) throws IOException, URISyntaxException {
        URI uri = new URI("file", ssp, null);/*entry point*/
        File file = new File(uri);
        BufferedReader in = new BufferedReader(new FileReader(file));/*exit point*/
        System.out.println("File: " + file.getAbsolutePath());

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();
    }
}
