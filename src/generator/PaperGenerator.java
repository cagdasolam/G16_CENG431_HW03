package generator;

import model.Article;
import model.ConferencePaper;
import model.Paper;
import org.jbibtex.ParseException;
import parser.BibFileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PaperGenerator {

	BibFileParser bibFileParser = new BibFileParser();

	public List<Paper> getPapersFromBibFiles() {

		List<File> bibFiles = Arrays.stream(Objects.requireNonNull(new File("./bib_files").listFiles()))
				.filter(file -> file.isFile() && file.getName().endsWith(".bib"))
				.toList();

		return bibFiles.stream().map(bibFile -> {
			try {
				return bibFileParser.parseBibFile(bibFile);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}).toList();
	}

	public List<Paper> getPapersFromCsv() throws IOException {
		File papersCsv = new File("./papersCsv.csv");

		if (!papersCsv.exists()) {
			List<Paper> papers = getPapersFromBibFiles();
			CsvGenerator.generatePaperCsv(papers);
			return papers;
		}

		List<Paper> papers = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(papersCsv))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(";");

				if (fields[0].equals("Article paper")) {
					Article article = new Article();

					article.setAuthors(Arrays.stream(fields[1].split(",")).toList());
					article.setTitle(fields[2]);
					article.setYear(Integer.parseInt(fields[3]));
					article.setDOI(fields[4]);
					article.setVolume(Integer.parseInt(fields[5]));
					article.setNumber(Integer.parseInt(fields[6]));
					article.setJournal(fields[7]);
					article.setNumDownloads(Integer.parseInt(fields[8]));

					papers.add(article);
				}

				if (fields[0].equals("Conference paper")) {
					ConferencePaper conferencePaper = new ConferencePaper();

					conferencePaper.setAuthors(Arrays.stream(fields[1].split(",")).toList());
					conferencePaper.setTitle(fields[2]);
					conferencePaper.setYear(Integer.parseInt(fields[3]));
					conferencePaper.setDOI(fields[4]);
					conferencePaper.setBookTitle(fields[5]);
					conferencePaper.setNumDownloads(Integer.parseInt(fields[6]));

					papers.add(conferencePaper);
				}

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return papers;
	}

}
