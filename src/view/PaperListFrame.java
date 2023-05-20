package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaperListFrame extends JFrame {
    private DefaultListModel<String> paperListModel;
    private JList<String> paperList;
    private JButton btnViewDetails;

    public PaperListFrame() {
        super("Paper List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        paperListModel = new DefaultListModel<>();
        paperList = new JList<>(paperListModel);
        btnViewDetails = new JButton("View Details");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());

        listPanel.add(new JLabel("Papers:"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(paperList), BorderLayout.CENTER);

        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(btnViewDetails, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(1000, 600);
        setLocationRelativeTo(null); // center the frame
    }

    public void setPapers(List<String> papers) {
        paperListModel.clear();
        for (String name : papers) {
            paperListModel.addElement(name);
        }
    }

    public JButton getBtnViewDetails() {
        return btnViewDetails;
    }

    public JList<String> getPaperList() {
        return paperList;
    }
}
