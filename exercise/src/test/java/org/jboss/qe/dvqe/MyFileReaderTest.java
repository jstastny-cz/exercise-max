package org.jboss.qe.dvqe;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Test
public class MyFileReaderTest {

    MyFileReaderUsingUtils fr;
    File file;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        file = new File(getClass().getResource("/testfile.txt").getFile());
        fr = new MyFileReaderUsingUtils(file.getPath());
    }

    @Test
    public void getNumberOfLinesTest() {
        assertEquals(fr.getNumberOfLines(), 8, "Unexpected number of lines");
        assertNotEquals(fr.getNumberOfLines(), 0, "Unexpected number of lines");
        assertNotEquals(fr.getNumberOfLines(), -1, "Unexpected number of lines");
        assertNotEquals(fr.getNumberOfLines(), 321, "Unexpected number of lines");
    }

    @Test
    public void getNumberOfNonEmptyLinesTest() throws IOException {
        assertEquals(fr.getNumberOfNonEmptyLines(), 4, "Unexpected number of non-empty lines");
        assertNotEquals(fr.getNumberOfNonEmptyLines(), 0, "Unexpected number of lines");
        assertNotEquals(fr.getNumberOfNonEmptyLines(), -1, "Unexpected number of lines");
        assertNotEquals(fr.getNumberOfNonEmptyLines(), 321, "Unexpected number of lines");
    }

    @Test
    public void readFirstNLinesTest() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second" });
        List<String> actualLines = fr.readFirstNLines(2);
        assertEquals(actualLines.size(), 2, "Unexpected size of list");
        assertNotEquals(actualLines.size(), 0, "Unexpected size of list");
        assertNotEquals(actualLines.size(), -1, "Unexpected size of list");
        assertNotEquals(actualLines.size(), 321, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }

    @Test
    public void readLinesTest() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second", "", "fourth", "", "", "seventh", "" });
        List<String> actualLines = fr.readLines();
        assertEquals(actualLines.size(), 8, "Unexpected size of list");
        assertNotEquals(actualLines.size(), 0, "Unexpected size of list");
        assertNotEquals(actualLines.size(), -1, "Unexpected size of list");
        assertNotEquals(actualLines.size(), 321, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }
}
