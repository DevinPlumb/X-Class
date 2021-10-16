package springs;

import java.awt.Color;
import java.util.Scanner;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;


public class EEDString extends AbstractSimulation {
	DisplayFrame frame = new DisplayFrame("X", "Y", "String");

	int stringSize;
	double stringLength;
	double stringMass;
	double force1;
	double amplitude = .5;
	double interval = amplitude/50;
	StringParticle[] particles;

	protected void doStep() {
		particles[0].setForce(control.getDouble("initialForce"));
		if (Math.abs(particles[0].getYpos()) >= amplitude) {
			interval=-interval;
		}
		particles[0].setYpos(particles[0].getYpos()+interval);
		particles[0].setXY(0, particles[0].getYpos());

		for (int i=1; i<particles.length; i++) {
			particles[i].setLeftx(particles[i-1].getXpos());
			particles[i].setLefty(particles[i-1].getYpos());
			particles[i].setRightx(particles[i+1].getXpos());
			particles[i].setRighty(particles[i+1].getYpos());
			particles[i].step();
		}
	}

	public void initialize() {
		super.setDelayTime(0);
		super.setStepsPerDisplay(2);
		stringSize = control.getInt("stringSize");// number of particles in the string
		stringLength = control.getDouble("stringLength");// length of string
		stringMass = control.getInt("stringMass");// mass of string
		particles = new StringParticle[control.getInt("stringSize")];
		//frame.setPreferredMinMax();
		frame.setVisible(true);
		frame.setLocation(0, -300);
		frame.setSize(1000, 1000);

		for (int i=0; i<particles.length; i++) {
			particles[i] = new StringParticle();
			particles[i].setK(control.getDouble("k"));
			particles[i].setMass(stringMass/stringSize);
			particles[i].setXpos((i*stringLength)/(stringSize-1));
			particles[i].setXvel(0);
			particles[i].setXaccel(0);
			particles[i].setYpos(0);
			particles[i].setYvel(0);
			particles[i].setYaccel(0);
			particles[i].setRestlength(stringLength/((stringSize-1)));
			particles[i].setDt(control.getDouble("timestep"));
			if(i!=0) {
				particles[i].setLeftx(particles[i-1].getXpos());
				particles[i].setLefty(0);
			}
			else {
				particles[i].setLeftx(0);
				particles[i].setLefty(0);
			}
			if(i!=particles.length-1) {
				particles[i].setRightx(particles[i+1].getXpos());
				particles[i].setRighty(0);
			}
			else {
				particles[i].setRightx(0);
				particles[i].setRighty(0);
			}
			particles[i].setXY(particles[i].getXpos(), particles[i].getYpos());
			frame.addDrawable(particles[i]);
		}

	}

	public void reset() {
		frame.clearDataAndRepaint();
		frame.clearDrawables();
		frame.clearChildFrames();
		control.setValue("stringSize", 10);
		control.setValue("stringLength",5);
		control.setValue("stringMass", 5.0);
		control.setValue("k", 2.0);
		control.setValue("initialForce", 10);
		control.setValue("timestep", .01);
		control.setValue("restlength", 0);
	}
	
	public static void main(String[] args) {
		SimulationControl.createApp(new EEDString());
	}


}