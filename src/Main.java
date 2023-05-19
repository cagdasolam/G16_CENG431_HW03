import controller.MainWindowController;
import controller.PaperController;
import controller.ReadingListController;
import controller.ResearcherController;
import generator.CsvGenerator;
import generator.PaperGenerator;
import model.Paper;
import model.Researcher;
import parser.JsonParser;
import parser.XmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {

		PaperGenerator paperGenerator = new PaperGenerator();

		CsvGenerator csvGenerator = new CsvGenerator();

		JsonParser jsonParser = new JsonParser();

		XmlParser xmlParser = new XmlParser();

		List<Paper> papers = paperGenerator.getPapersFromCsv();

		jsonParser.createJsonFile();

		File jsonFile = new File("readingList.json");

		Researcher researcher = new Researcher("researcher", "password", new ArrayList<>(), new ArrayList<>());

		ReadingListController readingListController = new ReadingListController();

		PaperController paperController = new PaperController();

		ResearcherController researcherController = new ResearcherController();

		System.out.println(readingListController.createNewReadingList(researcher, "ReadingList3"));

		System.out.println(readingListController.addPaperToReadingList("ReadingList2", "newPaper1"));

		paperController.downloadPaper(papers.get(0));
		paperController.downloadPaper(papers.get(14));

		List<Researcher> researchers = xmlParser.getResearchersFromXml();

		System.out.println(researchers);

		System.out.println(researcherController.followResearcher(researchers.get(0), researchers.get(3)));
		System.out.println(researcherController.unFollowResearcher(researchers.get(0), researchers.get(3)));
		
	
		/*SwingUtilities.invokeLater(() -> {
	        LoginFrame loginFrame = new LoginFrame();
	        new LoginController(loginFrame, xmlParser);
	        loginFrame.setVisible(true);
	    });*/
		
		new MainWindowController();

	}
}