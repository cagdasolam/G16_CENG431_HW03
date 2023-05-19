package view;

import javax.swing.*;

import model.Paper;

import java.awt.*;

public class PaperFrame extends JFrame {
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblYear;
    private JLabel lblDOI;

    public PaperFrame() {
        super("Paper Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel();
        lblAuthor = new JLabel();
        lblYear = new JLabel();
        lblDOI = new JLabel();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Title:"));
        add(lblTitle);
        add(new JLabel("Author(s):"));
        add(lblAuthor);
        add(new JLabel("Year:"));
        add(lblYear);
        add(new JLabel("DOI:"));
        add(lblDOI);

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setPaperDetails(Paper paper) {
        lblTitle.setText(paper.getTitle());
        lblAuthor.setText(String.join(", ", paper.getAuthors()));
        lblYear.setText(String.valueOf(paper.getYear()));
        lblDOI.setText(paper.getDOI());
    }
}

