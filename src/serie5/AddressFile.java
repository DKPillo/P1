package serie5;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressFile {
	private String filename;
	private ArrayList<Address> inAdds;

	public AddressFile(String fileToOpen) {
		filename = fileToOpen;
	}
	
	private String toLine(Address addr) {
		String output = addr.toString();
		return output;
	}
	
	private Address parseLine(String line) {
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
			System.out.println(exception.getMessage());
		}
		
		Address outAddress = new Address(newId, newName, newStreet, newZipCode, newCity);
		return outAddress;
	}
	
	private void save(ArrayList<Address> addresses) {
		
	}
	
	private ArrayList<Address> load() {
		
	}
}
