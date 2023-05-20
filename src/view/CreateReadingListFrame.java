package view;

import model.Paper;

import javax.swing.*;
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

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Select Paper(s):"));
        add(new JScrollPane(paperList));
        add(new JLabel("Reading List Name:"));
        add(readingListNameField);
        add(createButton);

        pack();
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
