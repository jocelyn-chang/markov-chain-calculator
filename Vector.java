/*
 * This class represents a vector. The vector inherits methods from the Matrix class. 
 * The vector has one row with a given number of columns.
 * If given elements to store, it is possible to get elements from the data.
 * @author Jocelyn Chang
 */
public class Vector extends Matrix {
	
/*
 * A constructor that creates an empty vector with a given amount of columns.
 * @param c the number of columns in the vector
 */
	public Vector(int c) {
		super(1, c);
	}
	
/*
 * A constructor that creates a vector with a given amount of columns and fills it with the elements from a one-dimensional linear array.
 * @param c the number of columns in the vector
 * @param linArr the elements in the vector
 */
	public Vector(int c, double[] linArr) {
		super(1, c, linArr);
	}

/*
 * Getter method that will get the element given it's column number.
 * @param c the column position of the element
 * @return the element in the vector
 */
	public double getElement(int c) {
		return getElement(0, c);
	}
}
