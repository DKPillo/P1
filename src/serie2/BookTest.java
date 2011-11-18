package serie2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookTest {

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	public static void main(String[] args) {
		Date date2 = new Date();
		
		try {
			date2 = stringToDate("12.01.1992");
		} catch (ParseException e) {
			date2.setTime(1234567890);
		}
		
		Book book1 = new Book();
		Book book2 = new Book(123, "Titel_2", "Salim Hermidas", date2);

		book1.setId(122);
		book1.setTitle("Titel_1");
		book1.setAuthor("Salim Hermidas");
		book1.setDateAsString("11.01.1992");

		System.out.println("Book 1");
		System.out.println(book1.toString());
		System.out.println("Book 2");
		System.out.println(book2.toString());

		System.out.println("Age Book 1: " + book1.age());
		System.out.println("Age Book 2: " + book2.age());
		
		System.out.println("");

		System.out.println("Book 1 with get");
		System.out.println("ID: " + book1.getId());
		System.out.println("Title: " + book1.getTitle());
		System.out.println("Author: " + book1.getAuthor());
		System.out.println("Date: " + book1.getDateAsString());

		System.out.println("");
		
		System.out.println("Set book2 with input");
		try {
			book2.input();
		} catch (ParseException e) {
			System.out.println("there was an error while input()");
		}

		System.out.println("");
		
		System.out.println("Book 2");
		System.out.println(book2.toString());

	}


	/** Converts the String object s into a Date object */
	private static Date stringToDate(String s) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}
