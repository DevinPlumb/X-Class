package matrix;

import java.util.Scanner;

public class InterpolationTest 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);

		Interpolation interpolation = new Interpolation();

		System.out.println("Enter a number of points to interpolate (int).");

		int points = input.nextInt();

		interpolation.interpolate(points);
	}
}
