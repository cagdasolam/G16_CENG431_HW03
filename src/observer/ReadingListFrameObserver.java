package observer;

import controller.ReadingListController;
import model.Paper;
import model.ReadingList;
import model.Researcher;
import view.ReadingListFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadingListFrameObserver {
    private ReadingListFrame paperListFrame;
    private List<Paper> papers;
    private Researcher researcher;
    private ReadingListController readingListController;
    private String readingListName;
    private ReadingList readingList;

    public ReadingListFrameObserver(String readingListName, List<Paper> papers, Researcher researcher) {
        this.paperListFrame = new ReadingListFrame(readingListName);
        this.papers = new ArrayList<>(papers);
        this.researcher = researcher;
        this.readingListController = new ReadingListController();
        this.readingListName = readingListName;

//        loadPapers();
        paperListFrame.setOwner(isOwner(readingListName));
        paperListFrame.getBtnRemovePaper().addActionListener(e -> removeSelectedPaper());
        paperListFrame.getBtnViewDetails().addActionListener(e -> showPaperDetails());
        List<String> paperNames = papers.stream().map(Paper::getTitle).collect(Collectors.toList());
        paperListFrame.setPapers(paperNames);
        paperListFrame.setVisible(true);
    }

    public ReadingListFrameObserver(ReadingList readingList, Researcher researcher) {
        this.readingListName = readingList.getReadingListName();
        this.paperListFrame = new ReadingListFrame(readingListName);
        this.readingList = readingList;
        this.papers = readingList.getPapers();
        this.researcher = researcher;
        this.readingListController = new ReadingListController();

//        loadPapers();
        paperListFrame.setOwner(isOwner(readingListName));
        paperListFrame.getBtnRemovePaper().addActionListener(e -> removeSelectedPaper());
        paperListFrame.getBtnViewDetails().addActionListener(e -> showPaperDetails());
        List<String> paperNames = papers.stream().map(Paper::getTitle).collect(Collectors.toList());
        paperListFrame.setPapers(paperNames);
        paperListFrame.setVisible(true);
    }

    public ReadingList getUpdatedReadingList (){
        return readingList;
    }

    private void loadPapers() {
        List<String> paperNames = papers.stream().map(Paper::getTitle).collect(Collectors.toList());
        paperListFrame.setPapers(paperNames);
    }

    private boolean isOwner(String readingListName) {
        for (ReadingList readingList : new ReadingListController().findReadingListByUserName(researcher.getName())) {
            if (readingList.getReadingListName().equals(readingListName)) {
                return true;
            }
        }
        return false;
    }

    private void removeSelectedPaper() {
        String selectedPaperTitle = paperListFrame.getPaperList().getSelectedValue();
        Paper selectedPaper = findPaper(selectedPaperTitle);

        if (selectedPaper != null) {
            List<Paper> papers = new ArrayList<>(readingList.getPapers());
            papers.remove(selectedPaper);
            List<String> paperNames = papers.stream().map(Paper::getTitle).toList();
            readingList.setNameOfPapers(paperNames);

            paperListFrame.removePaper(selectedPaper);
            readingListController.removePaperFromReadIngList(readingListName, selectedPaperTitle, researcher);
        } else {
            JOptionPane.showMessageDialog(paperListFrame, "Please select a paper");
        }
    }
    
    private void showPaperDetails() {
        String selectedPaperTitle = paperListFrame.getPaperList().getSelectedValue();
        Paper selectedPaper = findPaper(selectedPaperTitle);

        if (selectedPaper != null) {
            new PaperDetailFrameObserver(selectedPaper, researcher);
        } else {
            JOptionPane.showMessageDialog(paperListFrame, "Please select a paper");
        }
    }
    
    private Paper findPaper(String paperTitle) {
        for (Paper paper : papers) {
            if (paper.getTitle().equals(paperTitle)) {
                return paper;
            }
        }
        return null;
    }

    public ReadingListFrame getPaperListFrame() {
        return paperListFrame;
    }
}

