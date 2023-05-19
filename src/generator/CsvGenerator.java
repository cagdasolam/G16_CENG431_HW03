package generator;

import model.Article;
import model.ConferencePaper;
import model.Paper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvGenerator {

	public void generatePaperCsv(List<Paper> papers) {
		File papersCsv = new File("papersCsv.csv");

		if (papersCsv.exists()){
			return;
		}

		try (FileWriter writer = new FileWriter(papersCsv)) {
			for (Paper paper : papers) {
				StringBuilder line = new StringBuilder();
				if (paper instanceof Article article) {
					line.append("Article paper;")
							.append(String.join(",", article.getAuthors()))
							.append(";").append(article.getTitle())
							.append(";").append(article.getYear())
							.append(";").append(article.getDOI())
							.append(";").append(article.getVolume())
							.append(";").append(article.getNumber())
							.append(";").append(article.getJournal())
							.append(";").append(RandomNumberGenerator.generateRandomNumber(0, 1500))
							.append("\n");
				}
				if (paper instanceof ConferencePaper conferencePaper) {
					line.append("Conference paper;")
							.append(String.join(", ", conferencePaper.getAuthors()))
							.append(";").append(conferencePaper.getTitle())
							.append(";").append(conferencePaper.getYear())
							.append(";").append(conferencePaper.getDOI())
							.append(";").append(conferencePaper.getBookTitle())
							.append(";").append(RandomNumberGenerator.generateRandomNumber(0, 1500))
							.append("\n");
				}
				writer.append(line);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
