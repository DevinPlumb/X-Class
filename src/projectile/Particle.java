package projectile;

import org.opensourcephysics.display.Circle;

public class Particle extends Circle {
	
	double alpha;
	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
	double lift;
	public double getLift() {
		return lift;
	}
	public void setLift(double lift) {
		this.lift = lift;
	}

	double gravity;
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	double init_velocity_x;
	public double getInit_velocity_x() {
		return init_velocity_x;
	}
	public void setInit_velocity_x(double init_velocity_x) {
		this.init_velocity_x = init_velocity_x;
	}
	
	double init_velocity_y;
	public double getInit_velocity_y() {
		return init_velocity_y;
	}
	public void setInit_velocity_y(double init_velocity_y) {
		this.init_velocity_y = init_velocity_y;
	}

	int bounce;
	public int getBounce() {
		return bounce;
	}
	public void setBounce(int bounce) {
		this.bounce = bounce;
	}

	double mass;
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}

	double radius;
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	double Xaccel;
	public double getXaccel() {
		return Xaccel;
	}
	public void setXaccel(double xaccel) {
		Xaccel = xaccel;
	}
	
	double Yaccel;
	public double getYaccel() {
		return Yaccel;
	}
	public void setYaccel(double yaccel) {
		Yaccel = yaccel;
	}

	double Xvel;
	public double getXvel() {
		return Xvel;
	}
	public void setXvel(double xvel) {
		Xvel = xvel;
	}

	double Yvel;
	public double getYvel() {
		return Yvel;
	}
	public void setYvel(double yvel) {
		Yvel = yvel;
	}

	double Xpos;
	public double getXpos() {
		return Xpos;
	}
	public void setXpos(double xpos) {
		Xpos = xpos;
	}

	double Ypos;
	public double getYpos() {
		return Ypos;
	}
	public void setYpos(double ypos) {
		Ypos = ypos;
	}
	
	public double root() { // saves time, only 1 root calculation
		double root = Math.sqrt(Math.pow(this.getXvel(), 2) + Math.pow(this.getYvel(), 2));
		return root;
	}
	
	public double xStep(double t) { // moves x
//		System.out.println("Xaccel: " + Xaccel);
		// resets values
		this.setXaccel(-alpha*this.getXvel()*root() + -lift*alpha*this.getYvel()*root());
		this.setXvel(this.getXvel() + t*this.getXaccel());
		this.setXpos(this.getX() + t*this.getXvel());
		
		this.setX(this.getXpos());
		return this.getXpos();
	}
	
	public double yStep(double t) { // moves y
//		System.out.println("Yaccel: " + Yaccel);
		// resets values
		this.setYaccel(gravity - alpha*this.getYvel()*root() + lift*alpha*this.getXvel()*root());
		this.setYvel(this.getYvel() + t*this.getYaccel());
		this.setYpos(this.getY() + t*this.getYvel());
		
		this.setY(this.getYpos());
		return this.getYpos();
	}
	// http://twu.tennis-warehouse.com/learning_center/aerodynamics2.php
	
}
