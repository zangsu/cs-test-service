package GDHS.server.dataclass;

public class User {
	private String username;
	private String userID;

	public User(String username, String userID) {
		this.username = username;
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public String getUserID() {
		return userID;
	}
}
