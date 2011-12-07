package serie5;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 5 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressFile {
	private String filename;

	public AddressFile(String fileToOpen) {
		filename = fileToOpen;
	}
	
	protected String toLine(Address addr) {
		String output = addr.toString();
		return output;
	}
	
	protected Address parseLine(String line) throws AddressFileException {
		int newId, newZipCode;
		String newName, newStreet, newCity;
		
		AddressFileException problem = new AddressFileException ("Input value is out of range.");
		Scanner lineReader = new Scanner(line).useDelimiter(",");

		try {
			newId = Integer.parseInt(lineReader.next().trim());
			newName = lineReader.next().trim();
			newStreet = lineReader.next().trim();
			newZipCode = Integer.parseInt(lineReader.next().trim());
			newCity = lineReader.next().trim();
		} catch (Exception exception) {
			throw problem;
		}
		
		Address outAddress = new Address(newId, newName, newStreet, newZipCode, newCity);
		return outAddress;
	}
	
	public void save(ArrayList<Address> addresses) throws IOException {
		FileWriter fw = new FileWriter (filename);
	    BufferedWriter bw = new BufferedWriter (fw);
	    PrintWriter outFile = new PrintWriter (bw);
	    
		for(Address a : addresses){
			outFile.print(toLine(a));
			outFile.println();
		}
		
		outFile.close();
	}
	
	public ArrayList<Address> load() throws AddressFileException, FileNotFoundException {
		ArrayList<Address> tmpAds = new ArrayList<Address>();

		Scanner docReader = new Scanner(new File(filename));
		
		while (docReader.hasNextLine()) {
			tmpAds.add(parseLine(docReader.nextLine()));
		}
		
		return tmpAds;
	}
}
