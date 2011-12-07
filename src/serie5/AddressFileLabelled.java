package serie5;

import java.util.Scanner;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 5 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */

public class AddressFileLabelled extends AddressFile {

	public AddressFileLabelled(String fileToOpen) {
		super(fileToOpen);
	}
	
	protected String toLine(Address addr) {
		String output = "";
		output = output + "id : " + addr.getId() + " ; ";
		output = output + "name : " + addr.getName() + " ; ";
		output = output + "street : " + addr.getStreet() + " ; ";
		output = output + "zip : " + addr.getZipCode() + " ; ";
		output = output + "city : " + addr.getCity() + " ;";
		return output;
	}
	
	protected Address parseLine(String line) throws AddressFileException {
		int newId, newZipCode;
		String newName, newStreet, newCity;
		
		AddressFileException problem = new AddressFileException ("Input value is out of range.");
		Scanner lineReader = new Scanner(line);

		try {
			lineReader.findInLine("id" + "[\\s]*:[\\s]*([^;]*)");
			newId = Integer.parseInt(lineReader.match().group(1).trim());
			
			lineReader.findInLine("name" + "[\\s]*:[\\s]*([^;]*)");
			newName = lineReader.match().group(1).trim();

			lineReader.findInLine("street" + "[\\s]*:[\\s]*([^;]*)");
			newStreet = lineReader.match().group(1).trim();

			lineReader.findInLine("zip" + "[\\s]*:[\\s]*([^;]*)");
			newZipCode = Integer.parseInt(lineReader.match().group(1).trim());

			lineReader.findInLine("city" + "[\\s]*:[\\s]*([^;]*)");
			newCity = lineReader.match().group(1).trim();
		} catch (Exception exception) {
			throw problem;
		}
		
		Address outAddress = new Address(newId, newName, newStreet, newZipCode, newCity);
		return outAddress;
	}
}
