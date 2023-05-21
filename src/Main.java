import controller.MainWindowController;
import controller.PaperController;
import controller.ReadingListController;
import controller.ResearcherController;
import generator.PaperGenerator;
import model.Paper;
import model.Researcher;
import parser.CsvParser;
import parser.JsonParser;
import parser.XmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {

		PaperGenerator paperGenerator = new PaperGenerator();

		JsonParser jsonParser = new JsonParser();

		XmlParser xmlParser = new XmlParser();
		
		CsvParser csvParser = new CsvParser();

		List<Paper> papers = paperGenerator.getPapersFromCsv();

		jsonParser.createJsonFile();

		Researcher researcher = new Researcher("researcher", "password", new ArrayList<>(), new ArrayList<>());

		ReadingListController readingListController = new ReadingListController();

		PaperController paperController = new PaperController();

		ResearcherController researcherController = new ResearcherController();

		System.out.println(readingListController.createNewReadingList(researcher, "ReadingList3"));

//		System.out.println(readingListController.addPaperToReadingList("ReadingList3", "newPaper"));

		List<Researcher> researchers = xmlParser.getResearchersFromXml();

		System.out.println(researchers);

//		Researcher follower = researchers.get(0);
//		Researcher followed = researchers.get(3);
//
//		System.out.println(follower.getName() + "follow etti" + followed.getName());
//		researcherController.followResearcher(follower, followed);
//		System.out.println();
//		System.out.println(researcherController.unFollowResearcher(researchers.get(0), researchers.get(3)));
		
		new MainWindowController();

	}
}