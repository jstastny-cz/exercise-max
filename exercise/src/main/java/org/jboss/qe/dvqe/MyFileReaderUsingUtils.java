package org.jboss.qe.dvqe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

class MyFileReaderUsingUtils implements MyFileReaderInterface {
    File file;

    MyFileReaderUsingUtils(String path) throws FileNotFoundException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException();
        }
        file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException();
        }
    }

    public int getNumberOfLines() {
        int numOfLines = 0;
        try {
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            while (it.hasNext()) {
                numOfLines++;
                it.nextLine();
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
        return numOfLines;
    }

    public int getNumberOfNonEmptyLines() {
        int numOfEmptyLines = 0;
        try {
            LineIterator it = FileUtils.lineIterator(file, "UTF-8");
            while (it.hasNext()) {
                if (!(it.nextLine().isEmpty()))
                    numOfEmptyLines++;
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
        return numOfEmptyLines;
    }

    public List<String> readLines() {
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
        return lines;
    }

    public List<String> readFirstNLines(int n) {
        List<String> lines = null;
        LineIterator iterator = null;
        try {
            iterator = FileUtils.lineIterator(file, "UTF-8");
            lines = new ArrayList<String>();
            for (int i = 0; (i < n) && iterator.hasNext(); i++) {
                lines.add(iterator.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
        return lines;
    }
}
