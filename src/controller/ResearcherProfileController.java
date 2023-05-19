package controller;

import java.util.List;

import model.Researcher;
import view.ResearcherProfileFrame;

public class ResearcherProfileController {
    private ResearcherProfileFrame researcherProfileFrame;
    private Researcher researcher;

    public ResearcherProfileController(Researcher researcher) {
        this.researcherProfileFrame = new ResearcherProfileFrame();
        this.researcher = researcher;

        loadResearcherProfile();
        researcherProfileFrame.setVisible(true);
    }

    private void loadResearcherProfile() {
        researcherProfileFrame.setName(researcher.getName());
        researcherProfileFrame.setFollowing(researcher.getFollowingResearcherNames());
        researcherProfileFrame.setFollowers(researcher.getFollowerResearcherNames());
    }
}

