package serie2;

import java.util.Date;
import java.io.IOException;
import java.text.*;
import java.util.Scanner;


public class Book{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	// constructors
	public Book(int idIn, String titleIn, String authorIn, Date dateIn) {
		id = idIn;
		title = titleIn;
		author = authorIn;
		dateOfPublication = dateIn;
	}
	public Book() {
		
	}

	/** Returns the age of the book in days since publication */
	public int age(){
		//1000 ms = 1s, 60s = 1 min, 60 min = 1h, 24h = 1 day
		//1 Day = 1000 * 60 * 60 * 24
		long divide = 1000 * 60 * 60 * 24;
		Date d = new Date();
		long timeNow = d.getTime();
		long pubDate = dateOfPublication.getTime();
		
		long between = timeNow - pubDate;
		
		long daysSincePub = between / divide;
		return (int) daysSincePub;
	}

	/** Returns a String representation of the book */
	public String toString(){
		String a, b, c, d;

		a = "Book ID: " + (new Integer(id)).toString() + "\n";
		b = "Book Title: " + title + "\n";
		c = "Book Author: " + author + "\n";
		d = "Book Date: " + dateToString(dateOfPublication) + "\n";
		
		return a + b + c + d;
	}

	/** Reads all book data from user input 
	 * @throws IOException */
	public void input() throws ParseException {
		Scanner scn = new Scanner(System.in);
		System.out.println("Please enter id: ");
		String idSt = scn.nextLine();
		id = Integer.parseInt(idSt);
		System.out.println("Please enter title: ");
		title = scn.nextLine();
		System.out.println("Please enter authors name: ");
		author = scn.nextLine();
		System.out.println("Please enter date (dd.MM.yyyy): ");
		String scnDate = scn.nextLine();
		dateOfPublication = stringToDate(scnDate);
	}

	// Get-/Set-methods
	//ID
	public int setId(int newId) {
		id = newId;
		return id;
	}
	public int getId() {
		return id;
	}
	
	//title
	public String setTitle(String newTitle) {
		title = newTitle;
		return title;
	}
	public String getTitle() {
		return title;
	}
	
	//author
	public String setAuthor(String newAuthor) {
		author = newAuthor;
		return title;
	}
	public String getAuthor() {
		return author;
	}
	
	//date
	public Date setDate(Date newDate) {
		dateOfPublication = newDate;
		return dateOfPublication;
	}
	public Date getDate() {
		return dateOfPublication;
	}
	
	//dateAsTring
	public String setDateAsString(String newDateString) {
		try {
			dateOfPublication = stringToDate(newDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//dateOfPublication = dateOfPublication;
			//do Nothing
		}
		return dateToString(dateOfPublication);
	}
	public String getDateAsString() {
		return dateToString(dateOfPublication);
	}
	
	// private methods --------------------------------------------
	/** Converts the Date object d into a String object */
	private String dateToString(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	private Date stringToDate(String s) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}

