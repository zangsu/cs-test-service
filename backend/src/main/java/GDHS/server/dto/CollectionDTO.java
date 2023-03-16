package GDHS.server.dto;

public class CollectionDTO {
	boolean isCorrect;

	public CollectionDTO(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
}
