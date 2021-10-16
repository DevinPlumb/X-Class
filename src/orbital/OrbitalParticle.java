package orbital;

import java.util.ArrayList;

import org.opensourcephysics.display.Circle;

public class OrbitalParticle extends Circle {
  
  double netForce;
  
  int orbitalParticleNumber;
  public int getOrbitalParticleNumber() {
    return orbitalParticleNumber;
  }
  public void setOrbitalParticleNumber(int orbitalParticleNumber) {
    this.orbitalParticleNumber = orbitalParticleNumber;
  }
  
  String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
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
  
  double netForceX;
  public double getNetForceX() {
    return netForceX;
  }
  public void setNetForceX(double netForceX) {
    this.netForceX = netForceX;
  }

  double netForceY;
  public double getNetForceY() {
    return netForceY;
  }
  public void setNetForceY(double netForceY) {
    this.netForceY = netForceY;
  }
  
  double restitution;
  
  public double getRestitution() {
    return restitution;
  }
  public void setRestitution(double restitution) {
    this.restitution = restitution;
  }
  public double objectDist (double otherX, double otherY) {
    return Math.sqrt(Math.pow(this.getX()-otherX, 2) + Math.pow(this.getY()-otherY, 2));
  }

  public double objectDistX (double otherX) {
    return otherX - this.getX();
  }

  public double objectDistY (double otherY) {
    return otherY - this.getY();
  }

  public double objectForce (double otherX, double otherY, double otherMass) {
    return OrbitalGeneral.gravityConstant*this.getMass()*otherMass/(Math.pow(objectDistX(otherX), 2) + Math.pow(objectDistY(otherY), 2));
  }

  public double objectForceX (double otherX, double otherY, double otherMass, double distance) {
    return OrbitalGeneral.gravityConstant*this.getMass()*otherMass*objectDistX(otherX)/(Math.pow(distance, 3));
  }

  public double objectForceY (double otherX, double otherY, double otherMass, double distance) {
    return OrbitalGeneral.gravityConstant*this.getMass()*otherMass*objectDistY(otherY)/(Math.pow(distance, 3));
  }

  public void netForce (ArrayList<OrbitalParticle> forces) {
    for (int i=this.getOrbitalParticleNumber(); i<forces.size(); i++) {
      if (i != this.getOrbitalParticleNumber()) {
        double distance = objectDist(forces.get(i).getX(), forces.get(i).getY());
        
        if (distance <= this.getRadius() + forces.get(i).getRadius()) {
            
            double dx = forces.get(i).getX() - this.getX();
            double dy = forces.get(i).getY() - this.getY();
            
            double nx = dx/distance;
            double ny = dy/distance;
            
            double tx = -ny;
            double ty = nx;
            
            double v1n = this.getXvel()*nx + this.getYvel()*ny;
            double v1t = this.getXvel()*tx + this.getYvel()*ty;
            double v2n = forces.get(i).getXvel()*nx + forces.get(i).getYvel()*ny;
            double v2t = forces.get(i).getXvel()*tx + forces.get(i).getYvel()*ty;
            
            double V1nf = OrbitalGeneral.coefRest*(v1n*(this.getMass()-forces.get(i).getMass()) + 2*(forces.get(i).getMass()*v2n)) / (this.getMass() + forces.get(i).getMass());
            double V2nf = OrbitalGeneral.coefRest*(v2n*(-this.getMass()+forces.get(i).getMass()) + 2*(this.getMass()*v1n)) / (this.getMass() + forces.get(i).getMass());
            
            double v1Fx = v1t*tx + V1nf*nx;
            double v1Fy = v1t*ty + V1nf*ny;
            double v2Fx = v2t*tx + V2nf*nx;
            double v2Fy = v2t*ty + V2nf*ny; 
               
            this.setXvel(v1Fx);
            this.setYvel(v1Fy);
            forces.get(i).setXvel(v2Fx);
            forces.get(i).setYvel(v2Fy);
            
           // OrbitalGeneral.coefRest;
            
            //System.out.println("COLLISION");
            
            /*
             * First, find line between two centers.
             * Next, find tangent (perpendicular of line between centers located at point of contact)
             * Finally, convert to 1-dimensional collision by regarding the perpendicular velocity as conserved.
             */
            
        }
        else {
          double forcex = objectForceX(forces.get(i).getX(), forces.get(i).getY(), forces.get(i).getMass(), distance);
          netForceX += forcex;
          forces.get(i).setNetForceX(forces.get(i).getNetForceX() - forcex);
          
          double forcey = objectForceY(forces.get(i).getX(), forces.get(i).getY(), forces.get(i).getMass(), distance);
          netForceY += forcey;
          forces.get(i).setNetForceY(forces.get(i).getNetForceY() - forcey);
        }
      }
    }
  }
  
//  public void netForceX (OrbitalParticle[] forcesX) {
//    for (int i=this.getOrbitalParticleNumber(); i<forcesX.length; i++) {
//      if (i != this.getOrbitalParticleNumber()) {
//        double force = objectForceX(forcesX[i].getX(), forcesX[i].getY(), forcesX[i].getMass());
//        netForceX += force;
//        forcesX[i].setNetForceX(forcesX[i].getNetForceX() - force);
//      }
//    }
//  }

//  public void netForceY (OrbitalParticle[] forcesY) {
//    for (int i=this.getOrbitalParticleNumber(); i<forcesY.length; i++) {
//      if (i != this.getOrbitalParticleNumber()) {
//        double force = objectForceY(forcesY[i].getX(), forcesY[i].getY(), forcesY[i].getMass());
//        netForceY += force;
//        forcesY[i].setNetForceY(forcesY[i].getNetForceY() - force);
//      }
//    }
//  }

  public double xStep(double t) {
//    this.setX(this.getX() + (t/4)*this.getXvel());
//    this.setXvel(this.getXvel() + (t/2)*this.getXaccel());
//    this.setX(this.getX() + (t/4)*this.getXvel());
//    this.setXaccel(netForceX/this.getMass());
//    this.setX(this.getX() + (t/4)*this.getXvel());
//    this.setXvel(this.getXvel() + (t/2)*this.getXaccel());
//    this.setX(this.getX() + (t/4)*this.getXvel());
    
    this.setXaccel(netForceX / this.getMass());
    this.setXvel(this.getXvel() + t * this.getXaccel());
    this.setX(this.getX() + t*this.getXvel());
    
    netForceX=0;
    return this.getX();
  }

  public double yStep(double t) {
//    this.setY(this.getY() + (t/4)*this.getYvel());
//    this.setYvel(this.getYvel() + (t/2)*this.getYaccel());
//    this.setY(this.getY() + (t/4)*this.getYvel());
//    this.setYaccel(netForceY/this.getMass());
//    this.setY(this.getY() + (t/4)*this.getYvel());
//    this.setYvel(this.getYvel() + (t/2)*this.getYaccel());
//    this.setY(this.getY() + (t/4)*this.getYvel());
    
    this.setYaccel(netForceY / this.getMass());
    this.setYvel(this.getYvel() + t * this.getYaccel());
    this.setY(this.getY() + t*this.getYvel());
    
    netForceY=0;
    return this.getY();
  }
  
  public String toString() {
    return this.getName();
  }

}
