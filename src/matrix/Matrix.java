package matrix;

/**
 * This class is comprised of a series of functions that manipulate matrices. These methods are used in other classes to calculate the slope
 * of different functions at different x values and to graph the slope of all x values. They are also used to interpolate different points.
 * I focused on methods like removeRow and removeColumn, which seemed unique to me and which were used to not have extraneous empty rows.
 * Also the multiple methods used to row reduce and invert made the class more efficient.
 * 
 * @author Devin Plumb
 *
 */

public class Matrix {
	
	public double[][] matrix;
	int rows; // number of rows
	int columns; // number of columns
	
	/**
	 * Sets the dimensions (the number of rows and columns) of a matrix, makes it object
	 * @param i number of rows
	 * @param j	number of columns
	 */

	public Matrix (int i, int j) {
		matrix = new double[i][j]; // sets values of double matrix
		rows = i; // sets number of rows
		columns = j; // sets number of columns
	}
	
	/**
	 * Sets the entry value of a particular spot in a matrix
	 * @param i	row number
	 * @param j	column value
	 * @param d	new index value
	 */

	public void setEntry(int i, int j, double d) {
		this.matrix[i][j] = d; // sets value to input
	}
	
	/**
	 * Copies a matrix by setting rows and columns equal and copying all values
	 * @return copy		Copied matrix
	 */

