package orbital;

public class OrbitalMatrix {

	double[][] matrix = new double[3][3];

	public OrbitalMatrix(double rotation, double tx, double ty)	{
		
		matrix[0][0] = Math.cos(rotation);
		matrix[0][1] = -Math.sin(rotation);
		matrix[0][2] = tx;
		matrix[1][0] = Math.sin(rotation);
		matrix[1][1] = Math.cos(rotation);
		matrix[1][2] = ty;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 1;	
		
	}
	
	public double[] vectorRotation(double x, double y)
	{
		double[] components = new double[2];
		components[0] = matrix[0][0]*x + matrix[0][1]*y + matrix[0][2];
		components[1] = matrix[1][0]*x + matrix[1][1]*y + matrix[1][2];
		
		return components;
	}
}
