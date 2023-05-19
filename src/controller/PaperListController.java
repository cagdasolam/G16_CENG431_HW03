package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.Paper;
import view.PaperListFrame;

public class PaperListController {
    private PaperListFrame paperListFrame;
    private List<Paper> papers;

    public PaperListController(List<Paper> papers) {
        this.paperListFrame = new PaperListFrame();
        this.papers = papers;

        loadPapers();
        paperListFrame.setVisible(true);
    }

    private void loadPapers() {
        List<String> paperNames = papers.stream().map(Paper::getTitle).collect(Collectors.toList());
        paperListFrame.setPapers(paperNames);
    }
}

