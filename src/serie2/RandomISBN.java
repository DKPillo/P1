package serie2;

import java.util.Random;

public class RandomISBN {

	public static void main(String args[]){
		// TO DO
		System.out.println("1st ISBN : " + makeISBN());
		System.out.println("1nd ISBN : " + makeISBN());
		System.out.println("1rd ISBN : " + makeISBN());
	}

	/** generates and returns a random ISBN number in the format XX-XXX-XX-C */
	public static String makeISBN(){

		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		// TO DO
		int l, b, v, c;
		int l1, l2, b1, b2, b3, v1, v2;
		String lString, bString, vString;
		
		Random r = new Random();
		l = r.nextInt(100);
		b = r.nextInt(900) + 100;
		v = r.nextInt(100);

		lString =  (new Integer(l)).toString();
		bString =  (new Integer(b)).toString();
		vString =  (new Integer(v)).toString();
		
		if (lString.length() == 1) {
			lString = "0" + lString;
		}
		if (vString.length() == 1) {
			vString = "0" + vString;
		}

		l1 = Integer.parseInt(lString.substring(0, 1));
		l2 = Integer.parseInt(lString.substring(1, 2));

		b1 = Integer.parseInt(bString.substring(0, 1));
		b2 = Integer.parseInt(bString.substring(1, 2));
		b3 = Integer.parseInt(bString.substring(2, 3));

		v1 = Integer.parseInt(vString.substring(0, 1));
		v2 = Integer.parseInt(vString.substring(1, 2));
		
		c = doChkSum(l1, l2, b1, b2, b3, v1, v2);
		
		laendercode = "" + l1 + l2;
		bandnr = "" + b1 + b2 + b3;
		verlagsnr = "" + v1 + v2;
		checksum = "" + c;

		return laendercode+"-"+bandnr+"-"+verlagsnr+"-"+checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i){
		int doubled = 2 * i;
		if (doubled >= 10){
			doubled = doubled - 9;
		}
		return doubled;
	}
	
	public static int doChkSum(int l1c, int l2c, int b1c, int b2c, int b3c, int v1c, int v2c) {
		int chkSumOut;
		
		//L1#2 + L2 + B1#2 + B2 + B3#2 + V1 + V2#2 mod 10
		chkSumOut = (hashOp(l1c) + l2c + hashOp(b1c) + b2c + hashOp(b3c) + v1c + hashOp(v2c)) % 10;
		
		return chkSumOut;
	}
	
}
