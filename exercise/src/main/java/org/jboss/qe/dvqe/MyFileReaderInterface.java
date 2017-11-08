package org.jboss.qe.dvqe;

import java.util.List;

/**
 * MyFileReader can read a text file from given location
 *
 **/
public interface MyFileReaderInterface {
    /**
     * Method getNumberOfLines returns the number of rows this file has
     * 
     * @return returns the number of rows this file has or -1 on failure.
     *
     **/
    int getNumberOfLines();

    /**
     * Method getNumberOfNonEmptyLines returns number of non-empty rows from the file
     * 
     * @return returns number of non-empty rows from the file or -1 on failure.
     **/
    int getNumberOfNonEmptyLines();

    /**
     * Method readLines reads contents of the file an returns lines read.
     * 
     * @return the lines read or null on failure
     **/
    List<String> readLines();

    /**
     * Method readFirstNLines reads first n lines from the file.
     * 
     * @return the lines read or null on failure
     * @param numOfLines
     *            The number of lines to be read.
     **/
    List<String> readFirstNLines(int numOfLines);

}
