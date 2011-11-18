package serie2;

public class Serie2a3 {
	public static void main ( String args []){
		
		A a1 = new A();
		A a2 = new A();
		a1. increment ();
		System .out . println (a1 +"/"+ a2 );
		a2 = a1;
		a2. increment ();
		System .out . println (a1 +"/"+ a2 );

		String s1 = " ROCK ";
		String s2 = s1;
		s2 = s2. toLowerCase ();
		System .out . println (s1 +"/"+ s2 );

		int j=1;
		int i=j;
		j++;
		System .out . println (j +"/"+ i);
	}
}