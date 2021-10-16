package springs;

public class SpringParticle2 {
	
	double alpha;
	double gravity;
	double mass;
	double radius;
	double Xaccel;
	double Yaccel;
	double Xvel;
	double Yvel;
	double Xpos;
	double Ypos;
	double prevxnew;
	double prevynew;
	double dt;
	double force;
	double prevxold;
	double prevyold;
	double k;


	public double getPrevxold() {
		return prevxold;
	}
	public void setPrevxold(double prevxold) {
		this.prevxold = prevxold;
	}
	public double getPrevyold() {
		return prevyold;
	}
	public void setPrevyold(double prevyold) {
		this.prevyold = prevyold;
	}

	public double getForce() {
		return force;
	}
	public void setForce(double force) {
		this.force = force;
	}
	public double getDt() {
		return dt;
	}
	public void setDt(double dt) {
		this.dt = dt;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}

	public double getPrevxnew() {
		return prevxnew;
	}
	public void setPrevxnew(double prevxnew) {
		this.prevxnew = prevxnew;
	}
	public double getPrevynew() {
		return prevynew;
	}
	public void setPrevynew(double prevynew) {
		this.prevynew = prevynew;
	}

	public double getAlpha() {
		return alpha;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}


	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}


	public double getXaccel() {
		return Xaccel;
	}
	public void setXaccel(double xaccel) {
		Xaccel = xaccel;
	}


	public double getYaccel() {
		return Yaccel;
	}
	public void setYaccel(double yaccel) {
		Yaccel = yaccel;
	}

	public double getXvel() {
		return Xvel;
	}
	public void setXvel(double xvel) {
		Xvel = xvel;
	}

	public double getYvel() {
		return Yvel;
	}
	public void setYvel(double yvel) {
		Yvel = yvel;
	}

	public double getXpos() {
		return Xpos;
	}
	public void setXpos(double xpos) {
		Xpos = xpos;
	}

	public double getYpos() {
		return Ypos;
	}
	public void setYpos(double ypos) {
		Ypos = ypos;
	}

	//this.getPos - this.getPrevXnew were getting a really long length between them it has to be the change in the distance between them not the distance between them
	public void step() {
		double xChange = getPrevxnew()-getPrevxold();
		System.out.println("xChange: " + xChange);
		double yChange = getPrevynew()-getPrevyold();
		System.out.println("yChange: " + yChange);
		double xForce=mass*Xaccel;
		double yForce=mass*Yaccel;
	}
}
