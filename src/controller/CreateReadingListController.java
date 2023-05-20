package controller;

import model.Paper;
import model.ReadingList;
import model.Researcher;
import view.CreateReadingListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateReadingListController {
    private CreateReadingListFrame view;
    private Researcher researcher;
    private List<Paper> papers;
    private ReadingListController readinglistController;

    public CreateReadingListController(Researcher researcher, List<Paper> papers) {
        this.researcher = researcher;
        this.papers = papers;
        this.view = new CreateReadingListFrame();
        this.readinglistController = new ReadingListController();
        view.setPapers(papers);

        initController();
        view.setVisible(true);
    }

    private void initController() {
        view.getCreateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReadingList();
            }
        });
    }

    private void createReadingList() {
        String newReadingListName = view.getReadingListNameField().getText().trim();
        if (newReadingListName.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a name for the reading list.");
            return;
        }

        for (ReadingList existingReadingList : readinglistController.findReadingListByUserName(researcher.getName())) {
            if (existingReadingList.getReadingListName().equals(newReadingListName)) {
                JOptionPane.showMessageDialog(view, "A reading list with this name already exists.");
                return;
            }
        }

        List<String> selectedPaperTitles = view.getPaperList().getSelectedValuesList();

        readinglistController.createNewReadingList(researcher, newReadingListName);
        for (String paperTitle : selectedPaperTitles) {
            readinglistController.addPaperToReadingList(newReadingListName, paperTitle, researcher);
        }
        JOptionPane.showMessageDialog(view, "New reading list is created! : " + newReadingListName);
        view.dispose();

        /*ReadingList newReadingList = new ReadingList(readingListName);
        for (Paper paper : papers) {
            if (selectedPaperTitles.contains(paper.getTitle())) {
                newReadingList.addPaper(paper);
            }
        }

        researcher.addReadingList(newReadingList);
        view.dispose(); // close the window*/
    }
}
