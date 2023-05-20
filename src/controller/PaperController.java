package controller;

import model.Paper;
import parser.CsvParser;

import java.util.List;

public class PaperController {

	CsvParser csvParser = new CsvParser();
	private final String papersCsv = "papersCsv.csv";

	public void downloadPaper(Paper paper){

		paper.setNumDownloads(paper.getNumDownloads() + 1);

		csvParser.updateCSV(papersCsv, paper.getTitle());
	}

	public List<Paper> getPapers(){
		return csvParser.getPapers(papersCsv);
	}
}
