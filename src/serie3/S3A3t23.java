package serie3;

public class S3A3t23 {
	
	public static void main(String [] args) {
		System.out.println("While");
		asWhile();
		System.out.println("Do-While");
		asDo();
		System.out.println("For");
		asFor();
	}

	public static void asWhile() {
		int i=1;
		while (i <10){
		i++;
		System .out . println (i);
		}
	}

	public static void asDo() {
		int i=1;
		do {
		i++;
		System .out . println (i);
		} while (i < 10);
	}

	public static void asFor() {
		for (int i = 2; i <= 10; i++) {
			System.out.println(i);
		}
	}
	
	public static String dreierReihe (int limit ){
		String result = "";
		for(int counter = 3; counter != limit ; counter += 3){
		result = result + counter + " ";
		}
		return result ;
		}
	
}
