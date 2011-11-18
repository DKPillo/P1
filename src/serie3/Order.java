package serie3;

public class Order {

	private static int orderNumber = 0;
	
	private int id;
	private String customerName;
	private String customerAddress;
	private int size;
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private Book book5;
	
	//Constructors
	public Order() {
		orderNumber++;
		id = orderNumber;
		size = 0;
		customerName = "";
		customerAddress = "";
	}
	
	public Order(String tmpName, String tmpAddress) {
		orderNumber++;
		id = orderNumber;
		size = 0;
		customerName = tmpName;
		customerAddress = tmpAddress;
	}
	
	
	//Public Classes
	public String toString() {
		String output = "";
		output = output + "Order ID: " + id + "\n";
		output = output + "Customer name: " + customerName + "\n";
		output = output + "Customer address: " + customerAddress + "\n\n";
		
		if (size == 1) {
			output =  output + "Book 1: " + book1.toString() + "\n";
		}
		if (size == 2) {
			output =  output + "Book 1: " + book1.toString() + "\n";
			output =  output + "Book 2: " + book2.toString() + "\n";
		}
		if (size == 3) {
			output =  output + "Book 1: " + book1.toString() + "\n";
			output =  output + "Book 2: " + book2.toString() + "\n";
			output =  output + "Book 3: " + book3.toString() + "\n";
		}
		if (size == 4) {
			output =  output + "Book 1: " + book1.toString() + "\n";
			output =  output + "Book 2: " + book2.toString() + "\n";
			output =  output + "Book 3: " + book3.toString() + "\n";
			output =  output + "Book 4: " + book4.toString() + "\n";
		}
		if (size == 5) {
			output =  output + "Book 1: " + book1.toString() + "\n";
			output =  output + "Book 2: " + book2.toString() + "\n";
			output =  output + "Book 3: " + book3.toString() + "\n";
			output =  output + "Book 4: " + book4.toString() + "\n";
			output =  output + "Book 5: " + book5.toString() + "\n";
		}
		
		output = output + "\nTotal Price: " + getTotalPrice() + "\n";
		output = mkNice(output);
		
		return output;
	}
	
	public int getTotalPrice() {
		int tPrice = 0;
		if (size == 0) {
			tPrice = 0;
		} else {
			tPrice = doTotal();
		}
		
		return tPrice;
	}
	
	//Get-/Set-Methods
	public void setCustomerName(String newName) {
		customerName = newName;
	}

	public void setCustomerAddress(String newAddress) {
		customerAddress = newAddress;
	}
	
	public void addBook(Book newBook) {
		if (size == 0) {
			book1 = newBook;
			size = 1;
		}
		else if (size == 1) {
			book2 = newBook;
			size = 2;
		}
		else if (size == 2) {
			book3 = newBook;
			size = 3;
		}
		else if (size == 3) {
			book4 = newBook;
			size = 4;
		}
		else if (size == 4) {
			book5 = newBook;
			size = 5;
		}
		else if (size > 4) {
			System.out.println("\n" + "No book added!" + "\n" + "You have 5 books yet." + "\n");
		}
	}
	
	//Help Methods
	private int doTotal() {
		int total = 0;
		if (size == 1) {
			total = book1.getPrice();
		}
		if (size == 2) {
			total = book1.getPrice() + book2.getPrice();
		}
		if (size == 3) {
			total = book1.getPrice() + book2.getPrice() + book3.getPrice();
		}
		if (size == 4) {
			total = book1.getPrice() + book2.getPrice() + book3.getPrice() + book4.getPrice();
		}
		if (size == 5) {
			total = book1.getPrice() + book2.getPrice() + book3.getPrice() + book4.getPrice() + book5.getPrice();
		}
		return total;
	}
	
	private String mkNice(String input) {
		String up = "\n*\n**\n***\n****\n*****\n******\n*******\n";
		String down = "\n*******\n******\n*****\n****\n***\n**\n*\n\n";
		input = up + input + down;
		return input;
	}
	
}
