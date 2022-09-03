package project3;

public class Record {
	
// variable initializations 
  int id;
  String title;
  String author;
  Record next;

  // method to set all the this values to itself 
  Record(int i, String t, String a, Record r) {
    this.id = i;
    this.title = t;
    this.author = a;
    this.next = r;
    
  } // end of Record 
} // end of Record class 
