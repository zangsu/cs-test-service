package GDHS.server.dataclass;

public class Problem {
	String problem;
	String[] exmaple = new String[5];
	int correction;

	public Problem(String problem, String[] exmaple, int correction) {
		this.problem = problem;
		this.exmaple = exmaple;
		this.correction = correction;
	}

	public String getProblem() {
		return problem;
	}

	public String[] getExmaple() {
		return exmaple;
	}

	public int getCorrection() {
		return correction;
	}
}
