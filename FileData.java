package project3;

import java.util.Scanner;
import java.io.*;

class FileData{
    /** Class: FileData
     *  Contains the content of a record found in the input file. Each
     *  FileData object contains exactly one record. An object of this
     *  type will be returned by readNextRecord(..) function on successful
     *  read.
     *  Fields:
     *  id : ID of the record
     *  title : contains the title of the paper
     *  author: contains the author of the paper
     *  keywords is an array of all keywords related to that paper.
     */

	// variable initializations 
  	int id;
  	String title;
  	String author;
  	String keywords[]; // array for keywords String 

	  /* Constructor */
    FileData(int id, String title, String author, int keywordCount) {
		    this.id=id; // this id is equal to id 
        this.title=title; // this title is equal to title 
        this.author=author; // this author is equal to author
        keywords=new String[keywordCount];
        
        // for loop to insert null values 
        for(int i=0;i<keywords.length; i++) {
          keywords[i]=null;
          
        } // end of for loop
    } // end of fileData
} // end of FileData
