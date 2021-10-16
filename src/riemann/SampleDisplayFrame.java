package riemann;
import org.opensourcephysics.frames.DisplayFrame;

public class SampleDisplayFrame
{
      public static void main(String[] args) 
      {

                DisplayFrame dframe = new DisplayFrame("x","y","Frame for Drawing Shapes");

                dframe.setVisible(true);  //makes sure the window appears when app is run, you should include this line most times when you use a DisplayFrame
                dframe.setDefaultCloseOperation(3);  //closes the entire app when you close the frame window
                dframe.setPreferredMinMax(-10,10,-10,10); //sets the window to span from -10 to 10 on both the x-axis and y-axis
                dframe.setMessage("This window is still empty"); //Display a message in the lower right hand corner of the DisplayFrame window.

                //This code chunk will create a blank coordinate grid with the axes and window labeled.  
      }
}