package view;

import javax.swing.*;
import model.Paper;
import model.ReadingList;

import java.awt.*;
import java.util.List;

public class PaperDetailFrame extends JFrame {
    private JLabel lblTitle;
    private JLabel lblAuthors;
    private JLabel lblYear;
    private JLabel lblDOI;
    private JButton btnDownload;
    private JComboBox<String> readingListComboBox;
    private JButton btnAddToReadingList;

    public PaperDetailFrame() {
        super("Paper Detail");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel();
        lblAuthors = new JLabel();
        lblYear = new JLabel();
        lblDOI = new JLabel();
        btnDownload = new JButton("Download Paper");
        readingListComboBox = new JComboBox<>();
        btnAddToReadingList = new JButton("Add to Reading List");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Title:"));
        add(lblTitle);
        add(new JLabel("Authors:"));
        add(lblAuthors);
        add(new JLabel("Year:"));
        add(lblYear);
        add(new JLabel("DOI:"));
        add(lblDOI);
        add(btnDownload);
        add(new JLabel("Add to Reading List:"));
        add(readingListComboBox);
        add(btnAddToReadingList);

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void displayPaperDetails(Paper paper) {
        lblTitle.setText(paper.getTitle());
        lblAuthors.setText(String.join(", ", paper.getAuthors()));
        lblYear.setText(String.valueOf(paper.getYear()));
        lblDOI.setText(paper.getDOI());
    }

    public JButton getBtnDownload() {
        return btnDownload;
    }

    public JComboBox<String> getReadingListComboBox() {
        return readingListComboBox;
    }

    public JButton getBtnAddToReadingList() {
        return btnAddToReadingList;
    }
}
