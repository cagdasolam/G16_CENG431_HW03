package controller;

import parser.CsvParser;
import parser.XmlParser;
import view.LoginFrame;
import view.MainWindow;

public class MainWindowController {
    private MainWindow mainWindow;
    private LoginFrame loginFrame;
    private XmlParser xmlParser;

    public MainWindowController() {
        this.mainWindow = new MainWindow();
        this.loginFrame = new LoginFrame();
        this.xmlParser = new XmlParser();

        mainWindow.getBtnLogin().addActionListener(e -> openLoginWindow());
        mainWindow.getBtnViewPapers().addActionListener(e -> openPaperList());
        mainWindow.getBtnViewResearchers().addActionListener(e -> openResearcherList());

        mainWindow.setVisible(true);
    }

    private void openLoginWindow() {
        new LoginController(loginFrame, xmlParser);
        mainWindow.dispose(); // close the main window
    }

    private void openPaperList() {
    	CsvParser csvParser = new CsvParser();
        new PaperListController(csvParser.getPapers("papersCsv.csv"));
    }

    private void openResearcherList() {
        new ResearcherListController(xmlParser.getResearchersFromXml());
    }
}

