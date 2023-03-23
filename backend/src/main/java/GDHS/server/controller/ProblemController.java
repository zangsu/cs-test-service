package GDHS.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import GDHS.server.dto.ResultDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import GDHS.server.constant.HttpConst;
import GDHS.server.dataclass.Problem;
import GDHS.server.dto.CollectionDTO;
import GDHS.server.dto.ProblemDTO;
import GDHS.server.dto.UserAnswerDTO;
import GDHS.server.repository.AnswerRepository;
import GDHS.server.repository.ProblemRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ProblemController {

	ProblemRepository problemRepository = ProblemRepository.getProblemInstance();
	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();
	private ObjectMapper objectMapper = new ObjectMapper();


	@RequestMapping(HttpConst.PATH_PROBLEM)
	public ResponseEntity<Object> service(HttpMethod method, @RequestParam int problemNumber, @RequestParam UserAnswerDTO userAnswerDTO){

		Object resultDTO = null;
		//파라미터 확인
		if (verifyPageNumber(problemNumber))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		if(method.matches(HttpConst.HTTP_METHOD_GET)){
			resultDTO = getProblemDTO(problemNumber);
		}
		else if(method.matches(HttpConst.HTTP_METHOD_POST)){
			int userAnswer = userAnswerDTO.getUserAnswer();

			if (verifyUserAnswer(userAnswer))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			String problemAnswer = getProblemAnswer(problemNumber);
			answerRepository.putAnswer(checkCollection(userAnswer, problemNumber), problemNumber);
			resultDTO = new CollectionDTO(problemAnswer);
		}

		return new ResponseEntity<>(resultDTO, HttpStatus.OK);
		//makeResponse(response, resultDTO);
	}

	private boolean verifyUserAnswer(int userAnswer) {
		if(userAnswer > 5 || userAnswer < 1) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return true;
		}
		return false;
	}


	private void makeResponse(HttpServletResponse response, Object resultDTO) throws IOException {
		response.setContentType(HttpConst.RES_PARAM_CONTENT_JSON);
		response.setCharacterEncoding(HttpConst.RES_PARAM_ENCODING_UTF8);
		PrintWriter writer = response.getWriter();
		System.out.println(resultDTO);
		writer.println(objectMapper.writeValueAsString(resultDTO));
	}
}
