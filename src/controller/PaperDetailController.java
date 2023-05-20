package controller;

import model.Paper;
import model.ReadingList;
import model.Researcher;
import view.PaperDetailFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaperDetailController {
	private PaperDetailFrame view;
	private Paper paper;
	private ReadingListController readingListController;
	private Researcher researcher;


	public PaperDetailController(Paper paper, Researcher researcher) {
		view = new PaperDetailFrame();
		this.paper = paper;
		this.readingListController = new ReadingListController();
		this.researcher = researcher;
		showPaperDetail();
	}

	public void showPaperDetail() {
		view.displayPaperDetails(paper);

		// Populate the reading list combo box
		List<ReadingList> readingLists = readingListController.findReadingListByUserName(researcher.getName());
		for (ReadingList readingList : readingLists) {
			view.getReadingListComboBox().addItem(readingList);
		}

		view.getBtnDownload().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				downloadPaper(paper);
			}
		});

		view.getBtnAddToReadingList().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReadingList selectedReadingList = (ReadingList) view.getReadingListComboBox().getSelectedItem();
				if (selectedReadingList != null) {
					if (!addToReadingList(selectedReadingList, paper)){
                        JOptionPane.showMessageDialog(view, "Paper already in list");
                    }
				}
			}
		});

		view.setVisible(true);
	}

	private void downloadPaper(Paper paper) {
		new PaperController().downloadPaper(paper);
	}

	private boolean addToReadingList(ReadingList readingList, Paper paper) {
		return readingListController.addPaperToReadingList(readingList.getReadingListName(), paper.getTitle(), researcher);
	}
}

