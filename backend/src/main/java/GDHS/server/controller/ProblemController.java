package GDHS.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import GDHS.server.constant.HttpConst;
import GDHS.server.dataclass.Problem;
import GDHS.server.dto.CollectionDTO;
import GDHS.server.dto.ProblemDTO;
import GDHS.server.dto.UserAnswerDTO;
import GDHS.server.repository.AnswerRepository;
import GDHS.server.repository.ProblemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ProblemController {

	ProblemRepository problemRepository = ProblemRepository.getProblemInstance();
	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	@GetMapping(HttpConst.PATH_PROBLEM)
	public ResponseEntity<ProblemDTO> getProblem(@RequestParam int problemNumber){
		if (verifyPageNumber(problemNumber))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		ProblemDTO problemDTO = getProblemDTO(problemNumber);

		return new ResponseEntity(problemDTO, HttpStatus.OK);
	}

	@PostMapping(HttpConst.PATH_PROBLEM)
	public ResponseEntity<CollectionDTO> getUserAnswer(@RequestParam int problemNumber, @RequestBody UserAnswerDTO userAnswerDTO){
		if (verifyPageNumber(problemNumber))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		int userAnswer = userAnswerDTO.getUserAnswer();

		if (verifyUserAnswer(userAnswer))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		String problemAnswer = getProblemAnswer(problemNumber);
		answerRepository.putAnswer(checkCollection(userAnswer, problemNumber), problemNumber);
		CollectionDTO collectionDTO = new CollectionDTO(problemAnswer);

		return new ResponseEntity(collectionDTO, HttpStatus.OK);
	}


	private boolean verifyUserAnswer(int userAnswer) {
		if(userAnswer > 5 || userAnswer < 1) {
			return true;
		}
		return false;
	}

	private boolean checkCollection(int userAnswer, int problemNumber) {
		Problem problem = problemRepository.getProblem(problemNumber);
		return  (userAnswer == problem.getCorrection());
	}
	private String getProblemAnswer(int problemNumber){
		Problem problem = problemRepository.getProblem(problemNumber);
		return Integer.toString(problem.getCorrection());
	}


	private ProblemDTO getProblemDTO(int problemNumber) {
		Problem problem = problemRepository.getProblem(problemNumber);
		return new ProblemDTO(problem);
	}

	private boolean verifyPageNumber(int problemNumber) {
		if(problemNumber > 5 || problemNumber < 1){
			return true;
		}
		return false;
	}
}
