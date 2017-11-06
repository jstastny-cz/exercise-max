package org.jboss.qe.dvqe;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class MyFileReaderUsingUtilsTest {
	
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("testfile.txt").getPath());
	MyFileReaderUsingUtils fr = new MyFileReaderUsingUtils(file.getPath());


  @Test
  public void getNumberOfLines() {
	  assertEquals(8,fr.getNumberOfLines(),"Testing getNumberOfLines()");
  }

  @Test
  public void getNumberOfNonEmptyLines() {
	  assertEquals(4,fr.getNumberOfNonEmptyLines(),"Testing getNumberOfNonEmptyLines()");
  }

  @Test
  public void readFirstNLines() {
	  List<String> testLines = new ArrayList<String>();
	  List<String> actualLines = fr.readFirstNLines(2);
	  testLines.add("first");
	  testLines.add("second");
	    
	  assertEquals(2,actualLines.size(),"Testing size of array");
	  for(int i =0;i < actualLines.size();i++) {
		  assertEquals(testLines.get(i),actualLines.get(i),"Testing elements in list");
	  }
  }

  @Test
  public void readLines() {
	  List<String> testLines = new ArrayList<String>();
	  List<String> actualLines = fr.readLines();
	  testLines.add("first");
	  testLines.add("second");
	  testLines.add("");
	  testLines.add("fourth");
	  testLines.add("");
	  testLines.add("");
	  testLines.add("seventh");
	  testLines.add("");
	  for(int i =0;i < actualLines.size();i++) {
	      assertEquals(testLines.get(i),actualLines.get(i),"Testing elements in list");
	  }
  }
}
