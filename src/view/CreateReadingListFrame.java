package view;

import model.Paper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateReadingListFrame extends JFrame {
    private DefaultListModel<String> paperListModel;
    private JList<String> paperList;
    private JTextField readingListNameField;
    private JButton createButton;

    public CreateReadingListFrame() {
        super("Create Reading List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        paperListModel = new DefaultListModel<>();
        paperList = new JList<>(paperListModel);
        paperList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        readingListNameField = new JTextField();
        createButton = new JButton("Create Reading List");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel paperPanel = new JPanel(new BorderLayout());
        paperPanel.add(new JLabel("Select Paper(s):"), BorderLayout.NORTH);
        paperPanel.add(new JScrollPane(paperList), BorderLayout.CENTER);

        JPanel namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Reading List Name: ");
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(new JScrollPane(readingListNameField), BorderLayout.CENTER);

        mainPanel.add(paperPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(namePanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButton);

        setContentPane(mainPanel);
        setSize(1000, 600);
        setLocationRelativeTo(null); // center the frame
    }

    public void setPapers(List<Paper> papers) {
        paperListModel.clear();
        for (Paper paper : papers) {
            paperListModel.addElement(paper.getTitle());
        }
    }

    public JList<String> getPaperList() {
        return paperList;
    }

    public JTextField getReadingListNameField() {
        return readingListNameField;
    }

    public JButton getCreateButton() {
        return createButton;
    }
}
