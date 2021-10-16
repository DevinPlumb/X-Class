package matrix;

import org.opensourcephysics.frames.PlotFrame;

import riemann.PolyPractice;
/**
 * VDM takes in a function f(x) and determines the slope of f(a), assuming f(x) equals some polynomial of x. The class also has a method that
 * graphs the slope value at every point as compared to the function value at that point.
 * @author Devin Plumb
 */

public class VDM {

	/**
	 * Makes VDM into an object
	 */
	
	public void VDM() { // used to create a VDM object
	}
	
	/**
	 * Finds the slope of a given polynomial at a given x value
	 * @param poly											the given polynomial
	 * @param atX											the given x value
	 * @return Q_coefficient.matrix[poly.getDegree()-1][0]	the slope at that point (m, the second to last entry in the coefficients matrix)
	 */
	
	public double slopeAtPoint (polyfun.Polynomial poly, double atX) {
		Matrix x_minus_a_squared_coefficient = new Matrix(poly.getDegree()+1, poly.getDegree()+1); // symmetrical matrix, multiplies by Q coefficients to equal V coefficients
		Matrix V_coefficient = new Matrix(poly.getDegree()+1, 1); // actual poly function's coefficients

		for (int i=0; i<poly.getDegree()+1; i++) { // used for x_minus_a_squared
			for (int j=0; j<poly.getDegree()+1; j++) { // used for x_minus_a_squared
				if (j==i) { // determined using examples
					x_minus_a_squared_coefficient.matrix[i][j] = 1;
				}
				else if (j==i-1 && i!=poly.getDegree()) { // determined using examples
					x_minus_a_squared_coefficient.matrix[i][j] = -2*atX;
				}
				else if (j==i-2) { // determined using examples
					x_minus_a_squared_coefficient.matrix[i][j] = atX*atX;
				}
				else x_minus_a_squared_coefficient.matrix[i][j] = 0; // fills in rest of matrix
			}
		}
		
		for (int i=poly.getDegree(); i>=0; i--) {
			V_coefficient.matrix[poly.getDegree()-i][0] = poly.getCoefficient(i).getTerms()[0].getTermDouble(); // turns V_coefficient double[] into backwards of polynomial coefficients
		}

		Matrix Q_coefficient = new Matrix(poly.getDegree()+1, 1); // prepped to be product of multiplication

		for (int i = 0; i < poly.getDegree()+1; i++) {
			Q_coefficient.matrix[i][0] = x_minus_a_squared_coefficient.invert().times(V_coefficient).matrix[i][0]; // inverse times product equals original times original
		}
		
		return Q_coefficient.matrix[poly.getDegree()-1][0]; // returns second to last entry, which is linear slope of function
	}
	
	/**
	 * This function graphs the tangent line for a particular point on a particular polynomial function
	 * @param poly	the given polynomial
	 * @param atX	the point to find the tangent line
	 */
	
	public void tangentLine (polyfun.Polynomial poly, double atX) {
		double leftBound = -10; // limits domain
		double rightBound = 10; // limits domain
		double precision = .01; // high accuracy
		
		PlotFrame plot = new PlotFrame (null, null, null); // plots
		
		for (double x = leftBound; x < rightBound; x=x+precision) { // keeps within domain, accuracy on point
			plot.append(1, x, PolyPractice.eval(poly, x)); // original polynomial graph
			plot.append(2, x, slopeAtPoint(poly, atX)*(x-atX) + PolyPractice.eval(poly, atX)); // tangent line
		}
		
		plot.setVisible(true);
		plot.setPreferredMinMax(-10, 10, -10, 10);
	}
	
	/**
	 * Graphs the function of a given polynomial at a range of x values (ideally all x values, but here limited to -5 through 5)
	 * @param poly	the given polynomial
	 */

	public void slopeFunction (polyfun.Polynomial poly) { // completes above process for all points and graphs
		double leftBound = -10; // limits domain
		double rightBound = 10; // limits domain
		double precision = .01; // high accuracy
		
		PlotFrame plot = new PlotFrame (null, null, null); // plots
		
		for (double x = leftBound; x < rightBound; x=x+precision) { // keeps within domain, accuracy on point
			plot.append(1, x, slopeAtPoint(poly, x)); // slope function is first graph
			plot.append(2, x, PolyPractice.eval(poly, x)); // original polynomial graph
		}
		
		plot.setVisible(true);
		plot.setPreferredMinMax(-10, 10, -10, 10);
	}

}
