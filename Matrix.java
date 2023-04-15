/*
 * This class represents a matrix. The matrix has a certain number of rows and columns. 
 * If values are specified you can get elements, set elements, transpose, and multiply the matrix by a scalar or another matrix.
 * @author Jocelyn Chang
 */
public class Matrix {
/*
 * Number of rows in the matrix.
 */
	private int numRows;
/*
 * Number of columns in the matrix.
 */
	private int numCols;
/*
 * A double two-dimensional linear array of the numbers in the matrix.
 */
	private double[][] data;
	
/*
 * A constructor that creates an empty matrix with a given amount of rows and column.
 * @param r the number of rows in the matrix
 * @param c the number of columns in the matrix
 */
	public Matrix(int r, int c) {
		numRows = r;
		numCols = c;
		data = new double[numRows][numCols];
	}
	
/*
 * A constructor that creates a matrix with a given amount of rows and columns filled with the numbers in a given double one-dimensional linear array.
 * @param r the number of rows in the matrix
 * @param c the number of columns in the matrix
 * @param linArr the elements in the matrix
 */
	public Matrix(int r, int c, double[] linArr) {
		numRows = r;
		numCols = c;
		//empty matrix is first initialized
		data = new double[numRows][numCols];
		//loops through array and inserts numbers one by one
		int n = 0;
		for (int i = 0; i < data.length; i ++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = linArr[n];
				n++;
			}
		}
		
	}

/*
 * Getter method to get the number of rows in the matrix.
 * @return the number of rows in the matrix
 */
	public int getNumRows() {
		return numRows;
	}

/*
 * Getter method to get the number of columns in the matrix.
 * @return the number of columns in the matrix
 */
	public int getNumCols() {
		return numCols;
	}

/*
 * Getter method to get the data, all the numbers (elements) in the matrix.
 * @return the data of the matrix in a two-dimensional array
 */
	public double [][] getData() {
		return data;
	}

/*
 * Getter method to get an element, given it's row number and column number.
 * @param r row number of the element in the matrix
 * @param c column number of the element in the matrix
 * @return the element in the matrix
 */
	public double getElement(int r, int c) {
		return data[r][c];
	}
	
/*
 * Setter method to set an element, with a given index position, as a new value.
 * @param r the row number of the element in the matrix
 * @param c the column number of the element in the matrix
 * @param value the number replacing the previous element
 */
	public void setElement(int r , int c, double value) {
		data[r][c] = value;
	}

/*
 * Method to transpose the matrix and update the contents of the matrix to store a new transposed matrix.
 */
	public void transpose() {
		//initialize empty two-dimensional linear array to hold the transposed matrix data
		double[][] transposed = new double[numCols][numRows];
		for (int i = 0; i < transposed.length; i ++) {
			for (int j = 0; j < transposed[i].length; j++) {
				transposed[i][j] = data[j][i];
			}
		}
		//updates the variables and data of the original matrix to store the transposed values
		numRows = transposed.length;
		numCols = transposed[0].length;
		data = transposed;
	}
	
/*
 * Method to multiply a matrix by a scalar number and to return the resulting matrix.
 * @param scalar the number that the matrix is multiplied by
 * @return the resulting matrix
 */
	public Matrix multiply(double scalar) {
		//initializes a new empty matrix
		Matrix otherMatrix = new Matrix(this.data.length, this.data[0].length);
		//loops through each element in the data and multiplies it by the scalar
		for (int i = 0; i < this.data.length; i ++) {
			for (int j = 0; j < this.data[i].length; j++) {
				otherMatrix.data[i][j] = this.data[i][j]*scalar;
			}
		}
		return otherMatrix;
	}
	
/*
 * Method to multiply a matrix by another matrix to return the resulting matrix.
 * @param other another matrix that the first matrix is multiplied by
 * @return the resulting matrix
 */
	public Matrix multiply(Matrix other) {
		//returns an empty matrix if the two matrices aren't compatible to be multiplied together
		if (this.getNumCols() != other.getNumRows()) {
			Matrix otherMatrix = null;
			return otherMatrix;
		} else {
			//initializes matrix that will be returned
			Matrix otherMatrix = new Matrix(this.getNumRows(), other.getNumCols());
			//performs dot product for each row of the first matrix and column of the second matrix
			for (int a = 0; a < other.getNumCols(); a++) {
				for (int b = 0; b < this.getNumRows(); b++) {
					for (int c = 0; c < other.getNumRows(); c++) {
						otherMatrix.data[b][a] += this.data[b][c]*other.data[c][a];
					}
				}
			}
			return otherMatrix;
		}
	}

/*
 * Method to return a matrix data in a readable string format.
 * @return a string representation of the matrix data
 */
	public String toString() {
		String printForm = "";
		//checks if the data is empty
		if (data.length == 0) {
			return "Empty matrix";
		}
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				printForm += "\n";
			}
			for (int j = 0; j < data[i].length; j++) {
				printForm += String.format("%8.3f", data[i][j]);
			}
		}
		return printForm;
	}
}
