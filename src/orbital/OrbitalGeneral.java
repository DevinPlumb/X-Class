package orbital;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
//Gravity Constant thing
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.InteractiveMouseHandler;
import org.opensourcephysics.display.InteractivePanel;
//import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.frames.PlotFrame;

//collisions not implemented
//gravity assist does not have it's own thing need to add that

public class OrbitalGeneral extends AbstractSimulation {

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
  static double coefRest;
  double lastError;
  double distanceError;
  int insideDistanceZone = 0;
  boolean ellipseDrawn = false;
  static double gravityConstant;
  DisplayFrame frame = new DisplayFrame("X", "Y", "Position");
  double placeX;
  double placeY;
  static boolean elasticCollisions;
  
  PlotFrame speed = new PlotFrame("Time", "Speed", "Speed");
  PlotFrame acceleration = new PlotFrame("Time", "Acceleration", "Acceleration");

  OrbitalParticle selected = null;

  ControlPanel panel;

  ArrayList<Trail> trails = new ArrayList<Trail>();
  ArrayList<Trail> speeds = new ArrayList<Trail>();
  ArrayList<Trail> accelerations = new ArrayList<Trail>();
  ArrayList<OrbitalParticle> planets = new ArrayList();

  protected void doStep() {

    // forces
    for (int i = 0; i < planets.size(); i++) {
      planets.get(i).netForce(planets);
      
      planets.get(i).pixRadius = (int) Math.max(frame.getDrawingPanel().getMaxPixPerUnit() * planets.get(i).getRadius(), 4);
    }

    // changing position
    for (int i = 0; i < planets.size(); i++) {
      trails.get(i).addPoint(planets.get(i).xStep(t), planets.get(i).yStep(t));
      double vel = Math.sqrt(Math.pow(planets.get(i).getXvel(), 2) + Math.pow(planets.get(i).getYvel(), 2));
      speeds.get(i).addPoint(time, vel);
      //System.out.println(speed);
      speed.append(i, time, vel);
      
      double accel = Math.sqrt(Math.pow(planets.get(i).getXaccel(), 2) + Math.pow(planets.get(i).getYaccel(), 2));
      accelerations.get(i).addPoint(time, accel);
      acceleration.append(i, time, accel);
      
      
    }

    time += t;

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
    elasticCollisions = control.getBoolean("Elastic Collisions");
    coefRest = control.getDouble("Coefficient of Restitution");

    planets = new ArrayList<OrbitalParticle>();
    trails = new ArrayList<Trail>();
    speeds = new ArrayList<Trail>();
    accelerations = new ArrayList<Trail>();

    if (control.getBoolean("Binary Star")) {
      planets.add(new OrbitalParticle());
      planets.get(0).setMass(2E30);
      planets.get(0).setX(1E10);
      planets.get(0).setY(0);
      planets.get(0).setXvel(0);
      planets.get(0).setYvel(6.5E4);
      planets.get(0).setXaccel(0);
      planets.get(0).setYaccel(0);
      planets.get(0).setRadius(2);
      planets.get(0).setOrbitalParticleNumber(0);
      planets.get(0).setName("Sun");
      planets.get(0).color = Color.yellow;
      planets.add(new OrbitalParticle());
      planets.get(1).setMass(6E24);
      planets.get(1).setX(1.5E11);
      planets.get(1).setY(0);
      planets.get(1).setXvel(0);
      planets.get(1).setYvel(42426);
      planets.get(1).setXaccel(0);
      planets.get(1).setYaccel(0);
      planets.get(1).setRadius(1);
      planets.get(1).setOrbitalParticleNumber(1);
      planets.get(1).setName("Earth");
      planets.get(1).color = Color.blue;
      planets.add(new OrbitalParticle());
      planets.get(2).setMass(2E30);
      planets.get(2).setX(-1E10);
      planets.get(2).setY(0);
      planets.get(2).setXvel(0);
      planets.get(2).setYvel(-6.5E4);
      planets.get(2).setXaccel(0);
      planets.get(2).setYaccel(0);
      planets.get(2).setRadius(2);
      planets.get(2).setOrbitalParticleNumber(2);
      planets.get(2).setName("Sun Two");
      planets.get(2).color = Color.yellow;
      frame.setPreferredMinMax(-6E11, 6E11, -6E11, 6E11);
    }

    if (control.getBoolean("Solar System")) {
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
      speeds.add(new Trail());
      planets.add(new OrbitalParticle());
      planets.get(4).setMass(639E21);
      planets.get(4).setX(227900000000.0);
      planets.get(4).setY(0);
      planets.get(4).setXvel(0);
      planets.get(4).setYvel(24.077E3);
      planets.get(4).setXaccel(0);
      planets.get(4).setYaccel(0);
      planets.get(4).setRadius(4.0);
      planets.get(4).setOrbitalParticleNumber(4);
      planets.get(4).setName("Mars");
      planets.get(4).color = Color.RED;
      planets.add(new OrbitalParticle());
      planets.get(5).setMass(1.898E27);
      planets.get(5).setX(778500000000.0);
      planets.get(5).setY(0);
      planets.get(5).setXvel(0);
      planets.get(5).setYvel(13.07E3);
      planets.get(5).setXaccel(0);
      planets.get(5).setYaccel(0);
      planets.get(5).setRadius(4.0);
      planets.get(5).setOrbitalParticleNumber(5);
      planets.get(5).setName("Jupiter");
      planets.get(5).color = Color.GREEN;
      planets.add(new OrbitalParticle());
      planets.get(6).setMass(568.3E24);
      planets.get(6).setX(1433000000000.0);
      planets.get(6).setY(0);
      planets.get(6).setXvel(0);
      planets.get(6).setYvel(9.69E3);
      planets.get(6).setXaccel(0);
      planets.get(6).setYaccel(0);
      planets.get(6).setRadius(4.0);
      planets.get(6).setOrbitalParticleNumber(6);
      planets.get(6).setName("Saturn");
      planets.get(6).color = Color.CYAN;
      planets.add(new OrbitalParticle());
      planets.get(7).setMass(86.81E24);
      planets.get(7).setX(2877000000000.0);
      planets.get(7).setY(0);
      planets.get(7).setXvel(0);
      planets.get(7).setYvel(6.80E3);
      planets.get(7).setXaccel(0);
      planets.get(7).setYaccel(0);
      planets.get(7).setRadius(4.0);
      planets.get(7).setOrbitalParticleNumber(7);
      planets.get(7).setName("Uranus");
      planets.get(7).color = Color.CYAN;
      planets.add(new OrbitalParticle());
      planets.get(8).setMass(102.4E24);
      planets.get(8).setX(4503000000000.0);
      planets.get(8).setY(0);
      planets.get(8).setXvel(0);
      planets.get(8).setYvel(5.43E3);
      planets.get(8).setXaccel(0);
      planets.get(8).setYaccel(0);
      planets.get(8).setRadius(4.0);
      planets.get(8).setOrbitalParticleNumber(8);
      planets.get(8).setName("Neptune");
      planets.get(8).color = Color.CYAN;
      frame.setPreferredMinMax(-5E12, 5E12, -5E12, 5E12);
    }
    if(control.getBoolean("Elastic Collisions") == true)
    {
      
    }
    
    panel = new ControlPanel(this);
    for (OrbitalParticle p : planets) {
      panel.planetsSelector.addItem(p);
    }

    panel.pack();
    panel.setVisible(true);

    frame.clearDrawables();
    for (int i = 0; i<planets.size(); i++) {
      OrbitalParticle p = planets.get(i);
      
      frame.addDrawable(p);
      
      speed.setMarkerColor(i, p.color);
      acceleration.setMarkerColor(i, p.color);
      
      Trail trail = new Trail();
      trail.color = p.color;
      trails.add(trail);
      
      Trail sptrail = new Trail();
      sptrail.setStroke(new BasicStroke(2));
      sptrail.color = p.color;
      speeds.add(sptrail);
      
      Trail actrail = new Trail();
      actrail.setStroke(new BasicStroke(2));
      actrail.color = p.color;
      accelerations.add(actrail);
      
      frame.addDrawable(trail);
      speed.addDrawable(sptrail);
      acceleration.addDrawable(actrail);
    }
    acceleration.setVisible(true);
    speed.setVisible(true);
    frame.setVisible(true);
  }

