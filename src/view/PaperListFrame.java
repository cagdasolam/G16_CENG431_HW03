package view;

import model.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaperListFrame extends JFrame {
    private DefaultListModel<String> paperListModel;
    private JList<String> paperList;
    private JButton btnViewDetails;
    private JButton btnRemovePaper;

    public PaperListFrame(String readingListName) {
        super(readingListName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        paperListModel = new DefaultListModel<>();
        paperList = new JList<>(paperListModel);
        btnViewDetails = new JButton("View Details");
        btnRemovePaper = new JButton("Remove Selected Paper");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnViewDetails);
        buttonPanel.add(btnRemovePaper);

        listPanel.add(new JLabel("Papers:"), BorderLayout.NORTH);
        listPanel.add(new JScrollPane(paperList), BorderLayout.CENTER);

        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

    public JButton getBtnRemovePaper() {
        return btnRemovePaper;
    }

    public void setOwner(boolean isOwner) {
        btnRemovePaper.setVisible(isOwner);
    }

    public void addPaper(Paper paper){
        paperListModel.addElement(paper.getTitle());
    }

    public void removePaper(Paper paper){
        paperListModel.removeElement(paper.getTitle());
    }

}
