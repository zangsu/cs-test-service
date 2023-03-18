package GDHS.server.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontContoller", urlPatterns = "/*")
public class FrontController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontController.service");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI = " + requestURI);
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
