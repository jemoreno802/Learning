//package dt.example;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import dt.core.*;

public class HouseVotesProblem extends Problem {
	public HouseVotesProblem() {
		super();
		Domain yesNoDomain = new Domain("y","n","?");
		//this.inputs.add(new Variable("ClassName", new Domain("democrat","republican")));
		this.inputs.add(new Variable ("handicapped-infants", yesNoDomain));
		this.inputs.add(new Variable ("WPCS", yesNoDomain));
		this.inputs.add(new Variable ("AotBR", yesNoDomain));
		this.inputs.add(new Variable ("physicianFee", yesNoDomain));
		this.inputs.add(new Variable ("el-salvador-aid", yesNoDomain));
		this.inputs.add(new Variable ("RGiS", yesNoDomain));
		this.inputs.add(new Variable ("AntiSatelliteTestBan", yesNoDomain));
		this.inputs.add(new Variable ("AidToNicaraguanContras", yesNoDomain));
		this.inputs.add(new Variable ("mx-missile", yesNoDomain));
		this.inputs.add(new Variable ("immigration", yesNoDomain));
		this.inputs.add(new Variable ("SCC", yesNoDomain));
		this.inputs.add(new Variable ("educationSpending", yesNoDomain));
		this.inputs.add(new Variable ("superfundRightToSue", yesNoDomain));
		this.inputs.add(new Variable ("crime", yesNoDomain));
		this.inputs.add(new Variable ("dutyFreeExports", yesNoDomain));
		this.inputs.add(new Variable ("EAASA", yesNoDomain));
		
		//Output Variable
		this.output = new Variable ("HousesVotes", new Domain("democrat","republican"));
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Problem problem = new HouseVotesProblem();
		problem.dump();
		Set<Example> examples = problem.readExamplesFromCSVFile(new File(args[0]));
		for (Example e : examples) {
			System.out.println(e);
		}
		DecisionTree tree = new DecisionTreeLearner(problem).learn(examples);
		tree.dump();
		tree.test(examples);
		
	}

}
