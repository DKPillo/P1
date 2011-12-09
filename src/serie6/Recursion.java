package serie6;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class Recursion {
private final static int BIGGEST = 30;
private final static int STEPPS = 3;

	public static void main(String[] args) {
		System.out.println ("Iterative");
		iterative();
		System.out.println ("Recursive");
		recursive(BIGGEST);
	}
	
	public static void iterative() {
		for (int i=0; i <=BIGGEST; i += STEPPS) {
			System.out.println (i);
		}
	}
	
	public static void recursive(int actual) {
		if (actual == 0) {
			System.out.println(actual);
		} else {
			recursive(actual - STEPPS);
			System.out.println(actual);
		}
	}
}
