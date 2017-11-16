package org.jboss.qe.dvqe;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

public class FindFile {

    File basicDir;
    File[] overlayDirs;

    FindFile(File basicDir, File... overlayDirs) {
        if (basicDir == null || overlayDirs == null) {
            throw new IllegalArgumentException("Missing argument");
        }
        if (!basicDir.isDirectory()) {
            throw new IllegalArgumentException("First argument is not a directory");
        }
        for (int i = 0; i < overlayDirs.length; i++) {
            if (!overlayDirs[i].isDirectory()) {
                throw new IllegalArgumentException("argument is not a directory");
            }
        }
        this.basicDir = basicDir;
        this.overlayDirs = overlayDirs;
    }

    public File find(String filename) {
        File file = null;
        for (int index = overlayDirs.length - 1; index >= 0 && file == null; index--) {
            file = checkDir(filename, overlayDirs[index]);
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
