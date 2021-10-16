package matrix;

import polyfun.Polynomial;

public class VDMTest
{

        public static void main(String[] args) 
        {
                
                VDM vdm = new VDM();
                
                Polynomial poly1 = new Polynomial (new double[] {1, 5, 3, -2}); // -2x^3 + 3x^2 + 5x + 1
                
                Polynomial poly2 = new Polynomial (new double[] {5, -3, 7, 4, 0, 1}); // x^5 + 4x^3 + 7x^2 - 3x + 5
                
                Polynomial poly3 = new Polynomial (new double[] {0, 0, 1}); // x^2
                
                System.out.println(vdm.slopeAtPoint(poly1, 2.0));
                
                vdm.tangentLine(poly1, 2.0);
                
                vdm.slopeFunction(poly1);
                
                System.out.println("\n");
                
                System.out.println(vdm.slopeAtPoint(poly2, -5.0));
                
                vdm.tangentLine(poly2, -5.0);
                
                vdm.slopeFunction(poly2);
                
                System.out.println("\n");
                
                System.out.println(vdm.slopeAtPoint(poly3, 0.0));
                
                vdm.tangentLine(poly3, 0.0);
                
                vdm.slopeFunction(poly3);
        }
}
