

public class LogisticClassifier extends LinearClassifier {
	
	public LogisticClassifier(int ninputs) {
		super(ninputs);
	}
	
	/**
	 * A LogisticClassifier uses the logistic update rule
	 * (AIMA Eq. 18.8): w_i \leftarrow w_i+\alpha(y-h_w(x)) \times h_w(x)(1-h_w(x)) \times x_i 
	 */
	public void update(double[] x, double y, double alpha) {
		
		for(int i = 0; i< this.weights.length;i++) {
			double thres = threshold(VectorOps.dot1(this.weights, x));
			
			this.weights[i] = this.weights[i] + alpha*(y-thres)*thres*(1-thres)*x[i];
			System.out.println(x[i] + " updating " + i + "th weight to " + this.weights[i]);
		}
	}
	
	/**
	 * A LogisticClassifier uses a 0/1 sigmoid threshold at z=0.
	 */
	public double threshold(double z) {
	    double denom = 1 + Math.pow(2.718, -1*z);
		return 1/denom;
	}

}
