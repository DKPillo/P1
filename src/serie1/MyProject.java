package serie1;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;

public class MyProject {

	static P1_1 p1 = new P1_1();
	public static String whatToDo = "w";
    
	public static void main(String args []) throws IOException {
		while (whatToDo.equals("e") != true) 
        {
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("Hallo Welt");
            System.out.println("W�hlen Sie eine der folgenden Funktionen:");
            System.out.println("h: Hallo Welt!");
            System.out.println("q: Quotient");
            System.out.println("o: Output testen");
            System.out.println("f: Fehlerhafter Code");
            System.out.println("e: exit");
            System.out.println("------------------------------------");
            System.out.println("");

            //BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
            Scanner scan = new Scanner(System.in);
            
            whatToDo = scan.nextLine().substring(0, 1);
            
            System.out.println("");
            System.out.println("");
            
            switch(whatToDo.charAt(0))
            { 
                case 'h':
                    p1.halloWorld();
                    break;
                case 'q':
                    p1.adNumbers();
                    break;
                case 'o':
                    p1.testOut();
                    break;
                case 'f':
                    p1.fehler();
                    break;
                case 'e':
                    System.exit(0);
                    break;
                default:
                    System.out.println("Entscheide dich");
                    break;
            }

        }
	}
	
	
}
