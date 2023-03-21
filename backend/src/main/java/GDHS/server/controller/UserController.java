package GDHS.server.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import org.springframework.web.bind.annotation.CrossOrigin;

import GDHS.server.dataclass.User;
import GDHS.server.repository.SessionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	SessionRepository sessionRepository = SessionRepository.getSessionInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {

		log.info("UserServlet.service");

		/*
		HttpSession session = request.getSession();
		if(session.isNew()) {
			log.info("create new session");
			String userName = request.getParameter("userName");
			String userID = request.getParameter("userID");
			Long sessionId = sessionRepository.makeNewSessionID(userName, userID);
			session.setAttribute("sessionID", sessionId);
			Cookie cookie = new Cookie("sessionID", sessionId.toString());
			Iterator<String> stringIterator = session.getAttributeNames().asIterator();
			while(stringIterator.hasNext()){
				String next = stringIterator.next();
				log.info("session : {} - {}", next, session.getAttribute(next));
			}
			log.info("original domain : {}", cookie.getDomain());
			cookie.setDomain("localhost");
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 10);
			response.addCookie(cookie);


			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				log.info("cookie is null");
			}
			else{
			for (Cookie cookie1 : cookies) {
				log.info("cookie : {}", cookie.getName());
			}
			}

			log.info("sessionID = {}", sessionId);
		}else{
			Long sessionID = (Long)session.getAttribute("sessionID");
			User user = sessionRepository.getUser(sessionID);
			log.info("session User = {}, {}", user.getUsername(), user.getUserID());
		}
		*/
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
