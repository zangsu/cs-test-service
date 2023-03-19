package GDHS.server.dto;

public class CollectionDTO {
	/*

	boolean isCorrect;

	public CollectionDTO(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
	*/
	String Answer;

	public CollectionDTO(String answer) {
		Answer = answer;
	}

	public String getAnswer() {
		return Answer;
	}
}
