package serie4;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class Book implements IArticle{
	private int id;
	private String title;
	private String author;
	private int year;
	private int price; // CHF

	/** constructor */
	public Book(int id, String title, String author, int year, int price){
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getDescription() {
		String output = id + " (Book) " + title + ", by " + author + ", " + year + ", " + price + " CHF";
		return output;
	}
}
