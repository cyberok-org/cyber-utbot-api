package org.cyber.exploitBase;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.nio.file.attribute.UserPrincipal;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;


public class Files {
    static String URI_VULNERABILITY_HOST = "host";
    static String URI_VULNERABILITY_SSP = "ssp";
    static String URI_VULNERABILITY_PATH = "ssp";


    boolean pathCheckString(String s)  {
        return s.matches("(\\.\\.\\/){1,10}etc/passwd");
    }

    boolean pathCheckString2(String s1, String s2)  {
        return pathCheckString(s1) && pathCheckString(s2);
    }

    boolean pathCheckStringWithAccess(String s, String access)  {
        return access.equals("rw") && pathCheckString(s);
    }

    boolean pathCheckStringWithAccess(File f, String access)  {
        return access.equals("rw") && pathCheckString(f.getPath());
    }

    boolean pathCheckStringWithOther(String s, boolean append)  {
        return pathCheckString(s);
    }

    boolean pathCheckStringWithOther(String s, Charset charset)  {
        return pathCheckString(s);
    }

    boolean pathCheckStringWithOther(String s, Charset charset, boolean append) {
        return pathCheckString(s);
    }

    boolean pathCheckStringWithOther(String s, String csn) {
        return pathCheckString(s);
    }

    boolean pathCheckStringWithOther(String s, String csn, Locale l) {
        return pathCheckString(s);
    }

    boolean pathCheckStringWithOther(String s, Charset charset, Locale l)  {
        return pathCheckString(s);
    }

    boolean pathCheck(Path p, Charset ch) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p, Charset ch, OpenOption... options) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p, OpenOption... options) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p, boolean append) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p, FileAttribute<?>... attrs) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p) {
        return pathCheckString(p.toString());
    }

    boolean pathCheck(Path p1, Path p2) {
        return pathCheckString(p1.toString()) && pathCheckString(p2.toString());
    }

    boolean pathCheckWithStandardOpenOptionCreate(Path p, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.CREATE) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionCreate(Path p, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return options.contains(StandardOpenOption.CREATE) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionWrite(Path p, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.WRITE) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionWrite(Path p, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return options.contains(StandardOpenOption.WRITE) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionAppend(Path p, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.APPEND) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionAppend(Path p, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.APPEND) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionAppend(Path p, Iterable<? extends CharSequence> lines, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.APPEND) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionRead(Path p, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.READ) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionRead(Path p, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return options.contains(StandardOpenOption.READ) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardOpenOptionAppend(Path p, byte[] data, OpenOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardOpenOption.APPEND) && pathCheckString(p.toString());
    }

    boolean pathCheckWithStandardCopyOption(Path p1, Path p2, CopyOption... options) {
        return Arrays.stream(options).anyMatch(x -> x == StandardCopyOption.REPLACE_EXISTING) && pathCheckString(p1.toString()) && pathCheckString(p2.toString());
    }

    boolean pathCheckStringParts(String pref, String suff, FileAttribute<?>... attrs) { // FIXME + '/' +
        if (pref == null) {
            return pathCheckString(suff);
        } else if (suff == null) {
            return pathCheckString(pref);
        }
        return pathCheckString(pref + '/' + suff);
    }

    boolean pathCheckStringParts(Path dir, String pref, String suff, FileAttribute<?>... attrs) {   // FIXME + '/' +
        if (pref == null) {
            return pathCheckString(dir.toString() + '/' + suff);
        } else if (suff == null) {
            return pathCheckString(dir.toString() + '/' + pref);
        }
        return pathCheckString(dir.toString() + '/' + pref + '/' + suff);
    }

    boolean pathCheckParentChild(String parent, String child) {
        if (parent == null) {
            return pathCheckString(child);
        }
        return pathCheckString(parent + '/' + child);   // FIXME
    }

    boolean pathCheckParentChild(File parent, String child) {
        if (parent == null) {
            return pathCheckString(child);
        }
        return pathCheckString(parent.getPath() + '/' + child);   // FIXME
    }

    boolean pathCheckWithUserPrincipal(Path p, UserPrincipal owner) {
        return pathCheckString(p.toString());
    }

    boolean pathCheckFileSystemProviderRead1(Path p, Set<? extends OpenOption> options, ExecutorService executor, FileAttribute<?>... attrs) {
        return options.contains(StandardOpenOption.READ) && pathCheckString(p.toString());
    }

    boolean pathCheckFileSystemProviderRead2(Path p, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return options.contains(StandardOpenOption.READ) && pathCheckString(p.toString());
    }

    boolean pathCheckFileURI(String scheme, String userInfo, String host, int port, String path, String query, String fragment) {
        return scheme.equals("file") && (host.equals(URI_VULNERABILITY_HOST) || path.equals(URI_VULNERABILITY_PATH));
    }

    boolean pathCheckFileURI(String scheme, String host, String path, String fragment) {
        return scheme.equals("file") && (host.equals(URI_VULNERABILITY_HOST) || path.equals(URI_VULNERABILITY_PATH));
    }

    boolean pathCheckFileURI(String scheme, String ssp, String fragment) {
        return scheme.equals("file") && ssp.equals(URI_VULNERABILITY_SSP);
    }

    boolean pathCheckFileWithOther(File f) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, boolean append) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, Charset charset) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, Charset charset, Locale l) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, Charset charset, boolean append) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, String csn) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckFileWithOther(File f, String csn, Locale l) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckZip(File f, int mode) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckZip(File f) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckZip(File f, int mode, Charset charset) {
        return pathCheckString(f.getPath());
    }

    boolean pathCheckZip(String s, Charset charset) {
        return pathCheckString(s);
    }

    boolean pathCheckZip(File f, Charset charset) {
        return pathCheckString(f.getPath());
    }
}
