package riemann;
import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

public class RSAccTest 
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


		Polynomial p = new Polynomial(new double[] {3,6,-2,7,-2}); // p=-2x^4 + 7x^3 - 2x^2 + 6x +3

		PlotFrame f = new PlotFrame("x","y","Left Hand Rule Accumulation Function Graph");
		f.setLocation(0,0); 
		f.setPreferredMinMaxX(-100, 100);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);

		PlotFrame g = new PlotFrame("x","y","Right Hand Rule Accumulation Function Graph");
		g.setLocation(300,0); 
		g.setPreferredMinMaxX(-100, 100);
		g.setDefaultCloseOperation(3);
		g.setVisible(true);

		PlotFrame h = new PlotFrame("x","y","Trapezoid Rule Accumulation Function Graph");
		h.setLocation(600,0); 
		h.setPreferredMinMaxX(-100, 100);
		h.setDefaultCloseOperation(3);
		h.setVisible(true);

		PlotFrame i = new PlotFrame("x","y","Midpoint Rule Accumulation Function Graph");
		i.setLocation(900,0); 
		i.setPreferredMinMaxX(-100, 100);
		i.setDefaultCloseOperation(3);
		i.setVisible(true);

		PlotFrame j = new PlotFrame("x","y","Minimum Rule Accumulation Function Graph");
		j.setLocation(0,400); 
		j.setPreferredMinMaxX(-100, 100);
		j.setDefaultCloseOperation(3);
		j.setVisible(true);

		PlotFrame k = new PlotFrame("x","y","Maximum Rule Accumulation Function Graph");
		k.setLocation(300, 900);
		k.setPreferredMinMaxX(-100, 100);
		k.setDefaultCloseOperation(3);
		k.setVisible(true);

		PlotFrame l = new PlotFrame("x","y","Random Rule Accumulation Function Graph");
		l.setLocation(600,900); 
		l.setPreferredMinMaxX(-100, 100);
		l.setDefaultCloseOperation(3);
		l.setVisible(true);

		PlotFrame m = new PlotFrame("x","y","Simpson's Rule Accumulation Function Graph");
		m.setLocation(900,400);
		m.setPreferredMinMaxX(-100, 100);
		m.setDefaultCloseOperation(3);
		m.setVisible(true);

		LHR.rsAcc(f, p, 2, .1, -1.0); //plots the left hand rule accumulation function of x^2+x, with base x=-1;

		RHR.rsAcc(g, p, 2, .1, -1.0); //plots the right hand rule accumulation function of x^2+x, with base x=-1;

		TR.rsAcc(h, p, 2, .1, -1.0); //plots the trapezoid rule accumulation function of x^2+x, with base x=-1;

		MPR.rsAcc(i, p, 2, .1, -1.0); //plots the midpoint rule accumulation function of x^2+x, with base x=-1;

		MinR.rsAcc(j, p, 2, .1, -1.0); //plots the minimum rule accumulation function of x^2+x, with base x=-1;

		MaxR.rsAcc(k, p, 2, .1, -1.0); //plots the maximum rule accumulation function of x^2+x, with base x=-1;

		RanR.rsAcc(l, p, 2, .1, -1.0); //plots the random rule accumulation function of x^2+x, with base x=-1;

		SimR.rsAcc(m, p, 2, .1, -1.0); //plots the simpson's rule accumulation function of x^2+x, with base x=-1;
	}
}       