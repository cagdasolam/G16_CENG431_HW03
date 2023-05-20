package controller;

import javax.swing.*;

import model.Researcher;
import parser.XmlParser;
import view.LoginFrame;

import java.util.List;

public class LoginController {
    private LoginFrame loginFrame;
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

                Researcher researcher = researcherController.getResearcher(enteredUsername);
                // Launch the MainFrame
                SwingUtilities.invokeLater(() -> new MainController(researcher));
                loginFrame.dispose();
            }else {
                // If we reached here, it means the credentials were invalid
                JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password.");
            }

        });
        
        loginFrame.setVisible(true);
    }
}


