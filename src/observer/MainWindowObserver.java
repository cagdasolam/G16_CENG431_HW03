package observer;

import controller.ResearcherController;
import parser.JsonParser;
import view.LoginFrame;
import view.MainWindow;

import javax.swing.*;

public class MainWindowObserver {
    private MainWindow mainWindow;
    private LoginFrame loginFrame;
    private ResearcherController researcherController;


    public MainWindowObserver() {
        JsonParser jsonParser = new JsonParser();
        jsonParser.createJsonFile();
        this.mainWindow = new MainWindow();
        this.loginFrame = new LoginFrame();
        this.researcherController = new ResearcherController();

        mainWindow.getBtnLogin().addActionListener(e -> openLoginWindow());
        mainWindow.getBtnExit().addActionListener(e -> exitProgram());

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 600);

        mainWindow.setVisible(true);
    }

    private void exitProgram() {
        mainWindow.dispose();
    }

    private void openLoginWindow() {
        new LoginFrameObserver(loginFrame, researcherController);
        mainWindow.dispose(); // close the main window
    }
}