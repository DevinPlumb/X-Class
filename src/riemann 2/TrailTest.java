package riemann;
import org.opensourcephysics.frames.PlotFrame; //or frames.DisplayFrame if you use a DisplayFrame instead of a PlotFrame
import org.opensourcephysics.display.Trail; 

public class TrailTest
{
        public static void main(String[] args)
        {
                /*First create a DisplayFrame or PlotFrame to put the Trail on
                This code is the same as above
                */
                PlotFrame pf = new PlotFrame("x","y","Drawing Shapes");
                pf.setVisible(true);
                pf.setDefaultCloseOperation(3);

                Trail t = new Trail();  //instantiate a Trail object
                
                //The next four lines start the Trail at (0,0) and take it through the next 3 points, ending at (3,-2)
                t.addPoint(0, 0);  
                t.addPoint(1, 1);
                t.addPoint(2,-1.5);
                t.addPoint(3,-2);  

                t.moveToPoint(4,4);  //Breaks the Trail at the last point and starts a new one at (4,4)
                t.addPoint(5,5);  //Adds the point (5,5) to the new Trail

                t.closeTrail(); //connects the last point to the first and prevents more points from being added.

                pf.addDrawable(t); //adds the Trail to the PlotFrame pf so it will appear on the screen
        }
}