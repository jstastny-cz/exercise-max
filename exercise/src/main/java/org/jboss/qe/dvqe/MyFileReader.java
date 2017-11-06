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
	Scanner s = null;
	MyFileReader(String path) {
		if(path != null)
			file = new File(path);
		
	}
	public Scanner getScanner() {
		try {
			s = new Scanner(new BufferedReader(new FileReader(file)));
		}
		catch(IOException e) {
			System.out.println("error");
		}
		return s;
		
	}
	public int getNumberOfLines() {
		try (Scanner sc = getScanner()) {
			int numOfLines = 0;
			while(sc.hasNextLine()) {
				numOfLines++;
				sc.nextLine();
			}
			return numOfLines;
		}
	}
	public int getNumberOfNonEmptyLines() {
		try (Scanner sc = getScanner()) {
			int numOfEmptyLines = 0;
			while(sc.hasNextLine()) {
				if(sc.nextLine().length() == 0)
					numOfEmptyLines++;
			}
			return numOfEmptyLines;
		}
	}
	public List<String> readLines() {
		List<String> lines = new ArrayList<String>();
		try (Scanner sc = getScanner()) {
			while(sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
			return lines;
		}
	}
	public List<String> readFirstNLines(int n) {
		int i=0;
		List<String> lines = new ArrayList<String>();
		try (Scanner sc = getScanner()) {
			while(sc.hasNextLine() && i < n) {
				lines.add(sc.nextLine());
				i++;
			}
			return lines;
		}
	}
}
