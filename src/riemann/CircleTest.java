package riemann;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.DisplayFrame; //or frames.PlotFrame if you use a PlotFrame instead of a DisplayFrame
import java.awt.Color;




public class CircleTest
{
         public static void main(String args[])
         {
                /*First create a DisplayFrame or PlotFrame to put the circle on
                This code is the same as above
                */
                DisplayFrame df = new DisplayFrame("x","y","Drawing Shapes");
                df.setVisible(true);
                df.setDefaultCloseOperation(3);

                Circle c = new Circle();
                c.setXY(3, -2);
                c.pixRadius=10;
                c.color=Color.blue;

                df.addDrawable(c);      //You'll need to instatiate an object of type DisplayFrame to see it on your screen
          }
}