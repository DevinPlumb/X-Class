package projectile;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.DisplayFrame;

public class BasicApplication extends AbstractSimulation {

	int x;
	double time = 0;
	Particle Ball = new Particle();
	//	Circle c = new Circle();
	DisplayFrame frame = new DisplayFrame("x", "y", "Frame");  //two object which are created outside of any method so all methods can access them

	@Override
	protected void doStep() {
		for (time = 0; time < 300; time++) {
			Ball.setXY(Ball.Xpos + Ball.Xvel*time + Ball.Xaccel*time*time/2, Ball.Ypos + Ball.Yvel*time + Ball.Yaccel*time*time/2);
			time=+.1;
		}
	}

	public void reset()
	{
		control.setValue("x", 0);
		control.setValue("y", 0);	
	}

	public void initialize()
	{
		Ball.setMass(0.15);
		Ball.setRadius(3);
		Ball.setYaccel(-9.8);
		Ball.setXaccel(0);
		Ball.setYvel(30);
		Ball.setXvel(40);
		Ball.setYpos(1);
		Ball.setXpos(0);
		Ball.setXY(Ball.Xpos, Ball.Ypos);
		frame.setPreferredMinMax(-30, 150, -30, 150);
		frame.setVisible(true);
		frame.addDrawable(Ball);
	}

	public void stop()
	{

	}

	public static void main(String[] args)
	{
		SimulationControl.createApp(new BasicApplication());

	}

}
