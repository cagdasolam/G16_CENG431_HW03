package model;

import java.util.List;

public class ConferencePaper extends Paper{

	private String bookTitle;

	public ConferencePaper() {}

	public ConferencePaper(List<String> authors, String title, int year, String DOI, String bookTitle, int numDownloads) {
		super(authors, title, year, DOI, numDownloads);
		this.bookTitle = bookTitle;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
}
