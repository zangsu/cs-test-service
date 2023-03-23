package GDHS.server.controller;

import GDHS.server.constant.HttpConst;
import GDHS.server.dto.ResultDTO;
import GDHS.server.repository.AnswerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultController {

	AnswerRepository answerRepository = AnswerRepository.getAnswerInstance();

	@GetMapping(HttpConst.PATH_RESULT)
	public ResultDTO service(){
		ResultDTO result = answerRepository.getResult();

		return result;
	}
}
