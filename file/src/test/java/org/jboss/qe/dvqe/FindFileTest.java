package org.jboss.qe.dvqe;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.annotations.BeforeClass;

public class FindFileTest {

    File basicDir, overlayDir, testFileinBasic, testFileinOverlay, testDir;
    FindFile testObj;

    @BeforeClass
    public void beforeClass() throws IOException {

        Path basicPath = Files.createTempDirectory("basicDir");
        basicDir = basicPath.toFile();
        basicDir.deleteOnExit();
        Path overlayPath = Files.createTempDirectory("overlayDir");
        overlayDir = overlayPath.toFile();
        basicDir.deleteOnExit();
        testFileinBasic = Files.createTempFile(basicPath, "basicDirFile", ".txt").toFile();
        testFileinBasic.deleteOnExit();
        testFileinOverlay = Files.createTempFile(overlayPath, "overlayDirFile", ".txt").toFile();
        testFileinOverlay.deleteOnExit();
        testDir = Files.createTempDirectory(basicPath, "DirFile").toFile();
        testDir.deleteOnExit();
        testObj = new FindFile(basicDir, overlayDir);
    }

    @Test
    public void findFileInBasicDirTest() {
        assertEquals(testObj.find(testFileinBasic.getName()), testFileinBasic, "unexpected file retured");
    }

    @Test
    public void findFileNonExistentFileTest() {
        assertEquals(testObj.find("madeUpFile"), null, "unexpected value retured");
    }

    @Test
    public void findFileInOverLayDirTest() {
        assertEquals(testObj.find(testFileinOverlay.getName()), testFileinOverlay, "unexpected file retured");
    }

    @Test
    public void findFileInCaseDirTest() {
        assertEquals(testObj.find(testDir.getName()), null, "unexpected file retured");
    }
}
