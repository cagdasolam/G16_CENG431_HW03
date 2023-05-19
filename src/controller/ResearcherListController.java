package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.Researcher;
import view.ResearcherListFrame;

public class ResearcherListController {
    private ResearcherListFrame researcherListFrame;
    private List<Researcher> researchers;

    public ResearcherListController(List<Researcher> researchers) {
        this.researcherListFrame = new ResearcherListFrame();
        this.researchers = researchers;

        loadResearchers();
        researcherListFrame.setVisible(true);
    }

    private void loadResearchers() {
        List<String> researcherNames = researchers.stream().map(Researcher::getName).collect(Collectors.toList());
        researcherListFrame.setResearchers(researcherNames);
    }
}
