package controller;

import parser.CsvParser;
import parser.XmlParser;
import view.LoginFrame;
import view.MainWindow;

import javax.swing.*;

public class MainWindowController {
    private MainWindow mainWindow;
    private LoginFrame loginFrame;
    private ResearcherController researcherController;


    public MainWindowController(CsvParser csvParser, XmlParser xmlParser) {
        this.mainWindow = new MainWindow();
        this.loginFrame = new LoginFrame();
        this.researcherController = new ResearcherController();

        mainWindow.getBtnLogin().addActionListener(e -> openLoginWindow());

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 600);

        mainWindow.setVisible(true);
    }

    private void openLoginWindow() {
        new LoginController(loginFrame, researcherController);
        mainWindow.dispose(); // close the main window
    }

    private void openPaperList() {
        new PaperListController(csvParser.getPapers("papersCsv.csv"));
    }

    private void openResearcherList() {
        new ResearcherListController(researcherController.getResearchers());
    }
}

