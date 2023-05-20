package controller;

import model.ReadingList;
import model.Researcher;
import view.ResearcherProfileFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResearcherProfileController {
    private ResearcherProfileFrame researcherProfileFrame;
    private Researcher researcher;
    private ReadingListController readingListController;

    public ResearcherProfileController(Researcher researcher) {
        this.researcherProfileFrame = new ResearcherProfileFrame();
        this.researcher = researcher;
        this.readingListController = new ReadingListController();

        loadResearcherProfile(researcher);
        researcherProfileFrame.getBtnLookDetails().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReadingListDetails();
            }
        });
        researcherProfileFrame.setVisible(true);
    }

    private void loadResearcherProfile(Researcher researcher) {
        researcherProfileFrame.setName(researcher.getName());
        researcherProfileFrame.setFollowing(researcher.getFollowingResearcherNames());
        researcherProfileFrame.setFollowers(researcher.getFollowerResearcherNames());
        researcherProfileFrame.setReadingLists(readingListController.findReadingListByUserName(researcher.getName()));
    }
    
    private void showReadingListDetails() {
        ReadingList selectedReadingList =  researcherProfileFrame.getReadingLists().getSelectedValue();

        if (selectedReadingList != null) {
            new PaperListController(selectedReadingList.getPapers(), researcher);
        } else {
            JOptionPane.showMessageDialog(researcherProfileFrame, "Please select a reading list");
        }
    }
}

