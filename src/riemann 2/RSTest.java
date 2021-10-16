package riemann;
import polyfun.Polynomial;

public class RSTest 
{
	public static void main(String[] args) 
	{
		LeftHandRule LHR = new LeftHandRule();  //LeftHandRule extends Riemann
		RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
		TrapezoidRule TR= new TrapezoidRule(); //TrapezoidRule extends Riemann
		MidpointRule MPR = new MidpointRule();  //MidpointRule extends Riemann
		MinimumRule MinR = new MinimumRule();  //MinimumRule extends Riemann
		MaximumRule MaxR = new MaximumRule();  //MidpointRule extends Riemann
		RandomRule RanR = new RandomRule();  //RandomRule extends Riemann
		SimpsonRule SimR = new SimpsonRule();  //SimpsonRule extends Riemann

		Polynomial p = new Polynomial(new double[] {0,0,3}); //p=3x^2

		System.out.println(LHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the left hand rule

		System.out.println(RHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the right hand rule

		System.out.println(TR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the trapezoid rule
		
		System.out.println(MPR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the midpoint rule

		System.out.println(MinR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the minimum rule

		System.out.println(MaxR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the maximum rule
		
		System.out.println(RanR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the random rule

		System.out.println(SimR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using simpson's rule
	}
}