package controller;

import parser.CsvParser;
import parser.XmlParser;
import view.LoginFrame;
import view.MainWindow;

public class MainWindowController {
    private MainWindow mainWindow;
    private XmlParser xmlParser;
    private CsvParser csvParser;

    public MainWindowController(CsvParser csvParser, XmlParser xmlParser) {
        this.mainWindow = new MainWindow();
        this.xmlParser = xmlParser;
        this.csvParser = csvParser;

        mainWindow.getBtnLogin().addActionListener(e -> openLoginWindow());
        mainWindow.getBtnViewPapers().addActionListener(e -> openPaperList());
        mainWindow.getBtnViewResearchers().addActionListener(e -> openResearcherList());

        mainWindow.setVisible(true);
    }

    private void openLoginWindow() {
        new LoginController();
        mainWindow.dispose(); // close the main window
    }

    private void openPaperList() {
        new PaperListController(csvParser.getPapers("papersCsv.csv"));
    }

    private void openResearcherList() {
        new ResearcherListController(xmlParser.getResearchersFromXml());
    }
}

