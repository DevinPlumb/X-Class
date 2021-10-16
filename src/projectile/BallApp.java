package projectile;

import java.awt.Color;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

/**
 * BallApp
 * This class simulates multiple balls being hit by a baseball player at one end of a field. The length of the field, height of the wall,
 * force of gravity, force of air resistance, spin on the ball, coefficient of restitution, time step, range of angles, and range of speeds
 * are all variable.
 * Bonuses:
 * 1. Simulates ball bouncing against floor and wall
 * 2. Calculates minimum force for a ball bouncing over the wall, with 1 bounce and 2
 * 3. Determines the possibility of different balls going over, given their max heights
 * 4. Incorporates spin imparted on the ball to influence lift, and allows for both topspin and backspin
 * @author Devin Plumb
 *
 */

public class BallApp extends AbstractSimulation{
	
	double walldist; // distance from ball being hit to wall
	double wallheight; // height of the wall
	double gravity; // acceleration gravity causes all objects to have towards earth (with no friction)
	double alpha; // the amount of drag; air resistance
	double lift; // spin of the ball causes the ball to have acceleration perpendicular to the acceleration that drag causes
	double forceLoss; // % of force ball has on impact with solid surface retained after contact
	double t; // time step
	int angles; // number of angles used
	int speeds; // number of speeds used
	double minAngle; // minimum angle in range
	double maxAngle; // maximum angle in range
	double minSpeed; // minimum speed in range
	double maxSpeed; // maximum speed in range

	Trail[][] functions = new Trail[angles][speeds]; // double array of trails of balls, only appear after they make it over the wall
	Particle[][] balls = new Particle[angles][speeds]; // double array of particles of balls, used to control motion
	DisplayFrame frame = new DisplayFrame("X", "Y", "Ball Test"); // shows user motion of balls

	double minimumforce1=100000; // an unrealistically high minimum speed of the ball (home run), guarantees that value will be replaced
	double minimumforce2=100000; // an unrealistically high minimum speed of the ball (single bounce), guarantees that value will be replaced
	double minimumforce3=100000; // an unrealistically high minimum speed of the ball (double bounce), guarantees that value will be replaced
	double optAngle1; // value of angle corresponding to minimumforce1 (home run)
	double optAngle2; // value of angle corresponding to minimumforce2 (single bounce)
	double optAngle3; // value of angle corresponding to minimumforce3 (double bounce)
	double maxheight1=0; // calculates max height of balls before bounce, 0 is unrealistically low
	double maxheight2=0; // calculates max height of balls before second bounce, 0 is unrealistically low
	double maxheight3=0; // calculates max height of balls before third bounce, 0 is unrealistically low
	int angle1; // actual optimal value for home run
	int angle2; // actual optimal value for single bounce
	int angle3; // actual optimal value for double bounce
	int speed1; // actual minimum value for home run
	int speed2; // actual minimum value for single bounce
	int speed3; // actual minimal value for double bounce

	double time=0; // starting time
	double totalTime=1400; // time to cut off program
	
	/**
	 * doStep()
	 * 
	 * Runs one step for each ball given the time step. After a certain amount of total time, which is calculated, quits out of program.
	 */

