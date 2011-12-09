package serie6;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class Factorial {

	public static void main(String[] args) {
		int a;
		try {
			a = Integer.parseInt(args[0]);
		} catch (Exception e) {
			a = 1;
		}
		if (a < 1) {a = 1;}
		System.out.println(factorial(a));
	}
	
	public static long factorial(int n) {
		long fact;
		if (n == 1) {
			fact = n;
		} else {
			fact = n * factorial(n-1);
		}
		return fact;
	}

}
