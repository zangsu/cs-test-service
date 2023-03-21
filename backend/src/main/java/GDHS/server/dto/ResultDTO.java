package GDHS.server.dto;

public class ResultDTO {
	String resultTitle;
	boolean[] results;

	public ResultDTO(String resultTitle, boolean[] results) {
		this.resultTitle = resultTitle;
		this.results = results;
	}

	public String getResultTitle() {
		return resultTitle;
	}

	public boolean[] getResults() {
		return results;
	}
}
