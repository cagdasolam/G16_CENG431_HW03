package model;

import java.util.List;

public class ReadingList {

	private int readingListId;
	private String creatorResearcherName;
	private String readingListName;
	private int numberOfPapers;
	private List<String> nameOfPapers;

	public ReadingList(int readingListId, String creatorResearcherName, String readingListName, int numberOfPapers, List<String> nameOfPapers) {
		this.readingListId = readingListId;
		this.creatorResearcherName = creatorResearcherName;
		this.readingListName = readingListName;
		this.numberOfPapers = numberOfPapers;
		this.nameOfPapers = nameOfPapers;
	}

	public int getReadingListId() {
		return readingListId;
	}

	public void setReadingListId(int readingListId) {
		this.readingListId = readingListId;
	}

	public String getCreatorResearcherName() {
		return creatorResearcherName;
	}

	public void setCreatorResearcherName(String creatorResearcherName) {
		this.creatorResearcherName = creatorResearcherName;
	}

	public String getReadingListName() {
		return readingListName;
	}

	public void setReadingListName(String readingListName) {
		this.readingListName = readingListName;
	}

	public int getNumberOfPapers() {
		return numberOfPapers;
	}

	public void setNumberOfPapers(int numberOfPapers) {
		this.numberOfPapers = numberOfPapers;
	}

	public List<String> getNameOfPapers() {
		return nameOfPapers;
	}

	public void setNameOfPapers(List<String> nameOfPapers) {
		this.nameOfPapers = nameOfPapers;
	}
}
