package riemann;
import polyfun.Polynomial;

public class AddXsquaredTest {

        public static void main(String[] args) 
        {
                PolyPractice popeye = new PolyPractice(); 
                Polynomial poly = new Polynomial(new double[] {-6,-1, 4, 5, 10, 11, 2, 4, 3});
                popeye.addXSquared(poly); //popeye adds x^2 to poly, and then both prints and graphs the resulting polynomial
        }
}