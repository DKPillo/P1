package serie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1_1 {
	
	public P1_1() {
		
	}
	
	public static double a = 17;
	
	public void halloWorld() {
        System.out.println("Hallo Welt!");
    }
    
    public void adNumbers() throws IOException {
        BufferedReader input2=new BufferedReader(new InputStreamReader(System.in));
        
        String var1s = "";
        String var2s = "";
        double var1 = 1;
        double var2 = 1;
        
        System.out.println("Quotient ((a)^2)/b");
        System.out.println("Wählen Sie a:");
        var1s = input2.readLine();
        System.out.println("Wählen Sie b:");
        var2s = input2.readLine();
        
        try {
        var1 = Double.parseDouble(var1s);
        var2 = Double.parseDouble(var2s);
        } catch (NumberFormatException e) {
            System.out.println("Geben Sie eine ganze Zahl an!");
            return;
        }
        
        if (var2 == 0) {
            System.out.println("Sie können nicht durch null dividieren!");
            return;
        }
        
        System.out.println("");
        System.out.println("Quotient wird errechnet...");
        System.out.println("");
        
        double qd = Math.pow(var1,2)/var2;
        
        System.out.println("Resultat als Double:");
        System.out.print(qd);
        System.out.println("");
        
        System.out.println("Resultat als Integer mit rest:");
        double rest = 0;
        rest = Math.pow(var1,2)% var2;
        double toDivide = Math.pow(var1,2) - rest;
        double qi = toDivide/var2;
        
        int qii = new Double(qi).intValue();
        int resti = new Double(rest).intValue();
        
        String divOut = qii + " Rest " + resti;
        
        System.out.println(divOut);
        
    }
    
    public void testOut() {
        System.out.println ("1 + 2");
        System.out.println (1 + 2);
        System.out.println ("1 + 2 = " + 2 + 3);
    }
    
    public void fehler() {
        double b = 24;
        double c = 3.41;
        System.out.println ("a = " + a);
        a = a + b;
        System.out.println ("a = " + a);
        b = c/2;
        System.out.println ("b = " + b);
    }

}
