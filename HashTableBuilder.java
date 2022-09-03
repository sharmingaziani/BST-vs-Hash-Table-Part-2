package project3;

class HashTableBuilder {
	
  Element [] hashTable; // create hashTable 
  int tableSize; // tableSize initialization 

  HashTableBuilder(int numKeyWords) {
    //tableSize = nextPrime(numKeyWords * 2);
    tableSize = nextPrime(numKeyWords * 2); // multiply numKeyWords by 2
    hashTable = new Element[tableSize]; // create hashtable 
  } // end of HashTableBuilder constructor 

  private Element createNewElement(String keyword, Record recordToAdd) {
    return new Element(keyword, recordToAdd); // return newly created element 
  } // end of Element createNewElement
  
  void insert(String keyword, FileData fd) {
    Record recordToAdd = new Record(fd.id, fd.title, fd.author, null); // new Record object 
    int index = find(keyword);
    
    // if index equals -1, insert the keyword
    if (index == -1)
      insert(keyword, recordToAdd);
    else { // else index hashtable 
      //System.out.printf("%s: %d\n", keyword, index);
      recordToAdd.next = hashTable[index].head;
      hashTable[index].head = recordToAdd;
    } // end of else statement 
    
    /*if (!contains(keyword, recordToAdd)) {
      insert(keyword, recordToAdd);*/
    
  } // end of insert method 

  private void insert(String keyword, Record recordToAdd) {
    int key = convertStringToInt(keyword); // initialize key and convert from String 
    //System.out.printf("%d %d\n", key, tableSize);
    int index = key % tableSize; // hash index formula 
    int  probe = 1; // initialize probe
    
    while (hashTable[index] != null) { // while hashtable index does not equal null
      System.out.printf("%s conflicts with %s at index: %d\n", keyword, hashTable[index].keyword, index); // print out values to user 
      index = getNextQuadProbIndex(key, probe++); // index equals next probe index 
    } // end of while statement 
    hashTable[index] = createNewElement(keyword, recordToAdd); // indexed new element 
  } // end of insert method 

  int find(String keyword) {
    int key = convertStringToInt(keyword); // convert key to string 
    //System.out.printf("%d %d\n", key, tableSize);
    int index = key % tableSize; // index formula 
    int  probe = 1;
    
    while (hashTable[index] != null) {
    	
    	// if keyword equals 0, return index back 
      if (hashTable[index].keyword.compareTo(keyword) == 0)
        return index;
      index = getNextQuadProbIndex(key, probe++); // index is next probe 
    } // end of while statement 
    return -1;
  } // end of find method 

  private int getNextQuadProbIndex(int key, int probe) {
	  
    return ((key % tableSize) + (int) Math.pow(probe, 2)) % tableSize; // return formula for hashtable 
  } // end of getNextQuadProbIndex 

  private int convertStringToInt(String keyword) {
    int sum = 0;
    
    // for loop to find keywords at index i and return value 
    for (int i=0; i < keyword.length(); i++)
      sum += (int) keyword.charAt(i);
    return sum;
  } // end of convertStringToInt
  
  private int nextPrime(int num) {
      num++; // increment num 
      
      // for loop to find i value 
      for (int i = 2; i < num; i++) {
    	  // if num mod i equals 0 
         if(num%i == 0) {
            num++; // increment 
            i=2; // i = 2
         } else { // continue with block 
            continue;
         } // else continue 
      } // end of for loop 
      return num; // return value
   } // end of nextPrime 

   public void print() {
	   // initialize int values 
     int count = 0;
     int printWrap = 0;
     
     System.out.print("\n\n\n"); // print to organize output 
     
     // for loop for index size 
     for (int index=0; index<tableSize; index++) {
    	 // if hashtable index == null, output to user 
       if (hashTable[index] != null) {
         System.out.printf("index [%d]: %s (E ---> int): %d\n", index, hashTable[index].keyword, convertStringToInt(hashTable[index].keyword));
         Record rec = hashTable[index].head; // Record object equals hashtable head 
         //System.out.printf("%d|%s|%s\n ---> ", rec.id, rec.author, rec.title);
         System.out.print("\t\t");
         // while rec object is null 
         while(rec != null) {
        	 // output to user 
           if (++printWrap%3 == 0) {
             System.out.println();
             System.out.print("\t\t");
           } // end of if statement 
           
           //System.out.printf("\t%s\n",r.title);
           System.out.printf("%d|%s|%s ---> ", rec.id, rec.author, rec.title);
           rec = rec.next;
           //printWrap++;
         } // end of while statement 
         System.out.println("null\n");
         printWrap = 0;

       } // end of if 
     } // end of for loop 
   } // end of print 
} // end of HashTableBuilder 
