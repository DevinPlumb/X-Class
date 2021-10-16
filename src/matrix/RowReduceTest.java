package matrix;

public class RowReduceTest {

	public static void main(String[] args) 
	{
		Matrix alice = new Matrix (3,5);

		Matrix trixie = new Matrix (5, 5);

		Matrix amy = new Matrix (6, 10);
		
		Matrix annalisa = new Matrix (10, 10);

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

		for(int i=0; i<6; i++) //Filling amy
		{
			for(int j=0; j<10; j++)
			{
				if(i*j==6)
					amy.setEntry(i, j, i/j);
				else if(i+j==10)
					amy.setEntry(i, j, j-i);
				else if (j != 0) {
					if (i/j < 6)
						amy.setEntry(i, j, i*i/j);
				}
				else
					amy.setEntry(i, j, 0);
			}
		}
		
		for(int i=0; i<10; i++) //Filling annalisa
		{
			for(int j=0; j<10; j++)
			{
				if(i == 1 && j == 2) {
					annalisa.setEntry(i, j, 3);
				}
				else if (i == 9 && j == 8) {
					annalisa.setEntry(i, j, 5);
				}
				else
					annalisa.setEntry(i, j, 0);
			}
		}

		alice.setEntry(2,4, 7);

		alice.print(); //printing alice

		System.out.println("\n");

		alice.rowreduce().print(); //printing the Matrix which results when alice is row reduced

		System.out.println("\n");

		alice.print(); //printing alice again!

		System.out.println("\n");

		trixie.print(); //printing trixie

		System.out.println("\n");

		trixie.rowreduce().print(); //printing the Matrix which results when trixie is row reduced

		System.out.println("\n");

		trixie.print(); //printing trixie again!

		System.out.println("\n");

		amy.print(); //printing amy

		System.out.println("\n");

		amy.rowreduce().print(); //printing the Matrix which results when amy is row reduced

		System.out.println("\n");

		amy.print(); //printing amy again!

		System.out.println("\n");

		annalisa.print(); //printing annalisa

		System.out.println("\n");

		annalisa.rowreduce().print(); //printing the Matrix which results when annalisa is row reduced

		System.out.println("\n");

		annalisa.print(); //printing annalisa again!
		
		System.out.println("\n");
		
		//////////////////////////////////////////////
		
//		alice.print(); //printing alice
//
//		System.out.println("\n");
//
//		alice.invert().print(); //printing the Matrix which results when alice is row reduced
//
//		System.out.println("\n");
//
//		alice.print(); //printing alice again!
//
//		System.out.println("\n");

		trixie.print(); //printing trixie

		System.out.println("\n");

		trixie.invert().print(); //printing the Matrix which results when trixie is row reduced

		System.out.println("\n");

		trixie.print(); //printing trixie again!

		System.out.println("\n");

//		amy.print(); //printing amy
//
//		System.out.println("\n");
//
//		amy.invert().print(); //printing the Matrix which results when amy is row reduced
//
//		System.out.println("\n");
//
//		amy.print(); //printing amy again!
//
//		System.out.println("\n");

		annalisa.print(); //printing annalisa

		System.out.println("\n");

		annalisa.invert().print(); //printing the Matrix which results when annalisa is row reduced

		System.out.println("\n");

		annalisa.print(); //printing annalisa again!
	}

}