package GDHS.server.repository;

import java.util.HashMap;
import java.util.Map;

import GDHS.server.dataclass.User;

public class SessionRepository {
	private static Map<Long, User> sessionMap = new HashMap<>();
	private static Long lastSessionId = 0L;
	private static SessionRepository instance =  new SessionRepository();

	private SessionRepository() {
	}

	public static SessionRepository getSessionInstance() {
		return instance;
	}

	public Long makeNewSessionID(String userName, String userID){
		User user = new User(userName, userID);
		sessionMap.put(++lastSessionId, user);

		return lastSessionId;
	}

	public User getUser(Long UserId){
		return sessionMap.get(UserId);
	}

}
