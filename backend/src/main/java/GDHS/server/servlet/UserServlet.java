package GDHS.server.servlet;

import java.io.IOException;

import GDHS.server.repository.SessionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "userServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
	SessionRepository sessionRepository = SessionRepository.getInstance();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.isNew()) {
			String userName = request.getParameter("userName");
			String userID = request.getParameter("userID");
			Long sessionId = sessionRepository.makeNewSessionID(userName, userID);
			session.setAttribute("sessionID", sessionId);
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}
