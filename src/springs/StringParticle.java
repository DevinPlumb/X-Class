package springs;

import org.opensourcephysics.display.Circle;

public class StringParticle extends Circle {

  double alpha;
  double mass;
  double radius;
  double Xaccel;
  double Yaccel;
  double Xvel;
  double Yvel;
  double Xpos;
  double Ypos;
  double leftx;
  double lefty;
  double dt;
  double force;
  double righty;
  double rightx;
  double k;
  double restlength;

  public double getRestlength() {
    return restlength;
  }
  public void setRestlength(double restlength) {
    this.restlength = restlength;
  }
  public double getRighty() {
    return righty;
  }
  public void setRighty(double righty) {
    this.righty = righty;
  }
  public double getRightx() {
    return rightx;
  }
  public void setRightx(double rightx) {
    this.rightx = rightx;
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


  public double getLeftx() {
    return leftx;
  }
  public void setLeftx(double leftx) {
    this.leftx = leftx;
  }
  public double getLefty() {
    return lefty;
  }
  public void setLefty(double lefty) {
    this.lefty = lefty;
  }

  public double getAlpha() {
    return alpha;
  }
  public void setAlpha(double alpha) {
    this.alpha = alpha;
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

  public void step() {
    double fxLeft = getK()*(getLeftx()-getXpos());
    double fyLeft = getK()*(getLefty()-getYpos());

    double fxRight = getK()*(getRightx()-getXpos());
    double fyRight = getK()*(getRighty()-getYpos());

    double fx = fxLeft+fxRight;
    double fy = fyLeft+fyRight;
    
    System.out.println("fxtotal:" + fx);
    System.out.println("fytotal:" + fy);
    System.out.println();

    setYaccel((fy)/this.getMass());
    setXaccel((fx)/this.getMass());
    setXvel((getXvel()+getXaccel()*getDt()));
    setYvel((getYvel()+getYaccel()*getDt()));
    setXY((getXpos()+getXvel()*getDt()),(getYpos()+getYvel()*getDt()));
    setXpos(getXpos()+getXvel()*getDt());
    setYpos(getYpos()+getYvel()*getDt());
  }
}