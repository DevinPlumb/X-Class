package riemann;

import java.util.Random;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

/**
 * RandomRule
 * This program does the Random Rule
 * @author Devin Plumb
 *
 */

public class RandomRule extends Riemann { // slice and slicePlot abstract in Riemann
	Random gen = new Random();
	
	/**
	 * slice(polyfun.Polynomial poly, double sleft, double sright)
	 * This method calculates the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the x-axis. If RanR is of type
	 * RandomRule, then RanR.slice should calculate this area using the y value of a random x value between sleft and sright as the height of the rectangle.
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 * @return Returns the value of the area of the subinterval, as base*height of the rectangle
	 */
	
	public double slice(polyfun.Polynomial poly, double sleft, double sright) {
		double width = sright-sleft; // width is distance between extreme x values
		int intPoint = gen.nextInt(1001); // random number between 0-1000
		double point = intPoint*width/1000; // value of how far in between sleft and sright the x value is
		double height = (PolyPractice.eval(poly, sleft+point)); // gets height of random point
		double area = width*height; // formula for area of rectangle

		return area;
	}
	
	/**
	 * slicePlot (org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	 * This method draws the region whose (signed) area is calculated by slice. If RanR is of type RandomRule, then RanR.slicePlot should plot this area
	 * using the y value of a random x value between sleft and sright as the height of the rectangle.
	 * @param pframe	the PlotFrame on which the slicePlot is to be drawn
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param sleft		the left hand endpoint of the interval
	 * @param sright	the right hand endpoint of the interval
	 */

	public void slicePlot(PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright) {
		double width = sright-sleft; // width is distance between extreme x values
		int intPoint = gen.nextInt(1001); // random number between 0-1000
		double point = intPoint*width/1000; // value of how far in between sleft and sright the x value is
		double height = (PolyPractice.eval(poly, sleft+point)); // gets height of random point
		DrawableShape myrect = DrawableShape.createRectangle(sleft+(width/2), height/2, Math.abs(width), Math.abs(height)); // center x value, center y value, total width, total height
//        myrect.setMarkerColor(Color.blue, Color.black); // interior blue, black border
		pframe.addDrawable(myrect); // draws it

	}
}