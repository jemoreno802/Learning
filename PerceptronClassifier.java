
public class PerceptronClassifier extends LinearClassifier {
	
	public PerceptronClassifier(int ninputs) {
		super(ninputs);
	}
	
	/**
	 * A PerceptronClassifier uses the perceptron learning rule
	 * (AIMA Eq. 18.7): w_i \leftarrow w_i+\alpha(y-h_w(x)) \times x_i 
	 */
	public void update(double[] x, double y, double alpha) {
	    // Must be implemented by you
		double w[] = this.weights;
		for(int i = 0; i< w.length;i++) {
			double thres = threshold(VectorOps.dot1(w, x));
			w[i] = w[i] + alpha*(y-thres)*x[i];
			System.out.println(x[i] + "updating " + i + "th weight to " + w[i]);
		}
	}
	
	
	/**
	 * A PerceptronClassifier uses a hard 0/1 threshold.
	 */
	public double threshold(double z) {
		if(z >= 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
