/*-------------------------------------------------
 * Program 0
 * Author: Kelsey Cole
 * To run: "java prog0.java"
 * Purpose:
 *	Look up a list of employees and their
 *	salary by location 
 * ------------------------------------------------*/

import java.util.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.*; 
import java.io.*; 
import java.util.Scanner; //for user input

class prog0 {

//-----------------------------------------------------FUNCTION: readFileInList
// I used a tutorial from https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
// for this function

public static List<String> readFileInList(String fileName) 
  { 
  
    List<String> lines = Collections.emptyList(); 
    try
    { 
      lines = 
       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
    } 
  
    catch (IOException e) 
    { 
  
      // do something 
      e.printStackTrace(); 
    } 
    return lines; 
  } 
 
 //----------------------------------------------------------------FUNCTION: MAIN
    public static void main(String[] args) {
	
	//-------------------------------------------file processing
	
	//Initialize arraylists to parse file information
	ArrayList<String> addrNamesList = new ArrayList<String>(); ;
	ArrayList<String> addressesList = new ArrayList<String>(); ;
  	ArrayList<String> salariesList = new ArrayList<String>(); ;	
	ArrayList<String> salNamesList = new ArrayList<String>(); ;
   	
	//read data files 
	List<String> addressesLines = readFileInList("./personnel_addresses.txt");
	List<String> salariesLines = readFileInList("./personnel_salaries.txt");

	//sort lines alphabetically so that names, addresses, and salaries that
	//correspond to the same person will have the same index
	java.util.Collections.sort(addressesLines);
       	java.util.Collections.sort(salariesLines);	


	//parse lines for names and addresses
        for (String line : addressesLines){
		String[] addresses = line.split("\\|");
		addrNamesList.add(addresses[0]);
		addressesList.add(addresses[1]);
	}
	
	//parse lines for names and salaries
	for (String line : salariesLines){
		String[] salaries = line.split("\\|");
		salNamesList.add(salaries[0]);
		salariesList.add(salaries[1]);
	}
	
	//-------------------------------------read in user input
    	
	System.out.println("Enter a town: ");

    	
    	Scanner input = new Scanner(System.in);
    	
    	String userTown = input.next();
 	
	//---------------------------------find matches to user input
	
	int numMatches = 0; //keeps track of the number of matches
        int curIndex = 0; //keeps track of the current index in the list
	
	for(String townName : addressesList){
		if(townName.contains(userTown)){
    			//System.out.println(townName);
			System.out.println(salNamesList.get(curIndex) + " : " + salariesList.get(curIndex));
			numMatches++;
		}
		curIndex++;
	}
	
	//To quote Dr. Goldsmith: "sanity check"

	System.out.println("Number of matches: " + numMatches);
	}
}
