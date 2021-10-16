package riemann;
import java.awt.geom.GeneralPath;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;

/**
 * TrapezoidRule
 * This program does the Trapezoid Rule
 * @author Devin Plumb
 *
 */

public class TrapezoidRule extends Riemann { // slice and slicePlot abstract in Riemann
	
	/**
	 * slice(polyfun.Polynomial poly, double sleft, double sright)
	 * This method calculates the (signed) area between the graph of a polynomial and the x-axis, over a given interval on the x-axis. If TR is of type
	 * TrapezoidRule, then TR.slice should calculate this area using the y value of the left and right ends of the slice as the two heights of the trapezoid.
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be calculated
	 * @param sleft		the left hand endpoint of the interval, the y value of which will be used for the height1 of the trapezoid
	 * @param sright	the right hand endpoint of the interval, the y value of which will be used for the height2 of the trapezoid
	 * @return Returns the value of the area of the subinterval, as base*(height1-height2)/2 of the trapezoid
	 */
	
	public double slice(polyfun.Polynomial poly, double sleft, double sright) {
		double height1 = PolyPractice.eval(poly, sleft); // f(sleft) = height1 of the trapezoid
		double height2 = PolyPractice.eval(poly, sright); // f(sright) = height2 of the trapezoid
		double width = sright-sleft; // width is distance between extreme x values
		double area = width*(height1+height2)/2; // base*(height1-height2)/2 of the trapezoid is the area formula

		return area;
	}
	
	/**
	 * slicePlot (org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	 * This method draws the region whose (signed) area is calculated by slice. If TR is of type TrapezoidRule, then TR.slicePlot should plot this area
	 * using the y value of the left and right ends of the slice as the two heights of the trapezoid.
	 * @param pframe	the PlotFrame on which the slicePlot is to be drawn
	 * @param poly		the polynomial whose area (over or under the x-axis), over the interval from sleft to sright, is to be drawn
	 * @param sleft		the left hand endpoint of the interval, the y value of which will be used for the height1 of the trapezoid
	 * @param sright	the right hand endpoint of the interval, the y value of which will be used for the height2 of the trapezoid
	 */

	public void slicePlot(PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright) {
		double height1 = PolyPractice.eval(poly, sleft);
		double height2 = PolyPractice.eval(poly, sright);
		
		double x1Points[] = {sleft, sleft, sright, sright}; // four vertexes x values
		double y1Points[] = {0, height1, height2, 0}; // four vertexes y values
		GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length); // goes to all four vertexes
		polygon.moveTo(x1Points[0], y1Points[0]); // starts at bottom left corner

		for (int index = 1; index < x1Points.length; index++) { // goes through the four vertexes
			polygon.lineTo(x1Points[index], y1Points[index]); // moves from one to the next
		};
		polygon.closePath(); // close when back to bottom left corner
		DrawableShape trapezoid = new DrawableShape(polygon, 0, 0); // turns into DrawableShape
//		trapezoid.setMarkerColor(Color.blue, Color.black); // interior blue, black border
		pframe.addDrawable(trapezoid); // draw it


	}
}
