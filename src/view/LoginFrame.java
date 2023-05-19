package view;

import java.awt.Dimension;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnSubmit;

    public LoginFrame() {
    	setTitle("OpenResearch - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        tfUsername = new JTextField(20);
        pfPassword = new JPasswordField(20);
        btnSubmit = new JButton("Submit");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Username:"));
        panel.add(tfUsername);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // To create some space between components
        panel.add(new JLabel("Password:"));
        panel.add(pfPassword);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(btnSubmit);
        
        this.getContentPane().add(panel);

        setLocationRelativeTo(null); // center the frame
    }

    public JTextField getTfUsername() {
        return tfUsername;
    }

    public JPasswordField getPfPassword() {
        return pfPassword;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }
}

