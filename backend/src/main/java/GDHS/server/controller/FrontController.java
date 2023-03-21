package GDHS.server.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontContoller", urlPatterns = "/*")
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("launch FrontController.service");
		String requestURI = request.getRequestURI();
		log.info("request.getRemoteHost() = {}", request.getRemoteHost());
		log.info("request.getRemotePort() = {}", request.getRemotePort());
		log.debug("Request URL = {}", requestURI);
		HttpSession session = request.getSession(false);
		log.debug("session = {}", session);
		if(session != null){
			log.debug("sessionID = {}", session.getAttribute("sessionID"));
		}
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
