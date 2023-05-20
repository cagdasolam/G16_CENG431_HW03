package controller;

import model.Paper;
import model.ReadingList;
import view.ReadingListFrame;

import java.util.List;

public class ReadingListFrameController {

	ReadingList readingList;
	private ReadingListFrame readingListFrame;
	private PaperController paperController;

	public ReadingListFrameController(ReadingList selectedReadingList) {
		this.readingList = selectedReadingList;
		this.readingListFrame = new ReadingListFrame(this.readingList);
		this.paperController = new PaperController();

		List<Paper> papers = paperController.getPapers();

		loadReadingListDetails();
		readingListFrame.setVisible(true);


//		readingListFrame.;
	}

	private void loadReadingListDetails(){
		readingListFrame.setReadingList(readingList);
	}



}
