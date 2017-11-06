package org.jboss.qe.dvqe;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

class MyFileReaderUsingUtils implements MyFileReaderInterface{
	File file;
	MyFileReaderUsingUtils(String path) {
		if(path != null)
			file = new File(path);
	}
    public int getNumberOfLines() {
		int numOfLines = 0;
		try {
    	   LineIterator it = FileUtils.lineIterator(file, "UTF-8");
    	    while (it.hasNext()) {
       	     numOfLines++;
       	     it.nextLine();
       	   }
		}
		catch(IOException e) {
			System.out.println("error");
		}
		finally {
		
		}
    	return numOfLines;
	}
	public int getNumberOfNonEmptyLines() {
		int numOfEmptyLines = 0;
		try {
    	    LineIterator it = FileUtils.lineIterator(file, "UTF-8");
    	    while (it.hasNext()) {
    	    if(it.nextLine().length() == 0)
       	     numOfEmptyLines++;
       	    }
		}
		catch(IOException e) {
			System.out.println("error");
		}
		return numOfEmptyLines;
	}
	public List<String> readLines() {
		try {
			return FileUtils.readLines(file, "UTF-8");
		}
		catch(IOException e) {
			System.out.println("error");
			return null;
		}
	}
	public List<String> readFirstNLines(int n) {
		List<String> lines = new ArrayList<String>();
		LineIterator it = null;
		int i = 0;
		try {
    	    it = FileUtils.lineIterator(file, "UTF-8");
    	    while (it.hasNext() && i<n) {
    	    	lines.add(it.nextLine());
    	    	i++;
       	    }
    	    if(it != null) {
    	    	it.close();
    	    }
    	    return lines;
    	   
		}
		catch(IOException e) {
			System.out.println("error");
			return null;
		}
	}
}
