package serie4;

import java.util.ArrayList;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 4 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */

public class Order {

	private static int orderNumber = 0;
	
	private int id;
	private String customerName;
	private String customerAddress;
	private int size;
	private ArrayList<IArticle> shoppingCart = new ArrayList<IArticle>();
	
	//Constructors
	public Order() {
		orderNumber++;
		id = orderNumber;
		size = 0;
		customerName = "";
		customerAddress = "";
	}
	
	public void add(IArticle newAritcle) {
		shoppingCart.add(newAritcle);
		size = shoppingCart.size();
	}
	
	//Public Classes
	public int getId() {
		return id;
	}
	
	public ArrayList<IArticle> getOrderedArticles() {
		return shoppingCart;
	}
	
	public String toString() {
		String output = "";
		
		output = output + "\nTotal Price: " + getTotalPrice() + "\n";
		
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

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}
	
	
	//Help Methods
	private int doTotal() {
		int total = 0;
		for(IArticle a : this.shoppingCart){
			total = total + a.getPrice();
		}
		return total;
	}
	

	
}
