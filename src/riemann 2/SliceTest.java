package riemann;

import polyfun.Polynomial;

public class SliceTest 
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

		Polynomial p = new Polynomial(new double[] {0,4,0,3}); // p=3x^3+4x

		System.out.println(LHR.slice(p,1,1.1)+"\n"); //the area of a left hand rule slice of 3x^3+4x, from x=1 to x=1.1

		System.out.println(RHR.slice(p,1,1.1)+"\n"); //the area of a right hand rule slice of 3x^3+4x, from x=1 to x=1.1

		System.out.println(TR.slice(p,1,1.1)+"\n"); //the area of a trapezoid rule slice of 3x^3+4x, from x=1 to x=1.1
		
		System.out.println(MPR.slice(p,1,1.1)+"\n"); //the area of a midpoint rule slice of 3x^3+4x, from x=1 to x=1.1

		System.out.println(MinR.slice(p,1,1.1)+"\n"); //the area of a minimum rule slice of 3x^3+4x, from x=1 to x=1.1

		System.out.println(MaxR.slice(p,1,1.1)+"\n"); //the area of a maximum rule slice of 3x^3+4x, from x=1 to x=1.1
		
		System.out.println(RanR.slice(p,1,1.1)+"\n"); //the area of a random rule slice of 3x^3+4x, from x=1 to x=1.1

		System.out.println(SimR.slice(p,1,1.1)+"\n"); //the area of a simpson's rule slice of 3x^3+4x, from x=1 to x=1.1
	}
}