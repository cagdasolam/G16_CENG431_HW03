package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.Paper;
import model.ReadingList;
import model.Researcher;
import view.PaperListFrame;

public class PaperListController {
    private PaperListFrame paperListFrame;
    private List<Paper> papers;
    private Researcher researcher;

    public PaperListController(String readingListName, List<Paper> papers, Researcher researcher) {
        this.paperListFrame = new PaperListFrame(readingListName);
        this.papers = papers;
        this.researcher = researcher;

        loadPapers();
        paperListFrame.setOwner(isOwner(readingListName));
        paperListFrame.getBtnRemovePaper().addActionListener(e -> removeSelectedPaper());
        paperListFrame.getBtnViewDetails().addActionListener(e -> showPaperDetails());
        paperListFrame.setVisible(true);
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
            papers.remove(selectedPaper);
            loadPapers();
        } else {
            JOptionPane.showMessageDialog(paperListFrame, "Please select a paper");
        }
    }
    
    private void showPaperDetails() {
        String selectedPaperTitle = paperListFrame.getPaperList().getSelectedValue();
        Paper selectedPaper = findPaper(selectedPaperTitle);

        if (selectedPaper != null) {
            new PaperDetailController(selectedPaper, researcher);
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

}

