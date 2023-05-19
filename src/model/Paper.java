package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Paper {

	private List<String> authors;
	private String title;
	private int year;
	private String DOI;
	private int numDownloads;

	public Paper(){}

	public Paper(List<String> authors, String title, int year, String DOI, int numDownloads) {
		this.authors = authors;
		this.title = title;
		this.year = year;
		this.DOI = DOI;
		this.numDownloads = numDownloads;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String DOI) {
		this.DOI = DOI;
	}

	public int getNumDownloads() {
		return numDownloads;
	}

	public void setNumDownloads(int numDownloads) {
		this.numDownloads = numDownloads;
	}
}
