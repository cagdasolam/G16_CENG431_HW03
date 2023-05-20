package controller;

import view.PaperDetailFrame;
import model.Paper;
import model.ReadingList;
import model.Researcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaperDetailController {
    private PaperDetailFrame view;
    private Paper paper;
    private ReadingListController readingListController;
    

    public PaperDetailController(Paper paper, Researcher researcher) {
        view = new PaperDetailFrame();
        this.paper = paper;
        this.readingListController = new ReadingListController();
        showPaperDetail(researcher);
    }

    public void showPaperDetail(Researcher researcher) {
        view.displayPaperDetails(paper);

        // Populate the reading list combo box
        List<ReadingList> readingLists = readingListController.findReadingListByUserName(researcher.getName());
        for (ReadingList readingList : readingLists) {
            view.getReadingListComboBox().addItem(readingList.getReadingListName());
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
                    addToReadingList(selectedReadingList, paper);
                }
            }
        });

        view.setVisible(true);
    }

    private void downloadPaper(Paper paper) {
        new PaperController().downloadPaper(paper);
    }

    private void addToReadingList(ReadingList readingList, Paper paper) {
    	readingListController.addPaperToReadingList(readingList.getReadingListName(), paper.getTitle());
    }
}

