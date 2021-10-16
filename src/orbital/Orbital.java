package orbital;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Interactive;
import org.opensourcephysics.display.InteractiveMouseHandler;
import org.opensourcephysics.display.InteractivePanel;
//import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

import java.awt.Color;
import java.awt.event.MouseEvent;
//Gravity Constant thing
import java.util.ArrayList;

public class Orbital extends AbstractSimulation {

	int numPlanets;
	double t;
	double time;
	double speedSquare;
	double maxSpeed = 0;
	double maxX;
	double maxY;
	double minSpeed = Double.MAX_VALUE;
	double minX;
	double minY;
	double initX;
	double initY;
	double prevX;
	double prevY;
	double currentError = 0;
	double lastError;
	double distanceError;
	int insideDistanceZone = 0;
	boolean ellipseDrawn = false;
	static double gravityConstant;
	DisplayFrame frame = new DisplayFrame("X", "Y", "Orbital");
	DisplayFrame sunSpeed = new DisplayFrame("Vx", "Vy", "Sun Speed");
	DisplayFrame earthSpeed = new DisplayFrame("Vx", "Vy", "Earth Speed");
	DisplayFrame mercurySpeed = new DisplayFrame("Vx", "Vy", "Mercury Speed");
	DisplayFrame venusSpeed = new DisplayFrame("Vx", "Vy", "Venus Speed");
	DisplayFrame rocketSpeed = new DisplayFrame("Vx", "Vy", "Rocket Speed");
	double placeX;
	double placeY;
	boolean gravityAssist;

	OrbitalParticle selected = null;

	ControlPanel panel;

	ArrayList<Trail> trails = new ArrayList<Trail>();
	Trail sunSpeedTrail = new Trail();
	Trail earthSpeedTrail = new Trail();
	Trail mercurySpeedTrail = new Trail();
	Trail venusSpeedTrail = new Trail();
	Trail rocketSpeedTrail = new Trail();
	ArrayList<OrbitalParticle> planets = new ArrayList();


	protected void doStep() {

		// forces
		for (int i = 0; i < planets.size(); i++) {
			planets.get(i).netForce(planets);
		}

		// changing position
		for (int i = 0; i < planets.size(); i++) {
			trails.get(i).addPoint(planets.get(i).xStep(t), planets.get(i).yStep(t));
		}

		System.out.println(planets.get(1).getY() + ", " + planets.get(1).getYvel() + ", " + planets.get(1).getYaccel());

		time += t;

		sunSpeedTrail.addPoint(time, Math.sqrt(Math.pow(planets.get(0).getXvel(), 2) + Math.pow(planets.get(0).getYvel(), 2)) * 10E6);
		if (gravityAssist == false) {
			earthSpeedTrail.addPoint(time, Math.sqrt(Math.pow(planets.get(1).getXvel(), 2) + Math.pow(planets.get(1).getYvel(), 2)) * 10E4);
			mercurySpeedTrail.addPoint(time, Math.sqrt(Math.pow(planets.get(2).getXvel(), 2) + Math.pow(planets.get(2).getYvel(), 2)) * 10E4);
			venusSpeedTrail.addPoint(time, Math.sqrt(Math.pow(planets.get(3).getXvel(), 2) + Math.pow(planets.get(3).getYvel(), 2)) * 10E4);
		}
		if (gravityAssist == true) rocketSpeedTrail.addPoint(time, Math.sqrt(Math.pow(planets.get(1).getXvel(), 2) + Math.pow(planets.get(1).getYvel(), 2)) * 10E4);


		// UPDATE MENU

		// Update selected
		if (selected != null) {
			if (!panel.massText.isFocusOwner()) {
				panel.massText.setText(Double.toString(selected.getMass()));
			}
			if (!panel.radText.isFocusOwner()) {
				panel.radText.setText(Double.toString(selected.pixRadius));
			}
			if (!panel.xText.isFocusOwner()) {
				panel.xText.setText(Double.toString(selected.getX()));
			}
			if (!panel.yText.isFocusOwner()) {
				panel.yText.setText(Double.toString(selected.getY()));
			}
			if (!panel.vxText.isFocusOwner()) {
				panel.vxText.setText(Double.toString(selected.getXvel()));
			}
			if (!panel.vyText.isFocusOwner()) {
				panel.vyText.setText(Double.toString(selected.getYvel()));
			}
		} else {
			if (!panel.massText.isFocusOwner()) {
				panel.massText.setText("");
			}
			if (!panel.radText.isFocusOwner()) {
				panel.radText.setText("");
			}
			if (!panel.xText.isFocusOwner()) {
				panel.xText.setText("");
			}
			if (!panel.yText.isFocusOwner()) {
				panel.yText.setText("");
			}
			if (!panel.vxText.isFocusOwner()) {
				panel.vxText.setText("");
			}
			if (!panel.vyText.isFocusOwner()) {
				panel.vyText.setText("");
			}
		}

	}