	public Matrix copy () {
		Matrix copy = new Matrix(this.rows, this.columns); // same dimensions
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				copy.matrix[i][j] = this.matrix[i][j]; // same entry value
			}
		}
		return copy;
	}
	
	/**
	 * Prints out every index in a matrix, creating a visual display
	 */

	public void print() {
		// prints a matrix. For example, trixie.print() prints the matrix trixie to the screen.
		for (int i=0; i<this.rows; i++) {
			System.out.print("|"); // left line
			for (int j=0; j<this.columns; j++) {
				if (this.matrix[i][j] == 0) System.out.print(" " + 0 + " |"); // makes sure no entry appears as -0.0
				else System.out.print(" " + this.matrix[i][j] + " |"); // right line
			}
			System.out.println(); // breaks for new row
		}
	}
	
	/**
	 * Adds two matrices and returns the matrix that is the sum
	 * @param alice		Matrix added to original matrix
	 * @return plus		New matrix that is sum
	 */

	public Matrix plus (Matrix alice) {
		// adds two matrices. For example, trixie.plus(alice) is the sum of the matrices trixie and alice.
		Matrix plus = new Matrix(this.rows, this.columns); // same dimensions
		for (int i=0; i<this.rows; i++) {
			for (int j=0; j<this.columns; j++) {
				plus.matrix[i][j] = alice.matrix[i][j] + this.matrix[i][j]; // each entry is old entries added
			}
		}
		return plus;
	}
	
	/**
	 * Multiplies two matrices and returns the matrix that is the product; original matrix is the first in multiplication process
	 * @param alice		Matrix multiplied by original matrix
	 * @return times	New matrix that is product
	 */

	public Matrix times (Matrix alice) {
		// multiplies two matrices. For example, trixie.times(alice) is the product of the matrices trixie and alice.
		Matrix times = new Matrix(this.rows, alice.columns); // original rows, new columns
		for (int r=0; r<this.rows; r++) { // matrix dimension
			for (int i=0; i<alice.columns; i++) { // matrix dimension
				times.matrix[r][i] = 0;
				for (int j=0; j<alice.rows; j++) { // alice.rows = this.columns by definition, this for loop creates value of one entry
					times.matrix[r][i] += this.matrix[r][j] * alice.matrix[j][i]; 
				}
			}
		}
		return times;
	}
	
	/**
	 * Multiplies a row by a scalar and returns a new matrix with the multiplied row
	 * @param scalar			Scalar used to multiply
	 * @param rownumber			Row that is multiplied by scalar
	 * @return scalarTimesRow	New matrix with multiplied row 
	 */

	public Matrix scalarTimesRow (double scalar, int rownumber) {
		// multiplies a scalar times a row. For example, trixie.scalarTimesRow(2.0,3) multiplies row three of trixie by two.
		Matrix scalarTimesRow = new Matrix(this.rows, this.columns); // original dimensions
		for (int i=0; i<this.rows; i++) {
			for (int j=0; j<this.columns; j++) {
				if (i==rownumber) { // row is multiplied
					scalarTimesRow.matrix[i][j] = scalar*this.matrix[i][j];
				}
				else scalarTimesRow.matrix[i][j] = this.matrix[i][j]; // other rows left intact
			}
		}
		return scalarTimesRow;
	}
	
	/**
	 * Switches two rows and returns a new matrix with the switched rows
	 * @param firstrow		First row used to switch 
	 * @param secondrow		Second row used to switch
	 * @return switchRows	New matrix with switched rows 
	 */

	public Matrix switchRows (int firstrow, int secondrow) {
		// switches two rows of a matrix. For example, trixie.switchRows(3,5) exchanges rows three and five of the matrix trixie.
		Matrix switchRows = new Matrix(this.rows, this.columns); // original dimensions
		for (int i=0; i<this.rows; i++) {
			for (int j=0; j<this.columns; j++) {
				if (i==secondrow) {
					switchRows.matrix[firstrow][j] = this.matrix[secondrow][j]; // switches individual entry
				}
				else if (i==firstrow) {
					switchRows.matrix[secondrow][j] = this.matrix[firstrow][j]; // switches individual entry
				}
				else switchRows.matrix[i][j] = this.matrix[i][j];
			}
		}
		return switchRows;
	}
	
	/**
	 * Switches two columns and returns a new matrix with the switched columns
	 * @param firstcolumn		First column used to switch 
	 * @param secondcolumn		Second column used to switch
	 * @return switchColumns	New matrix with switched column 
	 */
	
	public Matrix switchColumns (int firstcolumn, int secondcolumn) {
		// switches two columns of a matrix. For example, trixie.switchColumns(3,5) exchanges columns three and five of the matrix trixie.
		Matrix switchColumns = new Matrix(this.rows, this.columns); // original dimensions
		for (int j=0; j<this.columns; j++) {
			for (int i=0; i<this.rows; i++) {
				if (j==secondcolumn) {
					switchColumns.matrix[i][firstcolumn] = this.matrix[i][secondcolumn]; // switches individual entry
				}
				else if (j==firstcolumn) {
					switchColumns.matrix[i][secondcolumn] = this.matrix[i][firstcolumn]; // switches individual entry
				}
				else switchColumns.matrix[i][j] = this.matrix[i][j];
			}
		}
		return switchColumns;
	}
	
	/**
	 * Takes a row and adds to it another row multiplied by a scalar value
	 * 
	 * @param scalar			Scalar that multiplies the first row
	 * @param firstrow			Row multiplied by scalar and then added
	 * @param secondrow			Row added on to by multiple of first row
	 * @return linearCombRows	New matrix that is result of process
	 */

	public Matrix linearCombRows (double scalar, int firstrow, int secondrow) {
		// adds a scalar multiple of the first row to the second row. For example, trixie.linearCombRows(.5,3,2) adds .5 times row three to row two.
		Matrix linearCombRows = this.copy(); // copies original matrix
		for (int i=0; i<linearCombRows.rows; i++) {
			for (int j=0; j<linearCombRows.columns; j++) {
				if (i==firstrow) {
					linearCombRows.matrix[secondrow][j] += scalar*linearCombRows.matrix[firstrow][j]; // only if proper row, adds to it
				}
			}
		}
		return linearCombRows;
	}
	
	/**
	 * removes a single row from anywhere in a Matrix
	 * 
	 * @param row			The number of the row that is removed
	 * @return removeRow	New matrix that is result of process
	 */

	public Matrix removeRow(int row) {
		Matrix moveToEnd = this.copy(); // copy of original
		for (int j=row; j<moveToEnd.rows-1; j++) { // until end of rows, stops one early because 1 early switches with normal ending
			moveToEnd = moveToEnd.switchRows(j, j+1); // moves row down by one
		}
		Matrix removeRow = new Matrix(moveToEnd.rows-1, moveToEnd.columns); // 1 fewer row
		for (int i=0; i<removeRow.rows; i++) {
			for (int j=0; j<removeRow.columns; j++) {
				removeRow.matrix[i][j] = moveToEnd.matrix[i][j]; // with row that is to be removed at the end of matrix, it is not reached
			}
		}
		return removeRow;
	}
	
	/**
	 * removes a single column from anywhere in a Matrix
	 * 
	 * @param column		The number of the column that is removed
	 * @return removeColumn	New matrix that is result of process
	 */
	
	public Matrix removeColumn(int column) {
		Matrix moveToEnd = this.copy(); // copy of original
		for (int j=column; j<moveToEnd.columns-1; j++) { // until end of columns, stops one early because 1 early switches with normal ending
			moveToEnd = moveToEnd.switchColumns(j, j+1); // moves column down by one
		}
		Matrix removeColumn = new Matrix(moveToEnd.rows, moveToEnd.columns-1); // 1 fewer column
		for (int i=0; i<removeColumn.rows; i++) {
			for (int j=0; j<removeColumn.columns; j++) {
				removeColumn.matrix[i][j] = moveToEnd.matrix[i][j]; // with column that is to be removed at the end of matrix, it is not reached
			}
		}
		return removeColumn;
	}
	
	/**
	 * takes away all empty columns and rows
	 * 
	 * @return removeRow	New matrix that is result of process
	 */
	
	public Matrix minimized() {
		Matrix minimized = this.copy(); // copy of original
		
		boolean[] emptyRows = new boolean[minimized.rows]; // goes through each row to check that it is not empty
		for (int i=0; i<minimized.rows; i++) {
			emptyRows[i] = true; // assumes it is empty
			for (int j=0; j<minimized.columns; j++) {
				if (minimized.matrix[i][j] != 0) { // if not empty
					emptyRows[i] = false; // label as such
				}
			}
			if (emptyRows[i] == true) { // check if labeled as not empty, if not:
				minimized = minimized.removeRow(i); // remove the row because it is empty
				i--; // don't move forward
			}
		}
		
		boolean[] emptyColumns = new boolean[minimized.columns]; // goes through each column to check that it is not empty
		for (int j=0; j<minimized.columns; j++) {
			emptyColumns[j] = true; // assumes it is empty
			for (int i=0; i<minimized.rows; i++) {
				if (minimized.matrix[i][j] != 0) { // if not empty
					emptyColumns[j] = false; // label as such
				}
			}
			if (emptyColumns[j] == true) { // check if labeled as not empty, if not:
				minimized = minimized.removeColumn(j); // remove the column because it is empty
				j--; // don't move forward
			}
		}
		
		return minimized;
	}
	
	/**
	 * does the process of row reduction to a single column in a matrix, filling it with 0s and putting the 1 in the correct spot while
	 * doing the necessary other processes to other columns
	 * 
	 * @param column		The number of the column that is reduced
	 * @return removeRow	New matrix that is result of process
	 */

	public Matrix columnreduce (int column) { 
		Matrix columnreduce = this.minimized().copy(); // minimizes and then copies
		double[] pivot = new double[2]; // the index and the value
		pivot[0] = 0; // index, assumed 0
		pivot[1] = 0; // value, assumed 0
		for (int i = column; i < columnreduce.rows; i++) { // starts at ideal index of pivot, runs to end
			if (columnreduce.matrix[i][column] != 0) { // finds first non-zero value
				pivot[0] = i; // sets index to value
				pivot[1] = columnreduce.matrix[i][column]; // sets value to value
				break; // stops, having found the first non-zero value
			}
		}
		if (pivot[0] > column) columnreduce = columnreduce.switchRows((int) pivot[0], column); // switches pivot row with place where pivot should be
		if (pivot[1] != 0) {
			columnreduce = columnreduce.scalarTimesRow(1/pivot[1], column); // turns pivot to 1, multiplying entire row in process
		}
		
		for (int i = 0; i < columnreduce.rows; i++) {
			if (columnreduce.matrix[i][column] != 0 && i != column) { // if there is a non-zero value where a 0 should be:
				columnreduce = columnreduce.linearCombRows(-columnreduce.matrix[i][column], column, i); // add a value to it such that it equals 0
			}
		}

		return columnreduce;
	}
	
	/**
	 * does the process of reducing a matrix column by column, using columnreduce() for each column
	 * 
	 * @return rowreduce	Row reduced matrix
	 */

	public Matrix rowreduce() {
		// row reduces a matrix. For example, trixie.rowreduce().print() should print the row reduction of trixie. rowreduce should work for all matrices.
		Matrix rowreduce = this.minimized().copy(); // takes a minimized version of the matrix
		
		double limit = 0;
		if (rowreduce.rows >= rowreduce.columns) limit = rowreduce.columns; // does not row reduce past the number of columns
		else limit = rowreduce.rows; // unless there are more columns than rows, in which case does not reduce past the number of rows

		for (int column = 0; column < limit; column++) { // runs through each column up until limit and reduces using previous function
			rowreduce = rowreduce.columnreduce(column); // calls previous method to put 0s in each column up until limit
		}

		return rowreduce;
	}
	
	/**
	 * inverts symmetrical matrices
	 * 
	 * @return invert	inverted matrix
	 */
	
	public Matrix invert() {
		// inverts a matrix. For example, trixie.invert().print() should print the inverse matrix of trixie. invert should work for square matrices which are invertible.
		Matrix min = this.minimized().copy(); // minimized copy of original matrix
		Matrix appended = new Matrix(min.rows, 2*min.columns); // puts second equally dimensioned matrix on the side
		if (min.rows > min.columns || min.rows < min.columns) { // if not symmetrical
			System.out.println("N/A"); // cannot operate
			return null; // probable error, not a problem for VDM or Interpolation
		}
		for (int i = 0; i < appended.rows; i++) {
			for (int j = 0; j < appended.columns; j++) {
				if (j<min.columns) {
					appended.setEntry(i, j, min.matrix[i][j]); // sets value to original matrix
				}
				else if (j-min.columns == i) {
					appended.setEntry(i, j, 1); // sets value of right half of matrix to identity
				}
				else {
					appended.setEntry(i, j, 0); // sets value of right half of matrix to identity
				}
			}
		}
		
		Matrix invert = appended.rowreduce(); // by row reducing, turns right half into inverted matrix
		
		for (int j=0; j<min.columns; j++) {
			invert = invert.removeColumn(0); // takes away entire left half of matrix, removing identity and leaving behind inverted matrix
		}
		
		return invert;
	}

}
