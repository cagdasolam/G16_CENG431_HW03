package controller;

import java.util.List;

import model.Paper;
import model.Researcher;
import parser.CsvParser;
import parser.XmlParser;
import view.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private Researcher researcher; // the currently logged-in researcher
    private XmlParser xmlParser;
    private List<Researcher> researchers;
    private List<Paper> papers;

    public MainController(Researcher researcher) {
        this.mainFrame = new MainFrame(researcher);
        this.researcher = researcher;
        this.xmlParser = new XmlParser();
        this.researchers = xmlParser.getResearchersFromXml();
        this.papers = new CsvParser().getPapers("papersCsv.csv");
        
        initController();

        mainFrame.setVisible(true);
    }
    
    private void initController() {
        mainFrame.getBtnViewProfile().addActionListener(e -> openProfile(researcher));
        mainFrame.getBtnViewReadingLists().addActionListener(e -> openReadingLists());
        mainFrame.getBtnViewPapers().addActionListener(e -> openPapers());
        mainFrame.getBtnViewResearchers().addActionListener(e -> openResearchers());
    }

    private void openProfile(Researcher researcher) {
    	
        for (Researcher r : researchers) {
            if (r.getName().equals(researcher.getName())) {
                new ResearcherProfileController(r); 
                break;
            }
        }
    }

    private void openReadingLists() {
        // Open reading list view here
    }

    private void openPapers() {
        new PaperListController(papers, researcher);
    }

    private void openResearchers() {
        // Open researchers view here
    }
}

