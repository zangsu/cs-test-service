package GDHS.server.controller;

import java.io.IOException;

import GDHS.server.repository.SessionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserController {
	SessionRepository sessionRepository = SessionRepository.getSessionInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {

		log.info("UserServlet.service");
		HttpSession session = request.getSession();
		if(session.isNew()) {
			String userName = request.getParameter("userName");
			String userID = request.getParameter("userID");
			Long sessionId = sessionRepository.makeNewSessionID(userName, userID);
			session.setAttribute("sessionID", sessionId);
			log.info("sessionID = {}", sessionId);
		}else{
			log.info("get sessionID = {}", session.getAttribute("sessionID"));
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
