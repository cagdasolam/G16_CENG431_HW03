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
        readingListNameField = new JTextField(20);
        createButton = new JButton("Create Reading List");

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(new JLabel("Select Paper(s):"), gbc);
        gbc.weighty = 1;
        add(new JScrollPane(paperList), gbc);

        gbc.weighty = 0;
        gbc.gridwidth = 1;
        add(new JLabel("New reading list name:"), gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        readingListNameField.setPreferredSize(new Dimension(300, readingListNameField.getPreferredSize().height));
        add(readingListNameField, gbc);

        createButton.setPreferredSize(new Dimension(150, createButton.getPreferredSize().height));
        gbc.fill = GridBagConstraints.NONE;
        add(createButton, gbc);

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