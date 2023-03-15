package GDHS.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import GDHS.server.dataclass.Problem;
import GDHS.server.dto.ProblemDTO;
import GDHS.server.repository.ProblemRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProblemController {

	ProblemRepository problemRepository = ProblemRepository.getProblemInstance();
	private ObjectMapper objectMapper = new ObjectMapper();

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getMethod().equals("GET")){
			ProblemDTO problemDTO = getRequest(request);
			if(problemDTO == null){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			makeResponse(response, problemDTO);
		}
	}

	private ProblemDTO getRequest(HttpServletRequest request) {
		String problemNumber = request.getParameter("problemNumber");
		ProblemDTO problemDTO = ServeProblemToClient(problemNumber);
		return problemDTO;
	}

	private void makeResponse(HttpServletResponse response, ProblemDTO problemDTO) throws IOException {
		response.setContentType("applicatino/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(objectMapper.writeValueAsString(problemDTO));
	}

	private ProblemDTO ServeProblemToClient(String problemNumberString) {
		int problemNumber = Integer.parseInt(problemNumberString);
		Problem problem = getProblem(problemNumber);
		if (problem == null)
			return null;
		return new ProblemDTO(problem);
	}

	private Problem getProblem(int problemNumber) {
		if(problemNumber > 5 || problemNumber < 1)
			return null;
		Problem problem = problemRepository.getProblem(problemNumber);
		return problem;
	}
}
