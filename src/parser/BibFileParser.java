package parser;

import generator.RandomNumberGenerator;
import model.Article;
import model.ConferencePaper;
import model.Paper;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;
import org.jbibtex.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BibFileParser {

	public Paper parseBibFile(File bibFile) throws IOException, ParseException {
		try (BufferedReader reader = new BufferedReader(new FileReader(bibFile))) {
			BibTeXParser bibTeXParser = new BibTeXParser();
			BibTeXDatabase database = bibTeXParser.parse(reader);

			for (BibTeXEntry entry : database.getEntries().values()) {
				String entryType = entry.getType().getValue();

				Paper paper;
				if (entryType.equals("article")) {
					paper = new Article();
				} else if (entryType.equals("inproceedings")) {
					paper = new ConferencePaper();
				} else {
					paper = new Paper();
				}

				Value authorValue = entry.getField(BibTeXEntry.KEY_AUTHOR);
				if (authorValue != null) {
					String[] authors = authorValue.toUserString().split(",");
					paper.setAuthors(Arrays.asList(authors));
				}

				Value titleValue = entry.getField(BibTeXEntry.KEY_TITLE);
				if (titleValue != null) {
					paper.setTitle(titleValue.toUserString());
				}

				Value yearValue = entry.getField(BibTeXEntry.KEY_YEAR);
				if (yearValue != null) {
					paper.setYear(Integer.parseInt(yearValue.toUserString()));
				}

				Value doiValue = entry.getField(BibTeXEntry.KEY_DOI);
				if (doiValue != null) {
					paper.setDOI(doiValue.toUserString());
				}

				// Set additional fields based on the paper type (Article or ConferencePaper)
				if (paper instanceof Article article) {
					Value journalValue = entry.getField(BibTeXEntry.KEY_JOURNAL);
					if (journalValue != null) {
						article.setJournal(journalValue.toUserString());
					}

					Value numberValue = entry.getField(BibTeXEntry.KEY_NUMBER);
					if (numberValue != null) {
						article.setNumber(Integer.parseInt(numberValue.toUserString()));
					}

					Value volumeValue = entry.getField(BibTeXEntry.KEY_VOLUME);
					if (volumeValue != null) {
						article.setVolume(Integer.parseInt(volumeValue.toUserString()));
					}
				} else if (paper instanceof ConferencePaper conferencePaper) {
					Value bookTitleValue = entry.getField(BibTeXEntry.KEY_BOOKTITLE);
					if (bookTitleValue != null) {
						conferencePaper.setBookTitle(bookTitleValue.toUserString());
					}
				}

				paper.setNumDownloads(RandomNumberGenerator.generateRandomNumber(0, 1500));

				return paper; // Return the first paper found (you can modify this as per your requirement)
			}
		}

		throw new ParseException("No entries found in the BibTeX file.");
	}
}
