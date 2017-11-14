package org.jboss.qe.dvqe;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.BeforeClass;

public class GetFileTest {

    File basicDir, overlayDir, testFile, testFile2;
    String testFileName, testFileName2;
    GetFile testObj;

    @BeforeClass
    public void beforeClass() throws IOException {

        Path basicPath = Files.createTempDirectory("basicDir");
        basicDir = basicPath.toFile();
        basicDir.deleteOnExit();

        Path overlayPath = Files.createTempDirectory("overlayDir");
        overlayDir = overlayPath.toFile();
        basicDir.deleteOnExit();

        testFile = Files.createTempFile(basicPath, "basicFolderFile", ".txt").toFile();
        testFile.deleteOnExit();
        testFileName = testFile.getName();

        testFile2 = Files.createTempFile(basicPath, "overlayFolderFile", ".txt").toFile();
        testFileName2 = testFile2.getName();
        testFile2.deleteOnExit();

        testObj = new GetFile(basicDir, overlayDir);

    }

    @Test
    public void findFileInBasicDirTest() {
        assertEquals(testObj.findFile(testFileName), testFile, "unexpected file retured");
    }

    @Test
    public void findFileNonExistentFileTest() {
        assertEquals(testObj.findFile("madeUpFile"), null, "unexpected value retured");
    }

    @Test
    public void findFileInOverLayDirTest() {
        assertEquals(testObj.findFile(testFileName2), testFile2, "unexpected file retured");
    }
}
