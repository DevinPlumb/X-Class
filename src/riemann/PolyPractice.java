package riemann;
import polyfun.*;
import org.opensourcephysics.frames.PlotFrame;
public class PolyPractice {
	
	public static double eval(Polynomial p, double x) {
		return p.evaluate(x).getTerms()[0].getTermDouble();
	}
	
	public static void addXSquared(Polynomial p) {
		double x=-50;
		Polynomial xSquared = new Polynomial(1.0,2);
		Polynomial sum = p.plus(xSquared);

		sum.print();
		PlotFrame pFrame = new PlotFrame("x","y","HW");
		pFrame.setVisible(true);
		pFrame.setPreferredMinMax(-10,10,-10,10);
		
		while (x>=-50 && x<=50) {
			pFrame.append(1, x, eval(sum, x));
			
			x=x+.005;
		}
	}
}