package matrix;

public class ZeroFillTest {

	public static void main(String[] args) 
    {
            Matrix alice = new Matrix (3,5);
            
            Matrix trixie = new Matrix (5, 5);
            
            for(int i=0; i<3; i++) //Filling alice
            {
                    for(int j=0; j<5; j++)
                    {
                            if(i+j==2)
                                    alice.setEntry(i, j, i+1);
                            else if(i+j==4)
                                    alice.setEntry(i, j, j+1);
                            else
                                    alice.setEntry(i, j, 0);
                    }
            }
            
            for(int i=0; i<5; i++) //Filling trixie
            {
                    for(int j=0; j<5; j++)
                    {
                            if(i+j==2)
                                    trixie.setEntry(i, j, i+1);
                            else if(i+j==4)
                                    trixie.setEntry(i, j, j+1);
                            else if (i+j>4)
                            		trixie.setEntry(i, j, i-j);
                            else
                                    trixie.setEntry(i, j, 0);
                    }
            }
            
            alice.setEntry(2,4, 7);
            
            alice.print(); //printing alice
            
            System.out.println("\n");
            
            alice.columnreduce(0).print();
            
            System.out.println("\n");
            
            alice.print(); //printing alice again!
            
            System.out.println("\n");
            
            trixie.print(); //printing trixie
            
            System.out.println("\n");
            
            trixie.columnreduce(0).print(); //printing the Matrix which results when trixie is row reduced
            
            System.out.println("\n");
            
            trixie.print(); //printing trixie again!
    }
	
}
