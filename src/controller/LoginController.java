package controller;

import javax.swing.*;

import model.Researcher;
import parser.XmlParser;
import view.LoginFrame;

import java.util.List;

public class LoginController {

    public LoginController() {
        LoginFrame loginFrame = new LoginFrame();

        loginFrame.getBtnSubmit().addActionListener(e -> {
            String enteredUsername = loginFrame.getTfUsername().getText();
            String enteredPassword = new String(loginFrame.getPfPassword().getPassword());
            
            XmlParser xmlParser = new XmlParser();
            List<Researcher> researchers = xmlParser.getResearchersFromXml();

            for (Researcher researcher : researchers) {
                if (enteredUsername.equals(researcher.getName()) && enteredPassword.equals(researcher.getPassword())) {
                    // Valid credentials, login successful
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");

                    // Launch the MainFrame
                    SwingUtilities.invokeLater(() -> new MainController(researcher));
                    loginFrame.dispose();               
                    
                    return;
                }
            }

            // credentials were invalid
            JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password.");
        });
        
        loginFrame.setVisible(true);
    }
}


