package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReadingListFrame extends JFrame {
    private DefaultListModel<String> readingListModel;
    private JList<String> readingList;

    public ReadingListFrame() {
        super("Reading Lists");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        readingListModel = new DefaultListModel<>();
        readingList = new JList<>(readingListModel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Reading Lists:"));
        add(new JScrollPane(readingList));

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setReadingList(List<String> readingLists) {
        readingListModel.clear();
        for (String name : readingLists) {
            readingListModel.addElement(name);
        }
    }
}

