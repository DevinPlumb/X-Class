package riemann;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

/**
 * MaximumRule
 * This program does the Maximum Rule
 * @author Devin Plumb
 *
 */

public class MaximumRule extends Riemann { // slice and slicePlot abstract in Riemann
	
	/**
	 * slice(polyfun.Polynomial poly, double sleft, double sright)
	 * This method calculates the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the x-axis. If MaxR is of type
	 * MaximumRule, then MaxR.slice should calculate this area using the y value of either the left or the right end of the slice (whichever y value is higher)
	 * as the height of the rectangle.
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 * @return Returns the value of the area of the subinterval, as base*height of the rectangle
	 */
	
	public double slice(polyfun.Polynomial poly, double sleft, double sright) {
		double height;
		if (PolyPractice.eval(poly, sleft)>PolyPractice.eval(poly, sright)) { // determines which y value is greater (not abs value, just more positive)
			height = PolyPractice.eval(poly, sleft);
		}
		else height = PolyPractice.eval(poly, sright);
		double width = sright-sleft; // width is distance between extreme x values
		double area = width*height; // formula for area of rectangle

		return area;
	}
	
	/**
	 * slicePlot (org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	 * This method draws the region whose (signed) area is calculated by slice. If MaxR is of type MaximumRule, then MaxR.slicePlot should plot this area
	 * using the y value of either the left or the right end of the slice (whichever y value is higher) as the height of the rectangle.
	 * @param pframe	the PlotFrame on which the slicePlot is to be drawn
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 */
	
	public void slicePlot(PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright) {
		double height;
		if (PolyPractice.eval(poly, sleft)>PolyPractice.eval(poly, sright)) { // same as before
			height = PolyPractice.eval(poly, sleft);
		}
		else height = PolyPractice.eval(poly, sright); // same as before
		double width = sright-sleft;
		DrawableShape myrect = DrawableShape.createRectangle(sleft+(width/2), height/2, Math.abs(width), Math.abs(height)); // center x value, center y value, total width, total height
//		myrect.setMarkerColor(Color.blue, Color.black); // interior blue, black border
		pframe.addDrawable(myrect); // draws it

	}
}