package org.jboss.qe.dvqe;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyFileReaderUsingUtilsTest {

    MyFileReaderUsingUtils fr, frEmpty;
    File file, tempFile;

    @BeforeClass
    public void setUp() throws IOException {
        file = new File(getClass().getResource("/testfile.txt").getFile());
        fr = new MyFileReaderUsingUtils(file.getPath());
        tempFile = File.createTempFile("tempFile", ".tmp");
        frEmpty = new MyFileReaderUsingUtils(tempFile.getPath());
        tempFile.delete();
    }

    @Test
    public void getNumberOfLines() {
        assertEquals(fr.getNumberOfLines(), 8, "Unexpected number of lines");
    }

    @Test
    public void getNumberOfLinesNullFileTest() {
        assertEquals(frEmpty.getNumberOfLines(), -1, "Unexpected return value");
    }

    @Test
    public void getNumberOfNonEmptyLines() {
        assertEquals(fr.getNumberOfNonEmptyLines(), 4, "Unexpected number of non-empty lines");
    }

    @Test
    public void getNumberOfNonEmptyLinesNullFileTest() {
        assertEquals(frEmpty.getNumberOfLines(), -1, "Unexpected return value");
    }

    @Test
    public void readFirstNLines() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second" });
        List<String> actualLines = fr.readFirstNLines(2);
        assertEquals(actualLines.size(), 2, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }

    @Test
    public void readFirstNLinesNullFileTest() {
        assertEquals(frEmpty.readFirstNLines(1), null, "Unexpected return value");
    }

    @Test
    public void readFirstNLinesInvalidArgumentTest() {
        assertEquals(fr.readFirstNLines(-1), null, "Unexpected return value");
    }

    @Test
    public void readFirstNLinesZeroLengthTest() {
        assertEquals(fr.readFirstNLines(0), null, "Unexpected return value");
    }

    @Test
    public void readLines() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second", "", "fourth", "", "", "seventh", "" });
        List<String> actualLines = fr.readLines();
        assertEquals(actualLines.size(), 8, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }

    @Test
    public void readLinesNullFileTest() {
        assertEquals(frEmpty.readLines(), null, "Unexpected return value");
    }
}
