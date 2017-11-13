package org.jboss.qe.dvqe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

class MyFileReaderUsingUtils implements MyFileReaderInterface {
    private final Logger logger = LoggerFactory.getLogger(MyFileReaderUsingUtils.class);
    File file;

    MyFileReaderUsingUtils(String path) throws FileNotFoundException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }
        file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException("File doesn't exist");
        }
    }

    public int getNumberOfLines() {
        int numOfLines;
        try {
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            numOfLines = 0;
            while (it.hasNext()) {
                numOfLines++;
                it.nextLine();
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            numOfLines = -1;
        }
        return numOfLines;
    }

    public int getNumberOfNonEmptyLines() {
        int numOfEmptyLines;
        try {
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            numOfEmptyLines = 0;
            while (it.hasNext()) {
                if (!(it.nextLine().isEmpty()))
                    numOfEmptyLines++;
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            numOfEmptyLines = -1;
        }
        return numOfEmptyLines;
    }

    public List<String> readLines() {
        List<String> lines;
        try {
            lines = FileUtils.readLines(file, "UTF-8");
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            lines = null;
        }
        return lines;
    }

    public List<String> readFirstNLines(int n) {
        List<String> lines;
        LineIterator iterator;
        try {
            iterator = FileUtils.lineIterator(file, "UTF-8");
            lines = new ArrayList<String>();
            for (int i = 0; (i < n) && iterator.hasNext(); i++) {
                lines.add(iterator.nextLine());
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            lines = null;
        }
        return lines;
    }
}
