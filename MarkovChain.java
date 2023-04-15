/*
 * This class represents a Markov chain. It creates a matrix of predicted future states using a state vector and transition matrix.
 * @author Jocelyn Chang
 */
public class MarkovChain {
	
/*
 * Vector of the present states.
 */
	private Vector stateVector;
/*
 * Matrix of the probabilities of state to state transitions.
 */
	private Matrix transitionMatrix;
	
/*
 * A constructor that sets a Markov chain with a state vector and a transition Matrix.
 * @param sVector a vector of the present states
 * @param tMatrix a matrix of the probabilities of state to state transitions
 */
	public MarkovChain(Vector sVector, Matrix tMatrix) {
		stateVector = sVector;
		transitionMatrix = tMatrix;
		
	}
	
/*
 * Method that checks if the state vector and transition matrix are valid for the Markov chain problem.
 * Checks that the transition matrix is square and that the number of rows of the transition matrix matches the number of columns of the state vector.
 * Checks that the sum of values for each row of the transition matrix and sum of all the values of the state vector are around 1.0 each.
 * @return if it is invalid or valid
 */
	public boolean isValid() {
		double vectorSum = 0.0;
		//checking if the dimensions of the transition matrix and state vector are invalid to return false
		if ((transitionMatrix.getNumRows() != transitionMatrix.getNumCols())||(transitionMatrix.getNumRows() != stateVector.getNumCols())) {
			return false;
		} else {
			//calculates the sum of the elements in the vector
			for (int i = 0; i < stateVector.getNumCols(); i++) {
				vectorSum += stateVector.getElement(i);
			} 
			//checks if the sum is not close to 1.0 and returns false
			if ((vectorSum >= 1.01) || (vectorSum <= 0.99)) {
				return false;
			} else {
				//calculates the sum of the elements in each row and see if they are or are not close to 1.0
				for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
					double matrixSum = 0.0;
					for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
						matrixSum += transitionMatrix.getElement(i, j);
					}
					if ((matrixSum >= 1.01) || (matrixSum <= 0.99)) {
						return false;
					}
				}
				return true;
			}
		}
	}
	
/*
 * Method that computes the probability matrix after a given number of steps if it is not an invalid Markov chain.
 * Multiplies the transition matrix by itself for one less than the given number of times and then multiplies the state vector by the resulting matrix.
 * @return the probability matrix
 */
	public Matrix computeProbabilityMatrix(int numSteps) {
		Matrix resultingMatrix = transitionMatrix;
		if (this.isValid() == false) {
			return null;
		}
		for (int i = 0; i < numSteps-1; i++) {
			resultingMatrix = transitionMatrix.multiply(resultingMatrix);
		} 
		return stateVector.multiply(resultingMatrix);
	}
}
