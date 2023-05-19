package generator;

import model.Paper;
import parser.BibFileParser;

import java.io.File;
import java.io.IOException;
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
			}
		}).toList();
	}

}
