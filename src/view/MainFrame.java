package view;

import javax.swing.*;

import model.Researcher;

import java.awt.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private JButton btnViewProfile;
    private JButton btnViewReadingLists;
    private JButton btnViewPapers;
    private JButton btnViewResearchers;

    public MainFrame(Researcher researcher) {
        super("OpenResearch - Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel lblGreeting = new JLabel("Hello " + researcher.getName() + "!");
        btnViewProfile = new JButton("View Profile");
        headerPanel.add(lblGreeting, BorderLayout.WEST);
        headerPanel.add(btnViewProfile, BorderLayout.EAST);
        
        // Content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        btnViewReadingLists = new JButton("Your Reading Lists");
        btnViewPapers = new JButton("See Papers");
        btnViewResearchers = new JButton("View Researchers");

        contentPanel.add(btnViewReadingLists);
        contentPanel.add(btnViewPapers);
        contentPanel.add(btnViewResearchers);

        // Add header and content to main frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public JButton getBtnViewProfile() {
        return btnViewProfile;
    }

    public JButton getBtnViewReadingLists() {
        return btnViewReadingLists;
    }

    public JButton getBtnViewPapers() {
        return btnViewPapers;
    }

    public JButton getBtnViewResearchers() {
        return btnViewResearchers;
    }
}
