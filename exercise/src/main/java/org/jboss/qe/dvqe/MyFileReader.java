package org.jboss.qe.dvqe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyFileReader implements MyFileReaderInterface {
    File file = null;
    Scanner scanner = null;

    MyFileReader(String path) {
        try {
            file = new File(path);
        } catch (NullPointerException e) {
            System.err.println("Missing path");
        }
    }
    public Scanner getScanner() {
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            System.err.println("Error loading file");
        }
        return scanner;
    }

    public int getNumberOfLines() {
        int numOfLines = 0;
        try (Scanner sc = getScanner()) {
            while (sc.hasNextLine()) {
                numOfLines++;
                sc.nextLine();
            }
            return numOfLines;
        }
    }

    public int getNumberOfNonEmptyLines() {
        int numOfNonEmptyLines = 0;
        try (Scanner scanner = getScanner()) {
            while (scanner.hasNextLine()) {
                if (!(scanner.nextLine().isEmpty()))
                    numOfNonEmptyLines++;
            }
            return numOfNonEmptyLines;
        }
    }

    public List<String> readLines() {
        List<String> lines = null;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            return lines;
        }
    }

    public List<String> readFirstNLines(int numOfLines) {
        List<String> lines = null;
        try (Scanner scanner = getScanner()) {
            lines = new ArrayList<String>();
            for(int i=0;scanner.hasNextLine() && i<numOfLines;i++)
                lines.add(scanner.nextLine());
            }
            return lines;
    }
}
