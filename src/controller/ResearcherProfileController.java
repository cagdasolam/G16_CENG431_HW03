package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.ReadingList;
import model.Researcher;
import view.ResearcherProfileFrame;

public class ResearcherProfileController {
    private ResearcherProfileFrame researcherProfileFrame;
    private Researcher researcher;
    private ReadingListController readingListController;

    public ResearcherProfileController(Researcher researcher) {
        this.researcherProfileFrame = new ResearcherProfileFrame();
        this.researcher = researcher;
        this.readingListController = new ReadingListController();

        loadResearcherProfile();
        researcherProfileFrame.setVisible(true);
    }

    private void loadResearcherProfile() {
        researcherProfileFrame.setName(researcher.getName());
        researcherProfileFrame.setFollowing(researcher.getFollowingResearcherNames());
        researcherProfileFrame.setFollowers(researcher.getFollowerResearcherNames());
        researcherProfileFrame.setReadingLists(readingListController.findReadingListByUserName(researcher.getName()));
    }
    
    private void showReadingListDetails() {
        String selectedReadingListName = researcherProfileFrame.getReadingLists().getSelectedValue();
        ReadingList selectedReadingList = findReadingList(selectedReadingListName);

        if (selectedReadingList != null) {
            new ReadingListFrameController(selectedReadingList);
        } else {
            JOptionPane.showMessageDialog(researcherProfileFrame, "Please select a reading list");
        }
    }
    
    private ReadingList findReadingList(String readingListName) {
    	return readingListController.findByReadingListName(readingListName);
    }


}

