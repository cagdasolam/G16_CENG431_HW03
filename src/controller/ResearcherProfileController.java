package controller;

import model.ReadingList;
import model.Researcher;
import view.ResearcherProfileFrame;

import javax.swing.*;

public class ResearcherProfileController {
	private final ResearcherProfileFrame researcherProfileFrame;
	private final Researcher selectedResearcher;
	private final Researcher loggedResearcher;
	private final ReadingListController readingListController;
	ResearcherController researcherController;

	public ResearcherProfileController(Researcher loggedResearcher, Researcher selectedResearcher) {
		this.researcherProfileFrame = new ResearcherProfileFrame();
		this.selectedResearcher = selectedResearcher;
		this.readingListController = new ReadingListController();
		this.loggedResearcher = loggedResearcher;
		this.researcherController = new ResearcherController();

		loadResearcherProfile(loggedResearcher, selectedResearcher, researcherProfileFrame);
		researcherProfileFrame.getBtnLookReadingListDetails().addActionListener(
				e -> showReadingListDetails());
		researcherProfileFrame.getBtnFollow().addActionListener(
				e -> {
					researcherController.followResearcher(loggedResearcher, selectedResearcher);
					researcherProfileFrame.addFollower(loggedResearcher.getName());
				});
		researcherProfileFrame.getBtnUnFollow().addActionListener(
				e -> {
					researcherController.unFollowResearcher(loggedResearcher, selectedResearcher);
					researcherProfileFrame.removeFollowing(loggedResearcher.getName());
				});


		researcherProfileFrame.setVisible(true);
	}


	private void loadResearcherProfile(Researcher loggedResearcher, Researcher selectedResearcher, ResearcherProfileFrame frame) {
		if (loggedResearcher.getName().equals(selectedResearcher.getName())){
			frame.getBtnFollow().setVisible(false);
			frame.getBtnUnFollow().setVisible(false);
		}

		frame.setName(selectedResearcher.getName());
		frame.setFollowing(selectedResearcher.getFollowingResearcherNames());
		frame.setFollowers(selectedResearcher.getFollowerResearcherNames());
		frame.setReadingLists(readingListController.findReadingListByUserName(selectedResearcher.getName()));
	}

	private void showReadingListDetails() {
		ReadingList selectedReadingList = researcherProfileFrame.getReadingLists().getSelectedValue();

        if (selectedReadingList != null) {
            new PaperListController(selectedReadingList, loggedResearcher);
        } else {
            JOptionPane.showMessageDialog(researcherProfileFrame, "Please select a reading list");
        }
    }

}

