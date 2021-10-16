package riemann;

public class Quadratic {
	double a;
	double b;
	double c;


	public boolean realRoots() {
		double disc = (b*b) - (4*a*c);
		if (disc >= 0) {
			return true;
		}
		return false;
	}


	public int numberOfRoots() {
		double disc = (b*b) - (4*a*c);
		if (a*c != 0) {
			if (disc == 0) {
				return 1;
			}
			else if (disc > 0) {
				return 2;
			}
			else return 0;
		}
		else {
			return 1;
		}
	}


	public double[] zeros() {
		double disc = (b*b) - (4*a*c);
		double root1;
		double root2;
		double[] roots = new double[2];
		if (a == 0) {
			root1 = -c/b;
			roots = new double[1];
			roots[0] = root1;
		}
		else {
			if (disc == 0) {
				root1 = (-b - Math.sqrt(b*b - 4*a*c)) / (2*a);
				roots = new double[1];
				roots[0] = root1;
			}
			else if (disc > 0) {
				root1 = (-b - Math.sqrt(b*b - 4*a*c)) / (2*a);
				root2 = (-b + Math.sqrt(b*b - 4*a*c)) / (2*a);
				roots[0] = root1;
				roots[1] = root2;
			}
			else {
				roots = new double[0];
			}
		}
		return roots;
	}


	public double axisOfSymmetry() {
		double xVert = -b/(2*a);
		return xVert;
	}


	public double extremeValue() {
		double xVert = -b/(2*a);
		double yVert = fof(xVert);
		return yVert;
	}


	public String maxOrMin() {
		if (a > 0) {
			return "minimum";
		}
		else if (a < 0) {
			return "maximum";
		}
		else return "";
	}


	public double fof(double x) {
		double y;
		y = (a*x*x) + (b*x) + c;
		return y;
	}
}
