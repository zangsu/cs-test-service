package GDHS.server.dataclass;

public class Problem {
	String problem;
	String[] exmaple = new String[5];
	String correction;

	public Problem(String problem, String[] exmaple, String correction) {
		this.problem = problem;
		this.exmaple = exmaple;
	}

	public String getProblem() {
		return problem;
	}

	public String[] getExmaple() {
		return exmaple;
	}

	public String getCorrection() {
		return correction;
	}
}
