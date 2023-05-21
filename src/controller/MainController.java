package controller;

import model.Paper;
import model.Researcher;
import parser.CsvParser;
import view.MainFrame;

import java.util.List;

public class MainController {
    private MainFrame mainFrame;
    private Researcher loggedResearcher; // the currently logged-in researcher
    private List<Researcher> researchers;
    private List<Paper> papers;
    private  ResearcherController researcherController;

    public MainController(Researcher loggedResearcher) {
        this.mainFrame = new MainFrame(loggedResearcher);
        this.loggedResearcher = loggedResearcher;
        this.researcherController = new ResearcherController();
        this.researchers = researcherController.getResearchers();
        this.papers = new CsvParser().getPapers("papersCsv.csv");
        
        initController();
        mainFrame.setVisible(true);
    }
    
    private void initController() {
        mainFrame.getBtnViewProfile().addActionListener(e -> openProfile());
        mainFrame.getCreateNewReadingList().addActionListener(e -> createNewReadingList());
        mainFrame.getBtnViewPapers().addActionListener(e -> openPapers());
        mainFrame.getBtnViewResearchers().addActionListener(e -> openResearchers());
    }

    private void openProfile() {
        new ResearcherProfileController(loggedResearcher, loggedResearcher);
    }

    private void createNewReadingList() {
        new CreateReadingListController(researcher, papers);
    }

    private void openPapers() {
        new PaperListController(papers, loggedResearcher);
    }

    private void openResearchers() {
        new ResearcherListController(researchers, loggedResearcher);
    }
}

