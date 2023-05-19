import generator.CsvGenerator;
import generator.JsonParser;
import generator.PaperGenerator;
import model.Paper;
import model.ReadingList;
import model.Researcher;
import parser.BibFileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {

		PaperGenerator paperGenerator = new PaperGenerator();

		CsvGenerator csvGenerator = new CsvGenerator();

		JsonParser jsonParser = new JsonParser();

		List<Paper> papers = paperGenerator.getPapersFromBibFiles();

		csvGenerator.generatePaperCsv(papers);

		jsonParser.createJsonFile();

		Researcher researcher = new Researcher("researcher","password", new ArrayList<>(), new ArrayList<>());

		System.out.println(jsonParser.createNewReadingList(researcher, "ReadingList"));


	}
}