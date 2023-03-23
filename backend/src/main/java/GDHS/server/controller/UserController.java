package GDHS.server.controller;

import java.io.IOException;

import GDHS.server.constant.HttpConst;
import GDHS.server.repository.AnswerRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {

	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	@PostMapping(HttpConst.PATH_USER)
	protected void service(@RequestParam String userName, HttpServletResponse response) throws
		ServletException, IOException {

		log.info("UserServlet.service");

		//String userName = request.getParameter(HttpConst.REQ_PARAM_USER_NAME);
		answerRepository.makeUser(userName);

		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
