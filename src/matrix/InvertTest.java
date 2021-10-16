package matrix;

import polyfun.Polynomial;

public class InvertTest 
{

        public static void main(String[] args) 
        {
//                Matrix matt = new Matrix (4,4); //matt is invertible
//                
//                int counter = 0;
//                
//                for(int i=0; i<3; i++) //Filling matt
//                {
//                        for(int j=0; j<3; j++)
//                        {
//                                counter++;
//                                matt.setEntry(i, j, counter);
//                        }
//                }
//                
//                matt.setEntry(2, 2, 10);
//                
//                matt.print(); //printing matt
//                
//                System.out.println("\n");
//                
//                matt.invert().print(); //printing the inverse of matt
//                
//                System.out.println("\n");
//                
//                matt.times(matt.invert()).print(); //printing the product of matt and his inverse
//                
//                System.out.println("\n");
//                
//                
//                Matrix trixie = new Matrix (4,4); //trixie is invertible
//                
//                for(int i=0; i<4; i++) //Filling trixie
//                {
//                        for(int j=0; j<4; j++)
//                        {
//                                if(i+j==2)
//                                        trixie.setEntry(i, j, i+1);
//                                else
//                                        trixie.setEntry(i, j, 0);
//                        }
//                }
//                
//                trixie.setEntry(3,3, 4);
//                
//                trixie.print(); //printing trixie
//                
//                System.out.println("\n");
//                
//                trixie.invert().print(); //printing the inverse of trixie
//                
//                
//                
//                System.out.println("\n");
//                
//                trixie.times(trixie.invert()).print(); //printing the product of trixie and her inverse
//                
                Matrix cortland = new Matrix (3,3);
                cortland.setEntry(0, 0, 0);
                cortland.setEntry(0, 1, 0);
                cortland.setEntry(0, 2, 1);
                cortland.setEntry(1, 0, 8);
                cortland.setEntry(1, 1, 0);
                cortland.setEntry(1, 2, 5);
                cortland.setEntry(2, 0, 0);
                cortland.setEntry(2, 1, 0);
                cortland.setEntry(2, 2, 2);
                
                cortland.print(); //printing cortland
                
                System.out.println("\n");
                
                cortland.invert().print(); //printing the inverse of cortland
                
                
        }
}