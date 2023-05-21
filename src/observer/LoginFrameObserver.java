package observer;

import javax.swing.*;

import controller.ResearcherController;
import model.Researcher;
import view.LoginFrame;

import java.io.IOException;

public class LoginFrameObserver {
    private LoginFrame loginFrame;
    private ResearcherController researcherController;

    public LoginFrameObserver(LoginFrame loginFrame, ResearcherController researcherController) {
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
                SwingUtilities.invokeLater(() -> {
                    try {
                        new MainFrameObserver(researcher);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                loginFrame.dispose();
            }else {
                // If we reached here, it means the credentials were invalid
                JOptionPane.showMessageDialog(loginFrame, "Invalid Username/Password.");
            }

        });
        
        loginFrame.setVisible(true);
    }
}