	public void initialize() {

		numPlanets = control.getInt("Number of Planets");
		t = control.getDouble("Time Step");
		time = 0;
		gravityConstant = control.getDouble("Gravity Constant");
		distanceError = control.getDouble("Ellipse Error");

		planets = new ArrayList<OrbitalParticle>();
		trails = new ArrayList<Trail>();

		planets.add(new OrbitalParticle());
		planets.get(0).setMass(control.getDouble("Sun Mass"));
		planets.get(0).setX(control.getDouble("Sun X Position"));
		planets.get(0).setY(control.getDouble("Sun Y Position"));
		planets.get(0).setXvel(control.getDouble("Sun X Velocity"));
		planets.get(0).setYvel(control.getDouble("Sun Y Velocity"));
		planets.get(0).setXaccel(control.getDouble("Sun X Acceleration"));
		planets.get(0).setYaccel(control.getDouble("Sun Y Acceleration"));
		planets.get(0).setRadius(control.getDouble("Sun Radius"));
		planets.get(0).setOrbitalParticleNumber(0);
		planets.get(0).setName("Sun");
		planets.get(0).color = Color.yellow;
		trails.add(new Trail());
		if (gravityAssist == false) {
			planets.add(new OrbitalParticle());
			planets.get(1).setMass(control.getDouble("Earth Mass"));
			planets.get(1).setX(control.getDouble("Earth X Position"));
			planets.get(1).setY(control.getDouble("Earth Y Position"));
			planets.get(1).setXvel(control.getDouble("Earth X Velocity"));
			planets.get(1).setYvel(control.getDouble("Earth Y Velocity"));
			planets.get(1).setXaccel(control.getDouble("Earth X Acceleration"));
			planets.get(1).setYaccel(control.getDouble("Earth Y Acceleration"));
			planets.get(1).setRadius(control.getDouble("Earth Radius"));
			planets.get(1).setOrbitalParticleNumber(1);
			planets.get(1).setName("Earth");
			planets.get(1).color = Color.blue;
			trails.add(new Trail());
			planets.add(new OrbitalParticle());
			planets.get(2).setMass(328.5E21);
			planets.get(2).setX(57910000000.0);
			planets.get(2).setY(0);
			planets.get(2).setXvel(0);
			planets.get(2).setYvel(47833.3);
			planets.get(2).setXaccel(0);
			planets.get(2).setYaccel(0);
			planets.get(2).setRadius(3.0);
			planets.get(2).setOrbitalParticleNumber(2);
			planets.get(2).setName("Mercury");
			planets.get(2).color = Color.orange;
			trails.add(new Trail());
			planets.add(new OrbitalParticle());
			planets.get(3).setMass(4.867E24);
			planets.get(3).setX(108E9);
			planets.get(3).setY(0);
			planets.get(3).setXvel(0);
			planets.get(3).setYvel(35.02E3);
			planets.get(3).setXaccel(0);
			planets.get(3).setYaccel(0);
			planets.get(3).setRadius(4.0);
			planets.get(3).setOrbitalParticleNumber(3);
			planets.get(3).setName("Venus");
			planets.get(3).color = Color.PINK;
			trails.add(new Trail());
		}
		if (gravityAssist == true) {
			planets.add(new OrbitalParticle());
			planets.get(1).setMass(2.0E6);
			planets.get(1).setX(0);
			planets.get(1).setY(1E6);
			planets.get(1).setXvel(0);
			planets.get(1).setYvel(1E9);
			planets.get(1).setXaccel(0);
			planets.get(1).setYaccel(0);
			planets.get(1).setRadius(4.0);
			planets.get(1).setOrbitalParticleNumber(1);
			planets.get(1).setName("Rocket");
			trails.add(new Trail());
		}

		panel = new ControlPanel(this);

		for (OrbitalParticle p : planets) {
			panel.planetsSelector.addItem(p);
		}

		panel.pack();
		panel.setVisible(true);

		frame.clearDrawables();
		frame.addDrawable(trails.get(0));
		frame.addDrawable(trails.get(1));
		if (gravityAssist == false) {
			frame.addDrawable(trails.get(2));
			frame.addDrawable(trails.get(3));
		}
		frame.addDrawable(planets.get(0));
		frame.addDrawable(planets.get(1));
		if (gravityAssist == false) {
			frame.addDrawable(planets.get(2));
			frame.addDrawable(planets.get(3));
		}
		frame.setVisible(true);
		frame.setPreferredMinMax(-2E11, 2E11, -2E11, 2E11);

		sunSpeed.addDrawable(sunSpeedTrail);
		sunSpeed.setVisible(true);
		sunSpeed.setPreferredMinMax(-2E7, 2E7, -2E7, 2E7);

		if (gravityAssist == false) {
			earthSpeed.addDrawable(earthSpeedTrail);
			earthSpeed.setVisible(true);
			earthSpeed.setPreferredMinMax(-2E8, 2E8, 27.8E8, 31.8E8);

			mercurySpeed.addDrawable(mercurySpeedTrail);
			mercurySpeed.setVisible(true);
			mercurySpeed.setPreferredMinMax(-2E8, 2E8, 46.2E8, 50.2E8);

			venusSpeed.addDrawable(venusSpeedTrail);
			venusSpeed.setVisible(true);
			venusSpeed.setPreferredMinMax(-2E8, 2E8, 33.2E8, 37.2E8);
		}

		if (gravityAssist == true) {
			rocketSpeed.addDrawable(rocketSpeedTrail);
			rocketSpeed.setVisible(true);
			rocketSpeed.setPreferredMinMax(-2E8, 2E8, -2E8, 2E8);
		}
	}

