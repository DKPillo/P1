package serie6;

import java.math.BigInteger;

/******************************************************************************
 * Programmierung 1 (HS 11)
 * Serie 6 
 *  
 * Salim Hermidas 
 * 11-125-382
 *
 */ 

public class FactBig {

	public static void main(String[] args) {
		//WORKS TO java FactBig 8964
		BigInteger a;
		BigInteger b = new BigInteger(String.valueOf(1));
		try {
			a = new BigInteger(args[0]);
		} catch (Exception e) {
			a = b;
			System.out.println("error");
		}
		if (a.compareTo(b) < 0) {a = b;}
		System.out.println(factorial(a));

		/*for (int c = 1; c < 51; c++) {
			BigInteger inp = new BigInteger(String.valueOf(c));
			System.out.print(c + ": ");
			System.out.println(factorial(inp));
		}*/
	}
	
	public static BigInteger factorial(BigInteger n) {
		BigInteger fact;
		BigInteger one = new BigInteger(String.valueOf(1));
		if (n.equals(one)) {
			fact = n;
		} else {
			fact = n.multiply(factorial(n.subtract(one)));
		}
		return fact;
	}

}
