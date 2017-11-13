package org.jboss.qe.dvqe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



class MyFileReader implements MyFileReaderInterface {
    private final Logger logger = LoggerFactory.getLogger(MyFileReader.class);
    File file = null;
    Scanner scanner = null;

    MyFileReader(String path) throws FileNotFoundException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }
        file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException("File doesn't exist");
        }
    }

    private Scanner getScanner() throws FileNotFoundException {
        scanner = new Scanner(new BufferedReader(new FileReader(file)));
        return scanner;
    }

    public int getNumberOfLines() {
        int numOfLines;
        try (Scanner sc = getScanner()) {
            numOfLines = 0;
            while (sc.hasNextLine()) {
                numOfLines++;
                sc.nextLine();
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ", e);
            numOfLines = -1;
        }
        return numOfLines;
    }

    public int getNumberOfNonEmptyLines() {
        int numOfNonEmptyLines;
        try (Scanner scanner = getScanner()) {
            numOfNonEmptyLines = 0;
            while (scanner.hasNextLine()) {
                if (!(scanner.nextLine().isEmpty()))
                    numOfNonEmptyLines++;
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            numOfNonEmptyLines = -1;
        }
        return numOfNonEmptyLines;
    }

    public List<String> readLines() {
        List<String> lines;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            lines = null;
        }
        return lines;
    }

    public List<String> readFirstNLines(int numOfLines) {
        List<String> lines;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            for (int i = 0; scanner.hasNextLine() && i < numOfLines; i++)
                lines.add(scanner.nextLine());
        } catch (Exception e) {
            logger.error("Read operation failed: ",e);
            lines = null;
        }
        return lines;
    }
}
