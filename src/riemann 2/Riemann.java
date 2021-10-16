package riemann;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Random;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

/**
 * Riemann
 * This is the parent class of each rule, which calculates the area between the graph of a polynomial and the
 * x-axis, draws that area, and plots the graph of the total area at each x value (accumulation function).
 * Bonuses:
 * 1. Midpoint Rule child class
 * 2. Minimum Rule child class
 * 3. Maximum Rule child class
 * 4. Random Rule child class
 * 5. Simpson's Rule child class
 * 6. Calculates and graphs accumulation function using variable number of subintervals, so that the accuracy is
 * consistent no matter what the distance from the base is.
 * @author Devin Plumb
 * 
 */

public abstract class Riemann { // extended by all rule classes for slice and slicePlot
	
	/**
	 * slice(polyfun.Polynomial poly, double sleft, double sright)
	 * This method calculates the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the x-axis. If RiemannSumRule
	 * extends Riemann and RSR is an object of type RiemannSumRule, then RSR.slice should calculate this area using the particular Riemann sum rule
	 * implemented in RiemannSumRule.
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 * @return Returns the value of the area of the subinterval, calculated according to a particular rule which is determined by the child class that extends this
	 */
	
	public abstract double slice(polyfun.Polynomial poly, double sleft, double sright); // calculates the area of one particular subinterval bounded by the curve

	/**
	 * slicePlot (org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	 * This method draws the region whose (signed) area is calculated by slice. If RiemannSumRule extends Riemann and RSR is an object of type
	 * RiemannSumRule, then RSR.slicePlot should draw this region using the particular Riemann sum rule implemented in RiemannSumRule.
	 * @param pframe	the PlotFrame on which the slicePlot is to be drawn
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 */
	
	public abstract void slicePlot(PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright); // plots one particular subinterval bounded by the curve
	
	/**
	 * rs (polyfun.Polynomial poly, double left, double right, int subintervals)
	 * This method calculates a Riemann sum. If RiemannSumRule extends Riemann and RSR is an object of type RiemannSumRule, then RSR.rs should calculate a
	 * Riemann sum using the particular Riemann sum rule implemented in RiemannSumRule.
	 * @param poly				the polynomial whose Riemann sum is to be calculated
	 * @param left				the left hand endpoint of the Riemann sum
	 * @param right				the right hand endpoint of the Riemann sum
	 * @param subintervals		the number of subintervals in the Riemann sum
	 * @return Returns the value of the Riemann sum, calculated according to a particular rule which is determined by the child class that extends this
	 * method
	 */
	
	public double rs(polyfun.Polynomial poly, double left, double right, int subintervals) {
		double range = right-left; // total distance between left and right bound is total x domain covered
		double delta = range/subintervals; // width of each subinterval equal to the total divided by the number of subintervals
		double sum=0; // at start of x values, sum is 0. sum grows as it runs through x values
		for (int i=0; i<subintervals; i++) { // each time through for is an individual slice
			sum+=slice(poly, left+(i*delta), left+((i+1)*delta)); // adds the value of one slice to total
		}
		return sum; // returns total area
	}
	
	/**
	 * rsPlot (PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double left, double right, int subintervals)
	 * This method graphs the input polynomial on the input PlotFrame. It also draws the regions used to calculate a particular Riemann sum. If
	 * RiemannSumRule extends Riemann and RSR is an object of type RiemannSumRule, then RSR.rsPlot should graph the input polynomial and draw the regions
	 * used to calculate the Riemann sum using the rule implemented in RiemannSumRule.
	 * @param pframe			the PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly				the polynomial whose Riemann sum is to be drawn
	 * @param index				the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the polynomial
	 * @param precision			the difference between the x-coordinates of two adjacent points on the graph of the polynomial
	 * @param left				the left hand endpoint of the Riemann sum
	 * @param right				the right hand endpoint of the Riemann sum
	 * @param subintervals		the number of subintervals in the Riemann sum
	 */

