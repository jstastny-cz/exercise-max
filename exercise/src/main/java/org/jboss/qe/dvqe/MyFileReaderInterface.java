package org.jboss.qe.dvqe;

import java.util.List;
/**
* MyFileReader can read a text file from given location 
*
**/
public interface MyFileReaderInterface{
/** 
* Method getNumberOfLines returns the number of rows this file has.
*
**/
int getNumberOfLines();
/**
* Method getNumberOfNonEmptyLines returns number of non-empty rows from the file.
**/
int getNumberOfNonEmptyLines();
/**
* Method readLines reads contents of the file an returns lines read.
* @return the lines read
**/
List<String> readLines();
/**
* Method readFirstNLines reads first n lines from the file.
* @return the lines read.
**/
List<String> readFirstNLines(int n);

}
