package org.jboss.qe.dvqe;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

public class FindFile {

    private File basicDir;
    private File[] overlayDirs;

    FindFile(File basicDir, File... overlayDirs) {
        if (basicDir == null) {
            throw new NullPointerException("Missing first argument: basic directory");
        }
        if (overlayDirs == null) {
            throw new NullPointerException("second argument is NULL");
        }
        for (File file : overlayDirs) {
            int argument = 3;
            if (file == null)
                throw new NullPointerException(argument + ".argument is  NULL");
            argument++;
        }

        if (!basicDir.isDirectory()) {
            throw new IllegalArgumentException("First argument " + basicDir + " is not a directory");
        }
        for (File file : overlayDirs) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("argument " + file + " is not a directory");
            }
        }
        this.basicDir = basicDir;
        this.overlayDirs = overlayDirs;
    }

    public File find(String filename) {
        File file = null;
        List<File> overlayDirs = Arrays.asList(this.overlayDirs);
        Collections.reverse(overlayDirs);
        Iterator<File> dirIterator = overlayDirs.iterator();
        while (dirIterator.hasNext() && file == null) {
            file = checkDir(filename, dirIterator.next());
        }
        if (file == null)
            file = checkDir(filename, basicDir);
        return file;
    }

    private File checkDir(String filename, File dir) {
        File foundFile = null;
        for (File file : FileUtils.listFiles(dir, new NameFileFilter(filename), null)) {
            if (!file.isDirectory())
                foundFile = file;
        }
        return foundFile;
    }
}
