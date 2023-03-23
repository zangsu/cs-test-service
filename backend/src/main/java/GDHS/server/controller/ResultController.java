package GDHS.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import GDHS.server.constant.HttpConst;
import GDHS.server.dto.ResultDTO;
import GDHS.server.repository.AnswerRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {

	private ObjectMapper objectMapper = new ObjectMapper();
	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	@GetMapping(HttpConst.PATH_RESULT)
	public void service(HttpServletResponse response) throws IOException {
		ResultDTO result = answerRepository.getResult();
		makeResponse(response, result);
	}

	private void makeResponse(HttpServletResponse response, Object resultDTO) throws IOException {
		response.setContentType(HttpConst.RES_PARAM_CONTENT_JSON);
		response.setCharacterEncoding(HttpConst.RES_PARAM_ENCODING_UTF8);
		PrintWriter writer = response.getWriter();
		System.out.println(resultDTO);
		writer.println(objectMapper.writeValueAsString(resultDTO));
	}
}
