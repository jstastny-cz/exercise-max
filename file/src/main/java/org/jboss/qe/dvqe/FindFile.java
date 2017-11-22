package org.jboss.qe.dvqe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindFile {

    private List<File> directories;

    /**
     * 
     * @param basicDir
     *            basic directory
     * @param overlayDirs
     *            variable number of overlay directories
     * @throws NullPointerException
     *             if argument is null
     * @throws IllegalArgumentException
     *             if argument is not a directory
     */
    public FindFile(File basicDir, File... overlayDirs) {
        if (basicDir == null) {
            throw new NullPointerException("Missing parameter: basic directory");
        }

        if (!basicDir.isDirectory()) {
            throw new IllegalArgumentException("parameter " + basicDir + " is not a directory");
        }

        if (overlayDirs == null || Arrays.asList(overlayDirs).contains(null)) {
            throw new NullPointerException("one of overlay directories is NULL");
        }

        for (File file : overlayDirs) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("parameter " + file + " is not a directory");
            }
        }
        directories = new ArrayList<>();
        directories.add(basicDir);
        directories.addAll(Arrays.asList(overlayDirs));
        Collections.reverse(directories);
    }

    /**
     * 
     * @param filename
     *            name of file
     * @return found file or null
     */
    public File find(String filename) {
        File file = null;
        for (File dir : directories) {
            file = new File(dir, filename);
            if (file.exists() && !file.isDirectory()) {
                return file;
            }
        }
        return null;
    }
}
