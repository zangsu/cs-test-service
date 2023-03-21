package GDHS.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import GDHS.server.dataclass.Problem;
import GDHS.server.dto.CollectionDTO;
import GDHS.server.dto.ProblemDTO;
import GDHS.server.dto.UserAnswerDTO;
import GDHS.server.repository.AnswerRepository;
import GDHS.server.repository.ProblemRepository;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProblemController {

	ProblemRepository problemRepository = ProblemRepository.getProblemInstance();
	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();
	private ObjectMapper objectMapper = new ObjectMapper();

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int problemNumber = Integer.parseInt(request.getParameter("problemNumber"));
		//실무에서는 이런 부분들 전부 예외처리를 해 주어야 할 것 같다.
		Object resultDTO = null;
		//파라미터 확인
		if (verifyPageNumber(problemNumber, response))
			return;

		if(request.getMethod().equals("GET")){
			resultDTO = getProblemDTO(problemNumber);
		} else if (request.getMethod().equals("POST")){ //POST
			int userAnswer = getUserAnswer(request);
			Long sessionID = (Long)request.getSession().getAttribute("sessionID");

			/*Long sessionID = null;
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				log.info("cookie = {}", cookie.getName());
				String StringSessionID = cookie.getAttribute("sessionID");
				if(StringSessionID != null) {
					sessionID = Long.parseLong(StringSessionID);
					log.info("sessionID = {}", sessionID);
					break;
				}
			}*/
			if(sessionID == null) {
				log.info("sessionID is null");
				return;
			}
			if (verifyUserAnswer(response, userAnswer))
				return;
			String problemAnswer = getProblemAnswer(problemNumber);
			answerRepository.putAnswer(sessionID, checkCollection(userAnswer, problemNumber), problemNumber);
			resultDTO = new CollectionDTO(problemAnswer);
		}

		makeResponse(response, resultDTO);
	}

	private int getUserAnswer(HttpServletRequest request) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		UserAnswerDTO userAnswerDTO = objectMapper.readValue(messageBody, UserAnswerDTO.class);
		int userAnswer = userAnswerDTO.getUserAnswer();
		return userAnswer;
	}


	private boolean verifyUserAnswer(HttpServletResponse response, int userAnswer) {
		if(userAnswer > 5 || userAnswer < 1) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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

	private boolean verifyPageNumber(int problemNumber, HttpServletResponse response) {
		if(problemNumber > 5 || problemNumber < 1){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return true;
		}
		return false;
	}


	private void makeResponse(HttpServletResponse response, Object resultDTO) throws IOException {
		response.setContentType("applicatino/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		System.out.println(resultDTO);
		writer.println(objectMapper.writeValueAsString(resultDTO));
	}
}