  public void reset() {

    control.setValue("Sun Mass", 2E30);
    control.setValue("Sun X Position", 0);
    control.setValue("Sun Y Position", 0);
    control.setValue("Sun X Velocity", 0);
    control.setValue("Sun Y Velocity", 0);
    control.setValue("Sun X Acceleration", 0);
    control.setValue("Sun Y Acceleration", 0);
    control.setValue("Sun Radius", 695800000);
    control.setValue("Earth Mass", 6E24);
    control.setValue("Earth X Position", 1.5E11);
    control.setValue("Earth Y Position", 0);
    control.setValue("Earth X Velocity", 0);
    control.setValue("Earth Y Velocity", 29747); // 29747 / 2.0
    control.setValue("Earth X Acceleration", 0);
    control.setValue("Earth Y Acceleration", 0);
    control.setValue("Earth Radius", 6371000);
    control.setValue("Number of Planets", 2);
    control.setValue("Time Step", 30000);
    control.setValue("Gravity Constant", 6.674E-11);
    control.setValue("Ellipse Error", 1E10);
    control.setValue("Binary Star", true);
    control.setValue("Solar System", false);
    control.setValue("Gravity Assist", false);
    //control.setValue("Elastic Collisions", false);
    control.setValue("Coefficient of Restitution", 1);
    
    numPlanets = control.getInt("Number of Planets");
    t = control.getDouble("Time Step");
    time = 0;
    gravityConstant = control.getDouble("Gravity Constant");
    distanceError = control.getDouble("Ellipse Error");
    elasticCollisions = control.getBoolean("Elastic Collisions");

    super.setDelayTime(0);

    frame.setInteractiveMouseHandler(new InteractiveMouseHandler() {

      @Override
      public void handleMouseAction(InteractivePanel panel2, MouseEvent evt) {
        panel2.handleMouseAction(panel2, evt);
        switch (panel2.getMouseAction()) {
        case InteractivePanel.MOUSE_DRAGGED:
          break;
        case InteractivePanel.MOUSE_PRESSED:
          placeX = panel2.getMouseX();
          placeY = panel2.getMouseY();
          break;
        case InteractivePanel.MOUSE_RELEASED:
          if (!evt.isAltDown() && evt.getButton() == MouseEvent.BUTTON1 && !evt.isControlDown()) {
            planets.add(new OrbitalParticle());
            int index = planets.size() - 1;
            planets.get(index).setMass(control.getDouble("Earth Mass"));
            planets.get(index).setX(placeX);
            planets.get(index).setY(placeY);
            planets.get(index)
                .setXvel((panel2.getMouseX() - placeX) / (t * 10));
            planets.get(index)
                .setYvel((panel2.getMouseY() - placeY) / (t * 10));
            planets.get(index).setXaccel(0);
            planets.get(index).setYaccel(0);
            planets.get(index).setRadius(control.getDouble("Earth Radius"));
            planets.get(index).setOrbitalParticleNumber(index);
            planets.get(index).setName("Planet " + index);
            planets.get(index).color = new Color(Color.HSBtoRGB(
                (float) Math.random(), 1, 1));
            trails.add(new Trail());
            trails.get(index).color = planets.get(index).color;
            frame.addDrawable(trails.get(index));
            frame.addDrawable(planets.get(index));
            panel.planetsSelector.addItem(planets.get(index));
            
            Trail actrail = new Trail();
            actrail.color = planets.get(index).color;
            actrail.setStroke(new BasicStroke(2));
            accelerations.add(actrail);
            acceleration.addDrawable(actrail);
            
            Trail sptrail = new Trail();
            sptrail.color = planets.get(index).color;
            sptrail.setStroke(new BasicStroke(2));
            speeds.add(sptrail);
            speed.addDrawable(sptrail);

            speed.setMarkerColor(index, planets.get(index).color);
            acceleration.setMarkerColor(index, planets.get(index).color);
          }
          break;
        }
      }
    });

  }

  public static void main(String[] args) {
    SimulationControl.createApp(new OrbitalGeneral());
  }

}