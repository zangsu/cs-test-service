package GDHS.server.controller;

import java.io.IOException;
import GDHS.server.repository.AnswerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserController {

	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {

		String userName = request.getParameter("userName");
		log.info("UserServlet.service");

		answerRepository.makeUser(userName);

		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
