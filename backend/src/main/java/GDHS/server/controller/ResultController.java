package GDHS.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import GDHS.server.dto.ResultDTO;
import GDHS.server.repository.AnswerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResultController {

	private ObjectMapper objectMapper = new ObjectMapper();
	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultDTO result = answerRepository.getResult();
		makeResponse(response, result);
	}

	private void makeResponse(HttpServletResponse response, Object resultDTO) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		System.out.println(resultDTO);
		writer.println(objectMapper.writeValueAsString(resultDTO));
	}
}