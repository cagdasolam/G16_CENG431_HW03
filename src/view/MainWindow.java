package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JButton btnLogin;
    private JButton btnViewPapers;
    private JButton btnViewResearchers;

    public MainWindow() {
        super("OpenResearch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        btnLogin = new JButton("Log In");
        btnViewPapers = new JButton("View Papers");
        btnViewResearchers = new JButton("View Researchers");

        setLayout(new GridLayout(3, 1));

        add(btnLogin);
        add(btnViewPapers);
        add(btnViewResearchers);

        setLocationRelativeTo(null); // center the window
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnViewPapers() {
        return btnViewPapers;
    }

    public JButton getBtnViewResearchers() {
        return btnViewResearchers;
    }
}

