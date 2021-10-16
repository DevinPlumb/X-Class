package riemann;
import org.opensourcephysics.frames.DisplayFrame;
import org.opensourcephysics.display.DrawableShape;
import java.awt.Color;
    
public class RectangleTest
{

         public static void main(String args[])
         {
               /*First create a DisplayFrame or PlotFrame to put the rectangle on
                This code is the same as above
                */
                DisplayFrame df = new DisplayFrame("x","y","Drawing Shapes");
                df.setVisible(true);
                df.setDefaultCloseOperation(3);
        

                //Creates a rectangle with center (1.5, -1), width of 6.1, and height of 0.9
                DrawableShape myrect = DrawableShape.createRectangle(1.5,-1,6.1,0.9);  
                myrect.setMarkerColor(Color.blue,Color.green);  //changes the interior to blue and the border to green.   


               df.addDrawable(myrect);
         }

}