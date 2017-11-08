package org.jboss.qe.dvqe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyFileReader implements MyFileReaderInterface {
    File file = null;
    Scanner scanner = null;

    MyFileReader(String path) throws FileNotFoundException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException();
        }
        file = new File(path);
        if (!file.isFile()) {
            throw new FileNotFoundException();
        }
    }

    public Scanner getScanner() throws FileNotFoundException {
        scanner = new Scanner(new BufferedReader(new FileReader(file)));
        return scanner;
    }

    public int getNumberOfLines() {
        int numOfLines = -1;
        try (Scanner sc = getScanner()) {
            numOfLines = 0;
            while (sc.hasNextLine()) {
                numOfLines++;
                sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
        return numOfLines;
    }

    public int getNumberOfNonEmptyLines() {
        int numOfNonEmptyLines = -1;
        try (Scanner scanner = getScanner()) {
            numOfNonEmptyLines = 0;
            while (scanner.hasNextLine()) {
                if (!(scanner.nextLine().isEmpty()))
                    numOfNonEmptyLines++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
        return numOfNonEmptyLines;
    }

    public List<String> readLines() {
        List<String> lines = null;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
        return lines;
    }

    public List<String> readFirstNLines(int numOfLines) {
        List<String> lines = null;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            for (int i = 0; scanner.hasNextLine() && i < numOfLines; i++)
                lines.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.err.println("File not Found");
        }
        return lines;
    }
}
