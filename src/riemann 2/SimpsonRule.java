package riemann;

import java.awt.geom.GeneralPath;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

/**
 * SimpsonRule
 * This program does Simpson's Rule
 * @author Devin Plumb
 *
 */

public class SimpsonRule extends Riemann { // slice and slicePlot abstract in Riemann

	/**
	 * slice(polyfun.Polynomial poly, double sleft, double sright)
	 * This method calculates the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the x-axis. If SimR is of type
	 * SimpsonRule, then SimR.slice should calculate this area using the quadratic approximation of the function given the three x values sleft, sright,
	 * and (sleft+sright)/2 and the three corresponding y values (f(x) at that value) as the upper bound of the slice.
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 * @return Returns the value of the area of the subinterval
	 */
	
	public double slice(polyfun.Polynomial poly, double sleft, double sright) {
		
		double x1 = sleft; // x1 is nicer name
		double x2 = (sleft+sright)/2; // x2 is cleaner
		double x3 = sright; // x3 is nicer name

		double y1 = PolyPractice.eval(poly, x1); // y1 is cleaner
		double y2 = PolyPractice.eval(poly, x2); // y2 is cleaner
		double y3 = PolyPractice.eval(poly, x3); // y3 is cleaner

		double denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
		double a = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom; // first coefficient
		double b = (x3 * x3 * (y1 - y2) + x2 * x2 * (y3 - y1) + x1 * x1 * (y2 - y3)) / denom; // second coefficient
		double c = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom; // third coefficient
		// all this is the result of Matrix for three equations of y = ax^2 + bx + c for three different x and y values
		
		double area = ((a*x3*x3*x3/3) + (b*x3*x3/2) + (c*x3)) - ((a*x1*x1*x1/3) + (b*x1*x1/2) + (c*x1)); // area of slice takes total area from 0->x3 then subtracts from 0->x1, result is x1->x3

		return area;
	}
	
	/**
	 * slicePlot (org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	 * This method draws the region whose (signed) area is calculated by slice. If SimR is of type SimpsonRule, then SimR.slicePlot should plot this area
	 * using the quadratic approximation of the function given the three x values sleft, sright, and (sleft+sright)/2 and the three corresponding y values
	 * (f(x) at that value) as the upper bound of the slice.
	 * @param pframe	the PlotFrame on which the slicePlot is to be drawn
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 */

	public void slicePlot(PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright) {

		double x1 = sleft; // x1 is nicer name
		double x2 = (sleft+sright)/2; // x2 is cleaner
		double x3 = sright; // x3 is nicer name

		double y1 = PolyPractice.eval(poly, x1); // y1 is cleaner
		double y2 = PolyPractice.eval(poly, x2); // y2 is cleaner
		double y3 = PolyPractice.eval(poly, x3); // y3 is cleaner

		double denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
		double a = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom; // first coefficient
		double b = (x3 * x3 * (y1 - y2) + x2 * x2 * (y3 - y1) + x1 * x1 * (y2 - y3)) / denom; // second coefficient
		double c = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom; // third coefficient
		// all this is the result of Matrix for three equations of y = ax^2 + bx + c for three different x and y values
		
		double x1Points[] = {x3, x3, x1, x1}; // four vertexes (3 straight lines connect them and one curved line) x values
		double y1Points[] = {y3, 0, 0, y1}; // four vertexes (3 straight lines connect them and one curved line) y values
		GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
		polygon.moveTo(x1Points[0], y1Points[0]); // start at upper right corner

		for (int index = 1; index < x1Points.length; index++) { // go around three straight edges
			polygon.lineTo(x1Points[index], y1Points[index]); // go from one vertex to next
		}
		
		for (double x = x1; x <= x3; x+=.01) { // move along x values of curved line
			polygon.lineTo(x, (a*x*x)+(b*x)+c); // tiny lines along .01 from x,f(x) to x+.01,f(x+.01)
		}
		
		polygon.closePath(); // end curve
		DrawableShape quadSlice = new DrawableShape(polygon, 0, 0); // convert to DrawableShape
//		quadSlice.setMarkerColor(Color.blue, Color.black); // blue interior, black border
		pframe.addDrawable(quadSlice); // draw it


	}
}
