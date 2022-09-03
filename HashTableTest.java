package project3;

import java.util.*;
import java.io.*;

public class HashTableTest {
	
  BufferedReader reader; // create reader for file txt
  HashTableBuilder table; // create table from HashTableBuilder class 
  int tableSize;

  public static void main(String[] args){
	  
	  // read in data from file and print table 
    HashTableTest test = new HashTableTest("datafile.txt");
    test.table.print();
  } // end of main method

  HashTableTest(String filename){
    try { // try block 
    	
      reader = new BufferedReader(new FileReader(filename)); // need to look at BufferedReader
      ArrayList<String> tempArray = new ArrayList<String>(); // create arrayList
      FileData fd;
      
      // while fileData is null 
      while ((fd = this.readNextRecord()) != null) {
    	  // for loop to check keyword length 
        for (int i=0; i<fd.keywords.length; i++) {
        	// if tempArray contains keywords, increment table size 
          if (!tempArray.contains(fd.keywords[i])) {
            tempArray.add(fd.keywords[i]);
            tableSize++; // increment 
          } // end of if statement 
        } // end of for loop 
      } // end of while statement 
      
      // HashTableBuilder object table 
      table = new HashTableBuilder(tableSize);
      //System.out.printf("Number of Keywords: %d\n", tableSize);

      // read the file name 
      reader = new BufferedReader(new FileReader(filename));
      /* READS DATAFILE.TXT INTO DATASTRUCTURE  */
      while ((fd = readNextRecord()) != null){
    	  // for loop insert keywords from filedata into table 
        for (int i=0; i<fd.keywords.length; i++){
          table.insert(fd.keywords[i], fd);
        } // end of for loop 
      } // end of while statement 
    }  // end of try
    
    
    catch (IOException e) {
      e.printStackTrace();
    } // end of catch  
    
    finally {
      try {
        if (reader!=null) reader.close(); // if reader is null, close file 
      }
      catch (IOException e){
        e.printStackTrace();
      } // end of catch 
    } 
  } // end of HashTableTest 

  public FileData readNextRecord(){
		if(reader == null){ // if reader is null, open file 
			System.out.println("Error: You must open the file first.");
			return null;
		} // end of if statement 
		
		else{
			FileData readData; // create fileData 
			
			try{
				String data=reader.readLine(); // read line for reader 
				if(data==null) return null;
                   // FileData(      int id,            String title,      String author,     int keywordCount)
				readData= new FileData(Integer.parseInt(data), reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()));

				// for loop to read in each line 
				for (int i=0; i<readData.keywords.length; i++) {
					readData.keywords[i] = reader.readLine();
				} // end of for loop
				
				String space=reader.readLine(); // space the string for format in file 
				
				// if the space is null, output error to user 
				if((space!=null) && (!space.trim().equals(""))){
					System.out.println("Error in file format");
					return null;
				} // end of if statement 
			} // end of try 
			
			// output error to user 
			catch(NumberFormatException e){
				System.out.println("Error Number Expected! ");
				return null;
			} // end of catch 
			
			catch(Exception e){
				System.out.println("Fatal Error: "+e);
				return null;
			} // end of catch 
			
			return readData;
		} // end of else statement 
	} // end of readNextRecord
} // end of class 