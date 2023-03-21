package GDHS.server.dataclass;

public class Answer {
	String userName;
	boolean[] answer;

	public Answer(String userName) {
		this.userName = userName;
		this.answer = new boolean[5];
	}

	public String getUserName() {
		return userName;
	}


	public boolean[] getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return userName + " " + answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3] + " " + answer[4];
	}
}
