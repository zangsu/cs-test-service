package GDHS.server.dataclass;

public class Answer {
	String userName;
	boolean[] answer;

	public Answer(String userName) {
		this.userName = userName;
		this.answer = new boolean[5];
	}
}