	protected void doStep()
	{
		for (int r=0; r<speeds; r++) { // runs through all speeds
			for(int i=0; i<angles; i++) { // runs through all angles
				if (balls[i][r].getYpos() <= 0 && balls[i][r].getYvel() < 0) { // if the ball is currently hitting the ground
					balls[i][r].bounce++; // adds to the number of bounces that the ball has registered
					balls[i][r].Xvel = balls[i][r].Xvel*(forceLoss); // loses some force in x direction, based on coefficient of restitution
					balls[i][r].Yvel = -balls[i][r].Yvel*(forceLoss); // loses some force in y direction and reverses sign
				}
				if (balls[i][r].getXpos() >= walldist && balls[i][r].getYpos() >= wallheight) { // if the ball has gone over the wall
					if (balls[i][r].bounce == 2) { // if double bounce
						functions[i][r].color = Color.red;
						if (Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)) < minimumforce3) { // if lowest possible speed
							minimumforce3=Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)); // replaces
							optAngle3 = i*(maxAngle-minAngle)/(angles-1)+minAngle; // replaces
							speed3=r; // replaces
							angle3=i; // replaces
						}
					}
					else if (balls[i][r].bounce == 1) { // single bounce
						functions[i][r].color = Color.yellow;
						if (Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)) < minimumforce2) { // if lowest possible speed
							minimumforce2=Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)); // replaces
							optAngle2 = i*(maxAngle-minAngle)/(angles-1)+minAngle; // replaces
							speed2=r; // replaces
							angle2=i; // replaces
						}
					}
					else if (balls[i][r].bounce == 0) { // home run
						functions[i][r].color = Color.blue;
						if (Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)) < minimumforce1) { // if lowest possible speed
							minimumforce1=Math.sqrt(Math.pow(balls[i][r].getInit_velocity_x(), 2) + Math.pow(balls[i][r].getInit_velocity_x(), 2)); // replaces
							optAngle1 = i*(maxAngle-minAngle)/(angles-1)+minAngle; // replaces
							speed1=r; // replaces
							angle1=i; // replaces
						}
					}
					frame.addDrawable(functions[i][r]); // after ball goes over wall, shows trail
				}
				if (balls[i][r].getXpos() >= walldist && balls[i][r].getYpos() < wallheight) { // bouncing off of wall
					balls[i][r].Xvel = -balls[i][r].Xvel*(forceLoss); // same thing as ground but for x
					balls[i][r].Yvel = balls[i][r].Yvel*(forceLoss); // same thing as ground but for y
				}
				if (balls[i][r].getYpos()>-.5) { // if ball is at or above ground, doesn't add point for ball far below surface
					functions[i][r].addPoint(balls[i][r].xStep(t), balls[i][r].yStep(t)); // moves the ball along
//					balls[i][r].xStep(t);
//					balls[i][r].yStep(t);
				}
				if (balls[i][r].getBounce() == 0 && balls[i][r].getYpos() > maxheight1) {
					maxheight1=balls[i][r].getYpos(); // replaces maxheight with no bounce
				}
				if (balls[i][r].getBounce() == 1 && balls[i][r].getYpos() > maxheight2) {
					maxheight2=balls[i][r].getYpos(); // replaces maxheight after 1 bounce
				}
				if (balls[i][r].getBounce() == 2 && balls[i][r].getYpos() > maxheight3) {
					maxheight3=balls[i][r].getYpos(); // replaces maxheight after 2 bounces
				}
			}
		}
		if (time == totalTime) { // when program is done
			if (minimumforce1 != 100000) { // if any ball goes over
				System.out.println("Minimum Speed for Ball with 0 Bounces: " + minimumforce1); // print out min speed
				System.out.println("Optimal Angle for Ball with 0 Bounces: " + optAngle1); // print out corresponding angle
			}
			if (maxheight1 > 0) { // if any ball flies
				if (maxheight1 > wallheight) {
					System.out.println("Home Run possible. Maximum Height for Ball with 0 Bounces: " + maxheight1);
				}
				else System.out.println("Home Run impossible. Maximum Height for Ball with 0 Bounces: " + maxheight1);
			}
			if (minimumforce2 != 100000) { // if any ball goes over
				System.out.println("Minimum Speed for Ball with 1 Bounce: " + minimumforce2); // print out min speed
				System.out.println("Optimal Angle for Ball with 1 Bounce: " + optAngle2); // print out corresponding angle
			}
			if (maxheight2 > 0) { // if any ball flies
				if (maxheight2 > wallheight) {
					System.out.println("Single Bounce Shot possible. Maximum Height for Ball with 1 Bounce: " + maxheight2);
				}
				else System.out.println("Single Bounce Shot impossible. Maximum Height for Ball with 1 Bounce: " + maxheight2);
			}
			if (minimumforce3 != 100000) { // if any ball goes over
				System.out.println("Minimum Speed for Ball with 2 Bounces: " + minimumforce3); // print out min speed
				System.out.println("Optimal Angle for Ball with 2 Bounces: " + optAngle3); // print out corresponding angle
			}
			if (maxheight3 > 0) { // if any ball flies
				if (maxheight3 > wallheight) {
					System.out.println("Double Bounce Shot possible. Maximum Height for Ball with 2 Bounces: " + maxheight3);
				}
				else System.out.println("Double Bounce Shot impossible. Maximum Height for Ball with 2 Bounces: " + maxheight3);
			}
			System.exit(0); // quits
		}
