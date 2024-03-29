package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.Problem;

public class ProblemRepository {
	private static Map<Integer, Problem> problemMap = new HashMap<>();
	private static ProblemRepository probleminstance = new ProblemRepository();

	private ProblemRepository() {
		String problem1 = "다음 중 쿠키에 대한 설명으로 틀린 것을 고르세요.";
		String[] example1 = {
				"쿠키는 클라이언트에게 저장이 된다.",
				"쿠키를 생성하는 대상은 서버이다.",
				"쿠키는 HTTP 프로토콜이 stateless 하기 때문에 사용된다.",
				"세션을 이용한 사용자 인증에는 쿠키가 필요하다.",
				"클라이언트는 Request의 메시지 바디에 쿠키 정보를 포함하여 Http Request를 전송한다."
		};
		int correction1 = 5;

		String problem2 = "다음 중 프로토콜에 대한 설명으로 옳은 것을 고르세요.";
		String[] example2 = {
				"TCP는 비연결 지향 프로토콜이다.",
				"Link layer 단계에서 데이터의 오류를 검증해 준다.",
				"Physical layer의 프로토콜이 동작하기 위해서는 어플리케이션의 Port 정보가 필요하다.",
				"라우터는 application layer의 프로토콜을 필수적으로 사용한다.",
				"모든 통신 장치는 OSI 7계층의 모든 프로토콜을 전부 구현해야 동작을 할 수 있다."};
		int correction2 = 2;

		String problem3 = "다음 보기의 big-O 표현법 중 가장 느린 시간 복잡도를 구하여라";
		String[] example3 = {
				"O(1)",
				"O(n^4)",
				"O(nlog(n))",
				"O(2^n)",
				"O(n)"
		};
		int correction3 = 4;

		String problem4 = "다음 중 -47의 2의 보수 표현법으로 옳은 것은?";
		String[] example4 = {
				"0011 1100",
				"1101 0000",
				"1101 0001",
				"0010 1111",
				"0001 1101"};
		int correction4 = 4;

		String problem5 = "다음 C 언어 변수 중 `데이터 영역`에 저장되는 변수는 무엇인가?";
		String[] example5 = {
				"main 함수 안에서 선언된 int age",
				"사용자 정의 함수 func1 안에서 선언 된 char alpha",
				"main 함수 안에서 동적으로 할당 된 int* array",
				"사용자 정의 함수 func3의 구현 코드",
				"사용자 정의 함수 func2 안에서 선언 된 static int sum",
				};
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
