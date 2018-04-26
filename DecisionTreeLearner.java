//package dt.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import dt.example.*;
import dt.util.ArraySet;

/**
 * Implementation of the decision-tree learning algorithm in AIMA Fig 18.5.
 * This is based on ID3 (AIMA p. 758).
 */
public class DecisionTreeLearner extends AbstractDecisionTreeLearner {
	
	/**
	 * Construct and return a new DecisionTreeLearner for the given Problem.
	 */
	public DecisionTreeLearner(Problem problem) {
		super(problem);
	}
	
	/**
	 * Main recursive decision-tree learning (ID3) method.  
	 */

	@Override
	protected DecisionTree learn(Set<Example> examples, List<Variable> attributes, Set<Example> parent_examples) {
	    // Must be implemented by you; the following two methods may be useful
		DecisionTree tree = null;
		if (examples.isEmpty()) {
			return new DecisionTree(pluralityValue(parent_examples));
		}
		else if(uniqueOutputValue(examples)!=null) {
			return new DecisionTree(uniqueOutputValue(examples));
		}
		else if(attributes.isEmpty()) {
			return new DecisionTree(pluralityValue(examples));
		}
		else {
			Variable A = mostImportantVariable(attributes, examples);
			tree = new DecisionTree(A);
			Domain domain = A.domain;
			for (String vk: domain){
				Set <Example> exs = new HashSet<Example>();
				exs.addAll(examplesWithValueForAttribute(examples, A, vk));	
				List <Variable> newAttributes = new ArrayList<Variable>();
				newAttributes.addAll(attributes);
				newAttributes.remove(A); //May be cause of error
				DecisionTree subtree = learn (exs, newAttributes, examples);
				tree.children.add(subtree);
			}
		}
		
		return tree;
		
	}
	
	/**
	 * Returns the most common output value among a set of Examples,
	 * breaking ties randomly.
	 * I don't do the random part yet.
	 */
	@Override
	protected String pluralityValue(Set<Example> examples) {
		HashMap <String, Integer> outputVals = new HashMap <String, Integer>();
		int highest = 1;
		String retval = null;
		
		//Iterate over the set of examples and save all possible output value and the # of times they occur
		for(Example e:examples){
			String val = e.getOutputValue();
			if(!outputVals.containsKey(val)){
				outputVals.put(val, 1);
			} else {
				outputVals.replace(val,outputVals.get(val)+1);
			}
		}
		
		//Check which output value occurs the most
		for (String output: outputVals.keySet()){
			if (outputVals.get(output) > highest) {
				retval = output;
				highest = outputVals.get(output);
			}
				
		}
		return retval;
	}
	
	/**
	 * Returns the single unique output value among the given examples
	 * is there is only one, otherwise null.
	 */
	@Override
	protected String uniqueOutputValue(Set<Example> examples) {
		Iterator<Example>iterator = examples.iterator();
		 
		Example e = (Example) iterator.next();
		String output = e.getOutputValue();
		
		if(countExamplesWithValueForOutput(examples,output) == examples.size())
			return output;
		else
			return null;
		
	}
	
	//
	// Utility methods required by the AbstractDecisionTreeLearner
	//

	/**
	 * Return the subset of the given examples for which Variable a has value vk.
	 */
	@Override
	protected Set<Example> examplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {
		Set<Example> subset = new HashSet<Example>();
		for (Example e : examples) {
			if (e.getInputValue(a).equals(vk)) {
				subset.add(e);
			}
		}
		return subset;
	}
	
	/**
	 * Return the number of the given examples for which Variable a has value vk.
	 */
	@Override
	protected int countExamplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {
		int result = 0;
		for (Example e : examples) {
			if (e.getInputValue(a).equals(vk)) {
				result += 1;
			}
		}
		return result;
		
	}

	/**
	 * Return the number of the given examples for which the output has value vk.
	 */
	@Override
	protected int countExamplesWithValueForOutput(Set<Example> examples, String vk) {
	    int result = 0;
	    for(Example e: examples){
	    	if (e.getOutputValue().equals(vk)){
	    		result += 1;
	    	}
	    		
	    }
	    return result;
	}
	
	

}
