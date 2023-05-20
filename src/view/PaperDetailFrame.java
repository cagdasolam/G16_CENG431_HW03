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
    private JComboBox<ReadingList> readingListComboBox;
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
        readingListComboBox.setRenderer(new ReadingListRenderer());
        btnAddToReadingList = new JButton("Add to Reading List");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        infoPanel.add(lblTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Authors:"), gbc);

        gbc.gridx = 1;
        infoPanel.add(lblAuthors, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Year:"), gbc);

        gbc.gridx = 1;
        infoPanel.add(lblYear, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(new JLabel("DOI:"), gbc);

        gbc.gridx = 1;
        infoPanel.add(lblDOI, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnDownload);
        buttonPanel.add(new JLabel("Add to Reading List:"));
        buttonPanel.add(readingListComboBox);
        buttonPanel.add(btnAddToReadingList);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setSize(750, 300);
        setLocationRelativeTo(null); // center the frame
    }

    public void displayPaperDetails(Paper paper) {
        lblTitle.setText(paper.getTitle());
        lblAuthors.setText(String.join(", ", paper.getAuthors()));
        lblYear.setText(String.valueOf(paper.getYear()));
        lblDOI.setText(paper.getDOI());
    }

    public void setReadingLists(List<ReadingList> readingLists) {
        readingListComboBox.removeAllItems();
        for (ReadingList readingList : readingLists) {
            readingListComboBox.addItem(readingList);
        }
    }

    public JButton getBtnDownload() {
        return btnDownload;
    }

    public JComboBox<ReadingList> getReadingListComboBox() {
        return readingListComboBox;
    }

    public JButton getBtnAddToReadingList() {
        return btnAddToReadingList;
    }

    public class ReadingListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof ReadingList readingList) {
                setText(readingList.getReadingListName());
            }

            return this;
        }
    }
}