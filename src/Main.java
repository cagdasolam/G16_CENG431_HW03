import controller.PaperController;
import controller.ReadingListController;
import generator.CsvGenerator;
import model.Article;
import parser.JsonParser;
import generator.PaperGenerator;
import model.Paper;
import model.Researcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {

		PaperGenerator paperGenerator = new PaperGenerator();

		CsvGenerator csvGenerator = new CsvGenerator();

		JsonParser jsonParser = new JsonParser();

		List<Paper> papers = paperGenerator.getPapersFromCsv();

		jsonParser.createJsonFile();

		File jsonFile = new File("readingList.json");

		Researcher researcher = new Researcher("researcher","password", new ArrayList<>(), new ArrayList<>());

		ReadingListController readingListController = new ReadingListController();

		PaperController paperController = new PaperController();

		System.out.println(readingListController.createNewReadingList(researcher, "ReadingList3"));

		System.out.println(readingListController.addPaperToReadingList("ReadingList", "newPaper5"));

		paperController.downloadPaper(papers.get(0));
		paperController.downloadPaper(papers.get(14));




	}
}