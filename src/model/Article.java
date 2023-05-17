package model;

import java.util.List;

public class Article extends Paper{

	private int volume;
	private int number;
	private String journal;

	public Article() {}

	public Article(List<String> authors, String title, int year, String DOI, int volume, int number, String journal, int numDownloads) {
		super(authors, title, year, DOI, numDownloads);
		this.volume = volume;
		this.number = number;
		this.journal = journal;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}
}
