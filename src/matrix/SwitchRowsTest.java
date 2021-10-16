package matrix;

public class SwitchRowsTest {

    public static void main(String[] args) 
    {
            Matrix trixie = new Matrix (4,4); 
            
            for(int i=0; i<4; i++) //Filling trixie
            {
                    for(int j=0; j<4; j++)
                    {
                            if(i+j==2)
                                    trixie.setEntry(i, j, i+1);
                            else
                                    trixie.setEntry(i, j, 0);
                    }
            }
            
            trixie.setEntry(3,3, 4);
            
            trixie.print(); //printing trixie
            
            System.out.println("\n");
            
            trixie.switchRows(1,3).print(); //printing the Matrix which results when rows 1 and 3 of trixie are exchanged
            
            System.out.println("\n");
            
            trixie.switchRows(0,1).switchRows(1, 2).switchRows(2, 3).print();
            
            System.out.println("\n");
            
            trixie.switchColumns(0,1).switchColumns(1, 2).switchColumns(2, 3).print();
            
            System.out.println("\n");
            
            trixie.print(); //printing trixie again!
    }

}