package org.jboss.qe.dvqe;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

public class GetFile {

    File basicDir, overlayDir;

    GetFile(File basicDir, File overlayDir) {
        if (basicDir == null || overlayDir == null) {
            throw new IllegalArgumentException("Missing argument");
        }
        if (!basicDir.isDirectory()) {
            throw new IllegalArgumentException("First argument is not a directory");
        }
        if (!overlayDir.isDirectory()) {
            throw new IllegalArgumentException("Second argument is not a directory");
        }
        this.basicDir = basicDir;
        this.overlayDir = overlayDir;
    }

    public File findFile(String filename) {
        File foundFile = null;
        for (File file : FileUtils
            .convertFileCollectionToFileArray(FileUtils.listFiles(basicDir, new NameFileFilter(filename), null))) {
            foundFile = file;
        }
        if(foundFile != null)
            for (File file : FileUtils
                .convertFileCollectionToFileArray(FileUtils.listFiles(overlayDir, new NameFileFilter(filename), null))) {
                foundFile = file;
            }
        return foundFile;
    }
}
