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

//		PaperGenerator paperGenerator = new PaperGenerator();
//
//		JsonParser jsonParser = new JsonParser();
//
//		XmlParser xmlParser = new XmlParser();
//
//		CsvParser csvParser = new CsvParser();
//
//		jsonParser.createJsonFile();
//

		new MainWindowController();

	}
}