package orbital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JFrame {

  public JComboBox planetsSelector;
  OrbitalGeneral orbital;
  
  public JTextField massText;
  public JTextField radText;
  public JTextField xText;
  public JTextField yText;
  public JTextField vxText;
  public JTextField vyText;
  
  public ControlPanel(final OrbitalGeneral orbital) {
    super("Menu");
    
    this.orbital = orbital;
    
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
    
    planetsSelector = new JComboBox();
    planetsSelector.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        orbital.selected = (OrbitalParticle) planetsSelector.getSelectedItem();
        massText.setText(Double.toString(orbital.selected.getMass()));
        radText.setText(Double.toString(orbital.selected.pixRadius));
        xText.setText(Double.toString(orbital.selected.getX()));
        yText.setText(Double.toString(orbital.selected.getY()));
        vxText.setText(Double.toString(orbital.selected.getXvel()));
        vyText.setText(Double.toString(orbital.selected.getYvel()));
      }
    });
    this.add(planetsSelector);
    this.add(Box.createHorizontalGlue());
    
 // Mass
    JPanel massPanel = new JPanel();
    massPanel.setLayout(new BoxLayout(massPanel, BoxLayout.LINE_AXIS));
    massPanel.add(new JLabel("Mass: "));

    massText = new JTextField();
    massText.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.setMass(Double.parseDouble(massText.getText()));
                } catch (Exception e1) {
                    massText.setText(Double.toString(orbital.selected.getMass()));
                }
            }
        }
    });
    massPanel.add(massText);
    this.add(massPanel);
    this.add(Box.createHorizontalGlue());

    // Radius
    JPanel radPanel = new JPanel();
    radPanel.setLayout(new BoxLayout(radPanel, BoxLayout.LINE_AXIS));
    radPanel.add(new JLabel("Radius: "));

    radText = new JTextField();
    radText.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.pixRadius = Integer.parseInt(radText.getText());
                } catch (Exception e1) {
                    radText.setText(Double.toString(orbital.selected.pixRadius));
                }
            }
        }
    });
    radPanel.add(radText);
    this.add(radPanel);
    this.add(Box.createHorizontalGlue());

    // Position
    JPanel posPanel = new JPanel();
    posPanel.setLayout(new BoxLayout(posPanel, BoxLayout.LINE_AXIS));

    // X
    posPanel.add(new JLabel("X: "));

    xText = new JTextField();
    xText.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.setX(Double.parseDouble(xText
                            .getText()));
//                    orbital.selected.path.reset();
//                    orbital.selected.path.moveTo(orbital.selected.position.x, orbital.selected.position.y);
                } catch (Exception e1) {
                    xText.setText(Double.toString(orbital.selected.getX()));
                }
            }
        }
    });
    posPanel.add(xText);

    // Y
    posPanel.add(new JLabel("Y: "));

    yText = new JTextField();
    yText.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.setX(Double.parseDouble(yText
                            .getText()));
                    //orbital.selected.path.reset();
                    //orbital.selected.path.moveTo(orbital.selected.position.x, orbital.selected.position.y);
                } catch (Exception e1) {
                    yText.setText(Double.toString(orbital.selected.getY()));
                }
            }
        }
    });
    posPanel.add(yText);

    this.add(posPanel);
    this.add(Box.createHorizontalGlue());

    // Velocity
    JPanel velPanel = new JPanel();
    velPanel.setLayout(new BoxLayout(velPanel, BoxLayout.LINE_AXIS));

    // X
    velPanel.add(new JLabel("VX: "));

    vxText = new JTextField();
    vxText.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.setXvel(Double.parseDouble(vxText
                            .getText()));
                } catch (Exception e1) {
                    vxText.setText(Double.toString(orbital.selected.getXvel()));
                }
            }
        }
    });
    velPanel.add(vxText);

    // Y
    velPanel.add(new JLabel("VY: "));

    vyText = new JTextField();
    vyText.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (orbital.selected != null) {
                try {
                    orbital.selected.setYvel(Double.parseDouble(vyText
                            .getText()));
                } catch (Exception e1) {
                    vyText.setText(Double.toString(orbital.selected.getYvel()));
                }
            }
        }
    });
    velPanel.add(vyText);

    this.add(velPanel);
    this.add(Box.createHorizontalGlue());
    
  }

}