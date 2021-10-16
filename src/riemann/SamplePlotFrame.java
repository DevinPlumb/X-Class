package riemann;
import org.opensourcephysics.frames.PlotFrame; 
 
 public class SamplePlotFrame 
 { 
         public static void main(String[] args) 
         {
 
                PlotFrame pframe = new PlotFrame("x","y","HW");
                pframe.setVisible(true);  //makes sure the window appears when app is run, you should include this line most times when you use a PlotFrame
                pframe.setDefaultCloseOperation(3);  //closes the entire app when you close the frame window
                pframe.setPreferredMinMax(-10,10,-10,10); //sets the window to span from -10 to 10 on both the x-axis and y-axis
                
                //data set index 1 appears in green
                pframe.append(1, 2, 3);
                pframe.append(1, 5, 6);
                pframe.append(1, 4, -8);
                
                // data set index 2 appears in blue
                pframe.append(2, 0, 3);
                pframe.append(2, 1, 4);

                pframe.setMarkerSize(1, 5);  //changes the size (in pixels) of the first data set (the green data set) to 5
                                
         }
}