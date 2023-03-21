package GDHS.server.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontContoller", urlPatterns = "/*")
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("launch FrontController.service");
		String requestURI = request.getRequestURI();
		log.debug("Request URL = {}", requestURI);

		switch (requestURI){
			case "/user":
				UserController userController = new UserController();
				userController.service(request, response);
				break;
			case "/problem":
				ProblemController problemController = new ProblemController();
				problemController.service(request, response);
				break;
			case "/result":
				ResultController resultController = new ResultController();
				resultController.service(request, response);
				break;
		}
	}

}
