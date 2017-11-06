package org.jboss.qe.dvqe;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Test
public class MyFileReaderTest {
    ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("testfile.txt").getPath());
	MyFileReader fr = new MyFileReader(file.getPath());
	
@Test
  public void getNumberOfLinesTest() {
	assertEquals(8,fr.getNumberOfLines(),"Testing getNumberOfLines()");
  }
@Test
  public void getNumberOfNonEmptyLinesTest() throws IOException {
    assertEquals(4,fr.getNumberOfNonEmptyLines(),"Testing getNumberOfNonEmptyLines()");
  }
@Test  
 public void readFirstNLinesTest() {  
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
  public void readLinesTest() {
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
    assertEquals(8,actualLines.size(),"Testing size of array");
    for(int i =0;i < actualLines.size();i++) {
		assertEquals(testLines.get(i),actualLines.get(i),"Testing elements in list");
	}
    
  }

}
