package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.Answer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerRepository {
	private static AnswerRepository answerInstance = new AnswerRepository();
	private static Map<Long, Answer> answerMap = new HashMap<>();
	private SessionRepository sessionRepository = SessionRepository.getSessionInstance();

	private SessionRepository sessionInstance = SessionRepository.getSessionInstance();
	private ProblemRepository problemInstance = ProblemRepository.getProblemInstance();


	private AnswerRepository() {	}

	public static AnswerRepository getAnswerInstance(){
		return answerInstance;
	}

	public void putAnswer(Long sessionID, boolean correction, int problemNumber) {
		Answer answer;
		if(problemNumber == 1){
			String username = sessionRepository.getUser(sessionID).getUsername();
			answer = new Answer(username);
		}
		else{
			answer = answerMap.get(sessionID);
		}
		answer.getAnswer()[problemNumber-1] = correction;
		answerMap.put(sessionID, answer);
		log.info(answer.toString());
	}
}
