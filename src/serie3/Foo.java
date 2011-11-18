package serie3;

public class Foo {
	private int a = 0;
	public static int b = 0;

	public void incrementA () {
		a++;
	}

	public void incrementB () {
		b++;
	}

	public String toString () {
		return "a="+ a+", b="+ b;
	}

	public static void main ( String [] args ) {
		Foo f1 = new Foo ();
		Foo f2 = new Foo ();
		f2. incrementA ();
		f2. incrementB ();
		System .out . println (f1 );
		System .out . println (f2 );
	}
}
