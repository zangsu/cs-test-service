package GDHS.server.controller;

import GDHS.server.constant.HttpConst;
import GDHS.server.repository.AnswerRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Controller
public class UserController {

	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(HttpConst.PATH_USER)
	protected void service(@RequestParam String userName){
		log.info("UserServlet.service");

		answerRepository.makeUser(userName);
	}
}
