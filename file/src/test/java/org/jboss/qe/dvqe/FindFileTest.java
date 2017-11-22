package org.jboss.qe.dvqe;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeClass;

public class FindFileTest {

    File basicDir, testFileinBasic, testFileinOverlay, testDir, testFileSameNameBasic, testFileSameNameOverlay,
            testFileinBasicSameName, testFileInOverlaySameName;
    File[] overlayDirs, overlayDirsFiles, overlayDirsFilesSameName;
    FindFile testObj;

    @BeforeClass
    public void beforeClass() throws IOException {

        basicDir = Files.createTempDirectory("basicDir").toFile();
        basicDir.deleteOnExit();
        testFileinBasic = new File(basicDir, "basicFile");
        testFileinBasic.createNewFile();
        testFileinBasic.deleteOnExit();

        testFileinBasicSameName = new File(basicDir, "sameFileName");
        testFileinBasicSameName.createNewFile();
        testFileinBasicSameName.deleteOnExit();

        overlayDirs = new File[3];
        overlayDirsFiles = new File[3];
        overlayDirsFilesSameName = new File[3];
        for (int i = 0; i < 3; i++) {
            overlayDirs[i] = Files.createTempDirectory("overlayDir").toFile();
            overlayDirs[i].deleteOnExit();
            overlayDirsFiles[i] = new File(overlayDirs[i], "File" + i);
            overlayDirsFiles[i].createNewFile();
            overlayDirsFiles[i].deleteOnExit();
            overlayDirsFilesSameName[i] = new File(overlayDirs[i], "sameFileName");
            overlayDirsFilesSameName[i].createNewFile();
            overlayDirsFilesSameName[i].deleteOnExit();
        }

        testDir = Files.createTempDirectory(Paths.get(basicDir.getPath()), "DirFile").toFile();
        testDir.deleteOnExit();
        testObj = new FindFile(basicDir,overlayDirs);
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
    public void findFileInOverlayDirTest() {
        for (File file : overlayDirsFiles) {
            assertEquals(testObj.find(file.getName()), file, "unexpected file retured");
        }
    }

    @Test
    public void findFileIfDirTest() {
        assertEquals(testObj.find(testDir.getName()), null, "unexpected file retured");
    }

    @Test
    public void findFileSameNameInDirsTest() {
        assertEquals(testObj.find("sameFileName"), overlayDirsFilesSameName[2], "unexpected file retured");
    }
}
