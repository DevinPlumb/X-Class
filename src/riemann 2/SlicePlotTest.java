package riemann;
import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

public class SlicePlotTest 
{
        public static void main(String[] args) 
        {
                LeftHandRule LHR = new LeftHandRule();
                RightHandRule RHR = new RightHandRule();
                TrapezoidRule TR = new TrapezoidRule();
                MidpointRule MPR = new MidpointRule();
                MinimumRule MinR = new MinimumRule();
                MaximumRule MaxR = new MaximumRule();
                RandomRule RanR = new RandomRule();
                SimpsonRule SimR = new SimpsonRule();
        
                Polynomial p = new Polynomial(new double[] {1, 2, 0, 5}); // p=5x^3+2x+1
                        
                PlotFrame f = new PlotFrame("x","y","Left Hand Slice");                  
                f.setLocation(0,0);
                f.setVisible(true);
                        
                PlotFrame g = new PlotFrame("x","y","Right Hand Rule Slice");
                g.setLocation(300,0); 
                g.setVisible(true);
                
                PlotFrame h = new PlotFrame("x","y","Trapezoid Rule Slice");
                h.setLocation(600,0);
                h.setVisible(true); 
                
                PlotFrame i = new PlotFrame("x","y","Midpoint Rule Slice");
                i.setLocation(900,0); 
                i.setVisible(true);
                
                PlotFrame j = new PlotFrame("x","y","Minimum Rule Slice");
                j.setLocation(0,400);
                j.setVisible(true);
                
                PlotFrame k = new PlotFrame("x","y","Maximum Rule Slice");
                k.setLocation(300, 900);
                k.setVisible(true);
                
                PlotFrame l = new PlotFrame("x","y","Random Rule Slice");
                l.setLocation(600,900);
                l.setVisible(true);
                
                PlotFrame m = new PlotFrame("x","y","Simpson's Rule Slice");
                m.setLocation(900,400);
                m.setVisible(true);
                
//                LHR.slicePlot(f, p, 0, 1);
//                
//                RHR.slicePlot(g, p, 0, 1);
//                
//                TR.slicePlot(h, p, 0, 1);
//                
//                MPR.slicePlot(i, p, 0, 1);
//                
//                MinR.slicePlot(j, p, 0, 1);
//                
//                MaxR.slicePlot(k, p, 0, 1);
//                
//                RanR.slicePlot(l, p, 0, 1);
//                
//                SimR.slicePlot(m, p, 0, 1);
                
                
                LHR.rsPlot(f, p, 1, .1, -10, 10, 10);
                RHR.rsPlot(g, p, 1, .1, -10, 10, 10);
                TR.rsPlot(h, p, 1, .1, -10, 10, 10);
                MPR.rsPlot(i, p, 1, .1, -10, 10, 10);
                MinR.rsPlot(j, p, 1, .1, -10, 10, 10);
                MaxR.rsPlot(k, p, 1, .1, -10, 10, 10);
                RanR.rsPlot(l, p, 1, .1, -10, 10, 10);
                SimR.rsPlot(m, p, 1, .1, -10, 10, 10);
                
                
        }
}