//		System.out.println(time);
		time++;
	}
	
	/**
	 * initialize()
	 * 
	 * Sets initial values for all balls and of all conditions, and prints out frame.
	 */
	
	public void initialize()
	{
		
		// sets all values to what user wants and inputs
		
		walldist=control.getDouble("Length of Field");
		wallheight=control.getDouble("Height of Wall");
		gravity = control.getDouble("Gravity"); // this is for changing gravity
		alpha = control.getDouble("Drag Coefficient"); // this is for changing alpha
		lift = control.getDouble("Lift Coefficient");
		forceLoss = control.getDouble("Restitution Coefficient");
		t = control.getDouble("Time Step");
		angles = control.getInt("Number of Angles");
		speeds = control.getInt("Number of Speeds");
		minAngle = control.getDouble("Minimum Angle");
		maxAngle = control.getDouble("Maximum Angle");
		minSpeed = control.getDouble("Minimum Speed");
		maxSpeed = control.getDouble("Maximum Speed");
		
		super.setDelayTime(0);
		
		functions = new Trail[angles][speeds]; // resets functions
		balls = new Particle[angles][speeds]; // resets balls
		
		// sets up all balls:
		
		for (int r=0; r<speeds; r++) {
			for(int i=0; i<angles; i++) {
				balls[i][r] = new Particle();
				balls[i][r].setAlpha(this.alpha);
				balls[i][r].setLift(this.lift);
				balls[i][r].setGravity(this.gravity);
				balls[i][r].setBounce(0);
				balls[i][r].setMass(0.15);
				balls[i][r].setRadius(3);
				balls[i][r].setYaccel(-9.8);
				balls[i][r].setXaccel(0);
				balls[i][r].setYvel((r*(maxSpeed-minSpeed)/(speeds-1)+minSpeed)*(Math.sin(Math.toRadians(i*(maxAngle-minAngle)/(angles-1)+minAngle)))); // range of angles and speeds for y velocity
				balls[i][r].setXvel((r*(maxSpeed-minSpeed)/(speeds-1)+minSpeed)*(Math.cos(Math.toRadians(i*(maxAngle-minAngle)/(angles-1)+minAngle)))); // range of angles and speeds for x velocity
				balls[i][r].setInit_velocity_x(balls[i][r].getXvel());
				balls[i][r].setInit_velocity_y(balls[i][r].getYvel());
				balls[i][r].setYpos(1);
				balls[i][r].setXpos(0);
				balls[i][r].setX(balls[i][r].Xpos);
				balls[i][r].setY(balls[i][r].Ypos);
				functions[i][r] = new Trail();
				frame.addDrawable(balls[i][r]);
			}
		}
		
		DrawableShape grass = DrawableShape.createRectangle(walldist/2, -.5, walldist, 1); // center x value, center y value, total width, total height
		grass.setMarkerColor(Color.GREEN, Color.GREEN); // interior blue, black border
		frame.addDrawable(grass);
		DrawableShape wall = DrawableShape.createRectangle(walldist+.125, (wallheight+1)/2 - 1, .25, wallheight+1); // center x value, center y value, total width, total height
		wall.setMarkerColor(Color.BLACK, Color.BLACK); // interior blue, black border
		frame.addDrawable(wall);
		frame.setPreferredMinMax(walldist-40, walldist+5, -4, wallheight+17);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	/**
	 * reset()
	 * 
	 * Resets all initial values.
	 */

	public void reset()
	{
		
		// sets reasonable initial values
		
		control.setValue("Length of Field", 120);
		control.setValue("Height of Wall", 3);
		control.setValue("Gravity", -9.8);
		control.setValue("Drag Coefficient", 0.01);
		control.setValue("Lift Coefficient", 0);
		control.setValue("Restitution Coefficient", 0.5);
		control.setValue("Time Step", 0.01);
		control.setValue("Number of Angles", 31);
		control.setValue("Number of Speeds", 41);
		control.setValue("Minimum Angle", 30);
		control.setValue("Maximum Angle", 60);
		control.setValue("Minimum Speed", 30);
		control.setValue("Maximum Speed", 70);
		
		// resets values to what user wants
		
		walldist=control.getDouble("Length of Field");
		wallheight=control.getDouble("Height of Wall");
		gravity = control.getDouble("Gravity"); // this is for changing gravity
		alpha = control.getDouble("Drag Coefficient"); // this is for changing alpha
		lift = control.getDouble("Lift Coefficient");
		forceLoss = control.getDouble("Restitution Coefficient");
		t = control.getDouble("Time Step");
		angles = control.getInt("Number of Angles");
		speeds = control.getInt("Number of Speeds");
		minAngle = control.getDouble("Minimum Angle");
		maxAngle = control.getDouble("Maximum Angle");
		minSpeed = control.getDouble("Minimum Speed");
		maxSpeed = control.getDouble("Maximum Speed");
		
		super.setDelayTime(0);
	}
	
	/**
	 * main(String[] args)
	 * 
	 * Instantiates the BallApp.
	 * 
	 * @param args
	 */

	public static void main(String[] args)
	{	
		SimulationControl.createApp(new BallApp());
	}
}
