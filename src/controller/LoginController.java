package controller;

import javax.swing.*;

import model.Researcher;
import parser.XmlParser;
import view.LoginFrame;
import view.MainFrame;
import view.ResearcherProfileFrame;

import java.util.List;

public class LoginController {
    private LoginFrame loginFrame;
//    private XmlParser xmlParser;

    private ResearcherController researcherController;

    public LoginController(LoginFrame loginFrame, ResearcherController researcherController) {
        this.loginFrame = loginFrame;
        loginFrame.setVisible(true);
        this.researcherController = researcherController;
        loginFrame.getBtnSubmit().addActionListener(e -> {
            String enteredUsername = loginFrame.getTfUsername().getText();
            String enteredPassword = new String(loginFrame.getPfPassword().getPassword());

            if (researcherController.login(enteredUsername, enteredPassword)){
                // Valid credentials, login successful
                JOptionPane.showMessageDialog(loginFrame, "Login Successful!");

                // Launch the MainFrame
                SwingUtilities.invokeLater(() -> new MainFrame(researcherController.getResearcher(enteredUsername)).setVisible(true));
                loginFrame.dispose();
            }else {
                // If we reached here, it means the credentials were invalid
                JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password.");
            }
        });
    }
}


