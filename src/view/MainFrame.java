package view;

import javax.swing.*;

import model.Researcher;

import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private JButton btnViewResearcherList;
    private JButton btnViewPaperList;
    private JButton btnViewProfile;
    private JButton btnViewReadingList;
    
    
    public MainFrame(Researcher researcher) {
        super("OpenResearch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnViewResearcherList = new JButton("View Researchers");
        btnViewPaperList = new JButton("View Papers");
        btnViewProfile = new JButton("View Profile");
        btnViewReadingList = new JButton("View Reading Lists");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(btnViewResearcherList);
        add(btnViewPaperList);
        add(btnViewProfile);
        add(btnViewReadingList);

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public JButton getBtnViewResearcherList() {
        return btnViewResearcherList;
    }

    public JButton getBtnViewPaperList() {
        return btnViewPaperList;
    }

    public JButton getBtnViewProfile() {
        return btnViewProfile;
    }

    public JButton getBtnViewReadingList() {
        return btnViewReadingList;
    }
}

