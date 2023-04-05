package GDHS.server.dto;

import GDHS.server.dataclass.Problem;

public class ProblemDTO {
	String problem;
	String[] example;

	public ProblemDTO(Problem problem) {
		this.problem = problem.getProblem();
		this.example = problem.getExmaple();
	}

	public String getProblem() {
		return problem;
	}

	public String[] getExample() {
		return example;
	}
}