	public void rsPlot(PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double left, double right, int subintervals) {
		pframe.setVisible(true); // makes plot visible
		pframe.setPreferredMinMax(-5,5,-5,5); // a range and domain easy to see start of function AND individual subintervals

		double x=left-10; // draw function slightly outside of where riemann will be calculated, for the viewer's sake
		Trail function = new Trail(); // function the name of the plot of the function this graphs

		while (x>=left-10 && x<=right+10) { // draw function slightly outside of where riemann will be calculated, for the viewer's sake
			function.addPoint(x, PolyPractice.eval(poly, x)); // evaluates y value at a particular x value and plots x,y
			x=x+precision; // jumps from point to point
		}
		x=left; // does not draw riemann slightly outside of given domain, unlike function; left is starting x value
		function.color = (generateRandomColor(Color.GREEN)); // generates a random shade of green
		function.setStroke(new BasicStroke(2)); // thickens line
		pframe.addDrawable(function); // draw function
		while (x>=left && x<=right) { // domain of riemann sum
			double range = right-left; // needed to calculate delta
			double delta = range/subintervals; // calculates delta

			slicePlot(pframe, poly, x, x+delta); // calls slicePlot, which is defined individually elsewhere
			x=x+delta; // moves to next subinterval
		}
	}
	
	/**
	 * rsAcc(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double base)
	 * This method graphs the accumulation function corresponding to the input polynomial and the input left hand endpoint. If RiemannSumRule extends
	 * Riemann and RSR is an object of type RiemannSumRule, then RSR.rsAcc should graph the accumulation function corresponding to the input polynomial and
	 * the input left hand endpoint using the particular Riemann sum rule implemented in RiemannSumRule.
	 * @param pframe		the PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly			the polynomial whose accumulation function is to be calculated
	 * @param index			the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the accumulation function
	 * @param precision		the difference between the x-coordinates of two adjacent points on the graph of the accumulation function
	 * @param base			the left hand endpoint of the accumulation function
	 */
	
	public void rsAcc(PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double base) {
		pframe.setVisible(true); // makes plot visible
		pframe.setPreferredMinMax(-100,100,-100,100); // nice range of x and y values
		
		double x=base-10; // draws from base-10 to base+10
		Trail function = new Trail(); // function the name of the plot of the function this graphs
		function.color = (generateRandomColor(Color.BLUE)); // generates a random shade of blue
		function.setStroke(new BasicStroke(2)); // thickens line
		while (x>=base-10 && x<=base+10) { // draws accumulation function
			function.addPoint(x, rs(poly, base, x, 10*((int) Math.round(Math.abs(base-x)/precision)))); // evaluates riemann sum from base to current x and makes that y value, keeping delta constant
			x=x+precision; // jumps to next point
		}
		pframe.addDrawable(function);  // draw function
		
		
	}

	/**
	 * generateRandomColor (Color mix)
	 * Borrowed from: http://stackoverflow.com/questions/43044/algorithm-to-randomly-generate-an-aesthetically-pleasing-color-palette
	 * Function takes in a pure, basic color and produces a random shade of that color.
	 * @param mix	the basic color from which all shades will be derived
	 * @return		the mixed color that is a shade of the original, mixed with other things
	 */
	
	public Color generateRandomColor(Color mix) {
	    Random random = new Random();
	    int red = random.nextInt(256); // random shade of red
	    int green = random.nextInt(256); // random shade of green
	    int blue = random.nextInt(256); // random shade of blue

	    // mix the color
	    if (mix != null) {
	        red = (red + mix.getRed()) / 2; // adds random shade to original
	        green = (green + mix.getGreen()) / 2; // adds random shade to original
	        blue = (blue + mix.getBlue()) / 2; // adds random shade to original
	    }

	    Color color = new Color(red, green, blue); // combines new shades
	    return color;
	}
}
