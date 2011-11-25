package serie4;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class DVD implements IArticle {
	private int id;
	private String title;
	private int year;
	private int price; // CHF

	/** constructor */
	public DVD(int id, String title, int year, int price){
		this.id = id;
		this.title = title;
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
		String output = id + " (DVD) " + title + ", " + year + ", " + price + " CHF";
		return output;
	}
}
