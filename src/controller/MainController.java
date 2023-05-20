package controller;

import model.Paper;
import model.Researcher;
import parser.CsvParser;
import view.MainFrame;

import java.util.List;

public class MainController {
    private MainFrame mainFrame;
    private Researcher researcher; // the currently logged-in researcher
    private List<Researcher> researchers;
    private List<Paper> papers;
    private  ResearcherController researcherController;

    public MainController(Researcher researcher) {
        this.mainFrame = new MainFrame(researcher);
        this.researcher = researcher;
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
        new ResearcherProfileController(researcher);
    }

    private void createNewReadingList() {
        new CreateReadingListController(researcher, papers);
    }

    private void openPapers() {
        new PaperListController(papers, researcher);
    }

    private void openResearchers() {
        new ResearcherListController(researchers);
    }
}

