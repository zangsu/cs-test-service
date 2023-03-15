package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.Answer;

public class AnswerRepository {
	private static AnswerRepository answerInstance = new AnswerRepository();
	private static Map<String, Answer> answerMap = new HashMap<>();

	private SessionRepository sessionInstance = SessionRepository.getSessionInstance();
	private ProblemRepository problemInstance = ProblemRepository.getProblemInstance();


	private AnswerRepository() {	}

	public static AnswerRepository getAnswerInstance(){
		return answerInstance;
	}

	public void putAnswer(String sessionID, boolean correction) {

	}
}
