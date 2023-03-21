package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.Problem;

public class ProblemRepository {
	private static Map<Integer, Problem> problemMap = new HashMap<>();
	private static ProblemRepository probleminstance = new ProblemRepository();

	private ProblemRepository() {
		String problem1 = "1번문제";
		String[] example1 = {"1번 보기", "2번 보기", "3번 보기", "4번 보기", "5번 보기"};
		int correction1 = 1;
		String problem2 = "2번문제";
		String[] example2 = {"1번 보기", "2번 보기", "3번 보기", "4번 보기", "5번 보기"};
		int correction2 = 2;
		String problem3 = "3번문제";
		String[] example3 = {"1번 보기", "2번 보기", "3번 보기", "4번 보기", "5번 보기"};
		int correction3 = 3;
		String problem4 = "4번문제";
		String[] example4 = {"1번 보기", "2번 보기", "3번 보기", "4번 보기", "5번 보기"};
		int correction4 = 4;
		String problem5 = "5번문제";
		String[] example5 = {"1번 보기", "2번 보기", "3번 보기", "4번 보기", "5번 보기"};
		int correction5 = 5;

		problemMap.put(1, new Problem(problem1, example1, correction1));
		problemMap.put(2, new Problem(problem2, example2, correction2));
		problemMap.put(3, new Problem(problem3, example3, correction3));
		problemMap.put(4, new Problem(problem4, example4, correction4));
		problemMap.put(5, new Problem(problem5, example5, correction5));
	}

	public static ProblemRepository getProblemInstance(){
		return probleminstance;
	}

	public Problem getProblem(int problemNumber){
		return problemMap.get(problemNumber);
	}
}
