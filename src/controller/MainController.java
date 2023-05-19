package controller;

import model.Researcher;
import parser.CsvParser;
import parser.XmlParser;
import view.MainFrame;

public class MainController {
    private MainFrame mainFrame;
    private Researcher researcher; // the currently logged-in researcher
    private XmlParser xmlParser;

    public MainController(Researcher researcher) {
        this.mainFrame = new MainFrame(researcher);
        this.researcher = researcher;
        this.xmlParser = new XmlParser();

        mainFrame.getBtnViewResearcherList().addActionListener(e -> viewResearcherList());
        mainFrame.getBtnViewPaperList().addActionListener(e -> viewPaperList());
        mainFrame.getBtnViewProfile().addActionListener(e -> viewProfile());
        mainFrame.getBtnViewReadingList().addActionListener(e -> viewReadingList());

        mainFrame.setVisible(true);
    }

    private void viewResearcherList() {
        new ResearcherListController(xmlParser.getResearchersFromXml());
    }

    private void viewPaperList() {
        /*new PaperListController(CsvParser.getPapers("papersCsv.csv"));*/
    }

    private void viewProfile() {
        new ResearcherProfileController(researcher);
    }

    private void viewReadingList() {
        /*new ReadingListController(researcher);*/
    }
}

