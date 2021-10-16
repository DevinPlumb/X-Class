package matrix;

import java.util.Scanner;

import org.opensourcephysics.frames.PlotFrame;

import polyfun.Polynomial;
import riemann.PolyPractice;

/**
 * Takes a series of points and graphs the unique function - of a degree equal to 1 less than the number of points - that passes through them
 * @author Devin Plumb
 *
 */

public class Interpolation {

	Scanner input = new Scanner(System.in);

	/**
	 * Makes Interpolation into an object
	 */
	
	public void Interpolation() {
	}
	
	/**
	 * Completes interpolation using matrices
	 * @param numberOfPoints	the number of points to interpolate, which corresponds to the degree of the function (numberOfPoints-1)
	 */

	public void interpolate (int numberOfPoints) { // allows user to decide the number of data points and therefore the degree of the function

		System.out.println("Please enter " + numberOfPoints + " points in the form: 'x1, y1'. Hit enter after each point."); // requests user input on number of points

		PlotFrame plot1 = new PlotFrame (null, null, null); // new plot

		double[] xPoints = new double[numberOfPoints]; // x values
		Matrix yPoints = new Matrix(numberOfPoints, 1); // y values
		for (int i=0; i<numberOfPoints; i++) {
			String[] coordinates = input.nextLine().split(", "); // splits x and y as according to set up
			xPoints[i] = Double.parseDouble(coordinates[0]); // x values in double form
			yPoints.matrix[i][0] = Double.parseDouble(coordinates[1]); // y values in matrix form (used for multiplication)
			plot1.append(1, xPoints[i], yPoints.matrix[i][0]); // graphs original series of points
		}

		plot1.setVisible(true);
		plot1.setPreferredMinMax(-10, 10, -10, 10);

		Matrix powers = new Matrix(numberOfPoints, numberOfPoints); // square matrix of x^n
		for (int i=0; i<numberOfPoints; i++) {
			for (int j=0; j<numberOfPoints; j++) {
				powers.matrix[i][j] = Math.pow(xPoints[i], numberOfPoints-1-j); // square and symmetrical matrix ready to be multiplied, and inverted
			}
		}

		double[] coefficients = new double[numberOfPoints]; // final answer, product of inverse of powers and y values

		for (int i = 0; i < numberOfPoints; i++) {
			coefficients[i] = powers.invert().times(yPoints).matrix[numberOfPoints-1-i][0]; // only true because Axi^n + ... + K = yi
		}
		for (int i=0; i<numberOfPoints; i++) {
			if (i>0) {
				System.out.print(Math.abs(coefficients[numberOfPoints-1-i])); // if not first value, +- will determine sign
			}
			else System.out.print(coefficients[numberOfPoints-1-i]); // if first value, print out what it really is
			System.out.print("x^");
			System.out.print(numberOfPoints-1-i); // power of x
			if (i<numberOfPoints-1) {
				if (coefficients[numberOfPoints-2-i] >= 0) { // if positive
					System.out.print(" + ");
				}
				else if (coefficients[numberOfPoints-2-i] < 0) { // if negative
					System.out.print(" - ");
				}
			}
			else System.out.println(); // if end of polynomial, return
		}

		Polynomial f = new Polynomial (coefficients); // new polynomial based of coefficients double

		double leftBound = -10; // domain left bound
		double rightBound = 10; // domain right bound
		double precision = .01; // acuracy

		PlotFrame plot2 = new PlotFrame (null, null, null);

		for (double x = leftBound; x < rightBound; x=x+precision) {
			plot2.append(1, x, PolyPractice.eval(f, x)); // adds value of calculated matrix (based off of data points, which were previously graphed
		}

		plot2.setVisible(true);
		plot2.setPreferredMinMax(-10, 10, -10, 10);

	}

}
