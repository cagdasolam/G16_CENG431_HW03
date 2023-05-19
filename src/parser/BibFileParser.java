package parser;
import model.Article;
import model.ConferencePaper;
import model.Paper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BibFileParser {

	public Paper parseBibFile(File bibFile) throws IOException {
		Paper paper;


		try (BufferedReader reader = new BufferedReader(new FileReader(bibFile))) {
			String line = reader.readLine();

			if (line.startsWith("@article")) {
				paper = new Article();
			}
			else if (line.startsWith("@inproceedings")) {
				paper = new ConferencePaper();
			}
			else {
				paper = new Paper();
			}

			List<String> authors = null;
			String title = null;
			int year = 0;
			String DOI = null;
			int numDownloads= 0;

			int volume = 0;
			int number = 0;
			String journal = null;

			String bookTitle = null;

			while ((line = reader.readLine()) != null) {

				Matcher authorMatcher = Pattern.compile("author\\s*=\\s*\\{(.*)\\},").matcher(line);
				Matcher titleMatcher = Pattern.compile("title\\s*=\\s*\\{(.*)\\},").matcher(line);
				Matcher yearMatcher = Pattern.compile("year\\s*=\\s*\\{(.*)\\},").matcher(line);
				Matcher doiMatcher = Pattern.compile("doi\\s*=\\s*\\{(.*)\\},").matcher(line);

				Matcher booktitleMatcher = Pattern.compile("booktitle\\s*=\\s*\\{(.*)\\},").matcher(line);

				Matcher journalMatcher = Pattern.compile("journal\\s*=\\s*\\{(.*)\\},").matcher(line);
				Matcher volumeMatcher = Pattern.compile("volume\\s*=\\s*\\{(.*)\\},").matcher(line);
				Matcher numberMatcher = Pattern.compile("number\\s*=\\s*\\{(.*)\\},").matcher(line);

				if (authorMatcher.find()) {
					authors = Arrays.asList(authorMatcher.group(1).split(","));
				} else if (titleMatcher.find()) {
					title = titleMatcher.group(1);
				} else if (yearMatcher.find()) {
					year = Integer.parseInt(yearMatcher.group(1));
				} else if (doiMatcher.find()) {
					DOI = doiMatcher.group(1);
				}else if (booktitleMatcher.find()){
					bookTitle = booktitleMatcher.group(1);
				}else if (journalMatcher.find()){
					journal = journalMatcher.group(1);
				}else if (volumeMatcher.find()){
					volume = Integer.parseInt(volumeMatcher.group(1));
				}else if (numberMatcher.find()){
					number = Integer.parseInt(numberMatcher.group(1));
				}
			}

			paper.setAuthors(authors);
			paper.setTitle(title);
			paper.setYear(year);
			paper.setDOI(DOI);

			if (paper instanceof Article){
				((Article) paper).setJournal(journal);
				((Article) paper).setNumber(number);
				((Article) paper).setVolume(volume);
			}
			if (paper instanceof ConferencePaper){
				((ConferencePaper) paper).setBookTitle(bookTitle);
			}
		}

		return paper;
	}
}
