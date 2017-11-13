package org.jboss.qe.dvqe;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Test
public class MyFileReaderTest {

    MyFileReader fr, frNull, frEmpty;
    File file, tempFile, emptyFile;

    @BeforeClass
    public void setUp() throws IOException {
        file = new File(getClass().getResource("/testfile.txt").getFile());
        fr = new MyFileReader(file.getPath());
        tempFile = File.createTempFile("tempFile", ".tmp");
        frNull = new MyFileReader(tempFile.getPath());
        tempFile.delete();
        emptyFile = File.createTempFile("emptyFile", ".tmp");
        emptyFile.deleteOnExit();
        frEmpty = new MyFileReader(emptyFile.getPath());
    }

    @Test
    public void getNumberOfLinesTest() {
        assertEquals(fr.getNumberOfLines(), 8, "Unexpected number of lines");
    }

    @Test
    public void getNumberOfLinesNullFileTest() {
        assertEquals(frNull.getNumberOfLines(), -1, "Unexpected return value");
    }

    @Test
    public void getNumberOfLinesEmptyFileTest() {
        assertEquals(frEmpty.getNumberOfLines(), 0, "Unexpected number of lines");
    }

    @Test
    public void getNumberOfNonEmptyLinesTest() throws IOException {
        assertEquals(fr.getNumberOfNonEmptyLines(), 4, "Unexpected number of non-empty lines");
    }

    @Test
    public void getNumberOfNonEmptyLinesNullFileTest() {
        assertEquals(frNull.getNumberOfLines(), -1, "Unexpected return value");
    }

    @Test
    public void getNumberOfNonEmptyLinesEmptyFileTest() {
        assertEquals(frEmpty.getNumberOfLines(), 0, "Unexpected number of lines");
    }

    @Test
    public void readFirstNLinesTest() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second" });
        List<String> actualLines = fr.readFirstNLines(2);
        assertEquals(actualLines.size(), 2, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }

    @Test
    public void readFirstNLinesNullFileTest() {
        assertEquals(frNull.readFirstNLines(1), null, "Unexpected return value");
    }

    @Test
    public void readFirstNLinesEmptyFileTest() {
        assertEquals(frEmpty.readFirstNLines(1).size(), 0, "Unexpected return value");
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
    public void readLinesTest() {
        List<String> testLines = Arrays.asList(new String[] { "first", "second", "", "fourth", "", "", "seventh", "" });
        List<String> actualLines = fr.readLines();
        assertEquals(actualLines.size(), 8, "Unexpected size of list");
        for (int i = 0; i < actualLines.size(); i++) {
            assertEquals(actualLines.get(i), testLines.get(i), "Unexpected element value");
        }
    }

    @Test
    public void readLinesNullFileTest() {
        assertEquals(frNull.readLines(), null, "Unexpected return value");
    }

    @Test
    public void readLinesEmptyFileTest() {
        assertEquals(frEmpty.readLines().size(), 0, "Unexpected return value");
    }
}
