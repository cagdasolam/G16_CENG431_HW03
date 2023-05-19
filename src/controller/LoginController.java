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
    private XmlParser xmlParser;

    public LoginController(LoginFrame loginFrame, XmlParser xmlParser) {
        this.loginFrame = loginFrame;
        loginFrame.setVisible(true);
        this.xmlParser = xmlParser;
        loginFrame.getBtnSubmit().addActionListener(e -> {
            String enteredUsername = loginFrame.getTfUsername().getText();
            String enteredPassword = new String(loginFrame.getPfPassword().getPassword());

            List<Researcher> researchers = xmlParser.getResearchersFromXml();

            for (Researcher researcher : researchers) {
                if (enteredUsername.equals(researcher.getName()) && enteredPassword.equals(researcher.getPassword())) {
                    // Valid credentials, login successful
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");

                    // Launch the MainFrame
                    SwingUtilities.invokeLater(() -> new MainFrame(researcher).setVisible(true));
                    loginFrame.dispose();
                    
                    /*SwingUtilities.invokeLater(() -> {
                    	ResearcherProfileFrame researcherProfileFrame = new ResearcherProfileFrame();
                    	new ResearcherProfileController(researcher);
                    	researcherProfileFrame.setVisible(true);
            	    });*/
                    
                    return;
                }
            }

            // If we reached here, it means the credentials were invalid
            JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password.");
        });
    }
}