	public void reset() {

		control.setValue("Sun Mass", 2E30);
		control.setValue("Sun X Position", 0);
		control.setValue("Sun Y Position", 0);
		control.setValue("Sun X Velocity", 0);
		control.setValue("Sun Y Velocity", 0);
		control.setValue("Sun X Acceleration", 0);
		control.setValue("Sun Y Acceleration", 0);
		control.setValue("Sun Radius", 2);
		control.setValue("Earth Mass", 6E24);
		control.setValue("Earth X Position", 1.5E11);
		control.setValue("Earth Y Position", 0);
		control.setValue("Earth X Velocity", 0);
		control.setValue("Earth Y Velocity", 29747); // 29747 / 2.0
		control.setValue("Earth X Acceleration", 0);
		control.setValue("Earth Y Acceleration", 0);
		control.setValue("Earth Radius", 1);
		control.setValue("Number of Planets", 2);
		control.setValue("Time Step", 30000);
		control.setValue("Gravity Constant", 6.674E-11);
		control.setValue("Ellipse Error", 1E10);
		control.setValue("Gravity Assist", true);

		numPlanets = control.getInt("Number of Planets");
		t = control.getDouble("Time Step");
		time = 0;
		gravityConstant = control.getDouble("Gravity Constant");
		distanceError = control.getDouble("Ellipse Error");
		gravityAssist = control.getBoolean("Gravity Assist");

		super.setDelayTime(0);

		frame.setInteractiveMouseHandler(new InteractiveMouseHandler() {

			public void handleMouseAction(InteractivePanel panel2, MouseEvent evt) {
				panel2.handleMouseAction(panel2, evt);
				switch(panel2.getMouseAction()) {
				case InteractivePanel.MOUSE_DRAGGED :
					break;
				case InteractivePanel.MOUSE_PRESSED :
					placeX = panel2.getMouseX();
					placeY = panel2.getMouseY();
					break;
				case InteractivePanel.MOUSE_RELEASED :
					planets.add(new OrbitalParticle());
					int index = planets.size()-1;
					planets.get(index).setMass(control.getDouble("Earth Mass"));
					planets.get(index).setX(placeX);
					planets.get(index).setY(placeY);
					planets.get(index).setXvel((panel2.getMouseX()-placeX) / (t*10));
					planets.get(index).setYvel((panel2.getMouseY()-placeY) / (t*10));
					planets.get(index).setXaccel(0);
					planets.get(index).setYaccel(0);
					planets.get(index).setRadius(control.getDouble("Earth Radius"));
					planets.get(index).setOrbitalParticleNumber(index);
					planets.get(index).setName("Planet " + index);
					planets.get(index).color = new Color(Color.HSBtoRGB((float)Math.random(), 1, 1));
					trails.add(new Trail());
					trails.get(index).color = planets.get(index).color;
					frame.addDrawable(trails.get(index));
					frame.addDrawable(planets.get(index));
					panel.planetsSelector.addItem(planets.get(index));
					break;
				}
			}
		});

	}

	public static void main(String[] args) {
		SimulationControl.createApp(new Orbital());
	}

}