package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.Answer;
import GDHS.server.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerRepository {
	//private static AnswerRepository answerInstance = new AnswerRepository();
	//private static Map<String, Answer> answerMap = new HashMap<>();
	//private SessionRepository sessionRepository = SessionRepository.getSessionInstance();
	private ProblemRepository problemInstance = ProblemRepository.getProblemInstance();

	private static Answer answer = new Answer();

	private AnswerRepository() {	}

	public static Answer getAnswer(){
		return answer;
	}

	public void makeUser(String userName){
		answer.setUserName(userName);
	}

	public void putAnswer(boolean correction, int problemNumber) {
		answer.getAnswer()[problemNumber-1] = correction;
		log.info(answer.toString());
	}

	public ResultDTO getResult(){
		int score = 0;
		for (boolean correction : answer.getAnswer()) {
			if(correction == true)
				score += 20;
		}
		String resultString = answer.getUserName()+ "님의 점수는 " + score + "점 입니다.";

		return new ResultDTO(resultString, answer.getAnswer());
	}


}
