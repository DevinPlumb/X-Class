package riemann;
import java.util.Scanner;
public class RustRemoval {
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please type in the values of the three coefficients in a quadratic of the form ax^2 + bx + c.");
		System.out.print("Value of a:");
		double a = input.nextDouble();
		System.out.print("Value of b:");
		double b = input.nextDouble();
		System.out.print("Value of c:");
		double c = input.nextDouble();

		Quadratic rustRemoval = new Quadratic();
		rustRemoval.a = a;
		rustRemoval.b = b;
		rustRemoval.c = c;
		
		double lineRoot = -c/b;
		if (c == 0) {
			lineRoot = 0;
		}

		if (a == 0) {
			if (b == 0) {
				if (c == 0) {
					System.out.println("This is not a quadratic. This is a line that is f(x) = 0, which means it intersects with the x-axis at all x values. No extreme values.");
				}
				else {
					System.out.println("This is not a quadratic. This is a line that is parallel with the x-axis and therefore has no roots. No extreme values.");
				}
			}
			else {
				System.out.println("This is not a quadratic. This is a line that intersects with the x-axis at x = " + rustRemoval.zeros()[0] + ". No extreme values.");
			}
		}
		else {
			if (rustRemoval.realRoots() == true) {
				if (rustRemoval.numberOfRoots() == 1) {
					System.out.println("The quadratic has 1 real root, and it is x = " + rustRemoval.zeros()[0] + ".");
				}
				else {
					System.out.println("The quadratic has 2 real roots. They are x = " + rustRemoval.zeros()[0] + " and x = " + rustRemoval.zeros()[1] + ".");
				}
			}
			else {
				System.out.println("The quadratic has no real roots.");
			}
			System.out.println("The extreme value of the function is a " + rustRemoval.maxOrMin() + " and is at f(" + rustRemoval.axisOfSymmetry() + ") = " + rustRemoval.extremeValue() + ".");
		}
		System.out.println("Input any real x value.");
		double x = input.nextDouble();
		System.out.println("f(" + x + ") = " + rustRemoval.fof(x));
	}
}
