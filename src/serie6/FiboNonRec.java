package serie6;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class FiboNonRec {

	public static void main(String[] args) {
		System.out.println("Not Recursive");
		for (int a = 1; a < 51; a++) {
			System.out.println(fib(a));
		}
	}
	
	public static long fib(int i) {
		long retVal = 0;
		long first = 0, second = 0;
	
		for (int a = 1; a <= i; a++) {
			if (a == 1) {
				first = 0;
				retVal = first;
			} else if (a == 2) {
				second = 1;
				retVal = second;
			} else {
				retVal = first + second;
				first = second;
				second = retVal;
			}
		}
		
		return retVal;
	}

}
