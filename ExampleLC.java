

import java.util.Arrays;

/**
 * An example is a vector of double values for the inputs and single
 * double value for the output.
 * Note that the first value of the vector should always be 1.0
 * (which is multiplied by the first weight, w_0, to give the
 * constant term in the function's expression. See AIMA p. 721.
 */
public class ExampleLC {
	
	public double[] inputs;
	public double output;
	
	public ExampleLC(double[] inputs, double output) {
		this.inputs = inputs;
		this.output = output;
	}
	
	public ExampleLC(int ninputs) {
		this(new double[ninputs], 0.0);
	}

	public String toString() {
		return Arrays.toString(inputs) + " -> " + output;
	}

}
