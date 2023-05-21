package observer;

import controller.ReadingListController;
import model.Paper;
import model.ReadingList;
import model.Researcher;
import view.CreateReadingListFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateReadingListFrameObserver {
    private CreateReadingListFrame view;
    private Researcher researcher;
    private List<Paper> papers;
    private ReadingListController readinglistController;

    public CreateReadingListFrameObserver(Researcher researcher, List<Paper> papers) {
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
    }
}
