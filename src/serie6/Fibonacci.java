package serie6;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println("Recursive");
		for (int a = 1; a < 51; a++) {
			System.out.println(fib(a));
		}
	}
	
	public static long fib(int i) {
		long retVal;
		if (i == 1) {
			retVal = 0;
		} else if (i == 2) {
			retVal = 1;
		} else {
			retVal = fib(i - 1) + fib(i - 2);
		}
		return retVal;
	}

}
