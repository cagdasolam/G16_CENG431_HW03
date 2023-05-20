package view;

import javax.swing.*;

import model.ReadingList;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResearcherProfileFrame extends JFrame {
    private final JLabel lblName;
    private final DefaultListModel<String> followingListModel;
    private final DefaultListModel<String> followersListModel;
    private final DefaultListModel<ReadingList> readingListsModel;
    private final JList<ReadingList> readingLists;
    private final JList<String> followersList;
    private final JList<String> followingList;

    private final JButton btnLookDetails;

    public ResearcherProfileFrame() {
        super("Researcher Profile");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lblName = new JLabel();
        followingListModel = new DefaultListModel<>();
        followingList = new JList<>(followingListModel);
        followersListModel = new DefaultListModel<>();
        followersList = new JList<>(followersListModel);
        readingListsModel = new DefaultListModel<>();
        readingLists = new JList<>(readingListsModel);
        readingLists.setCellRenderer(new ReadingListRenderer());

        btnLookDetails = new JButton("Look Details");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        profilePanel.add(new JLabel("Researcher Name:"));
        profilePanel.add(lblName);
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(new JLabel("Following:"));
        profilePanel.add(new JScrollPane(followingList));
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(new JLabel("Followers:"));
        profilePanel.add(new JScrollPane(followersList));
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(new JLabel("Reading Lists:"));
        profilePanel.add(new JScrollPane(readingLists));

        mainPanel.add(profilePanel, BorderLayout.CENTER);
        mainPanel.add(btnLookDetails, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // center the frame

        // Add component listener for responsive behavior
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustListSize();
            }
        });
    }

    public void setName(String name) {
        lblName.setText(name);
    }

    public void setFollowing(List<String> following) {
        followingListModel.clear();
        for (String name : following) {
            followingListModel.addElement(name);
        }
    }

    public void setFollowers(List<String> followers) {
        followersListModel.clear();
        for (String name : followers) {
            followersListModel.addElement(name);
        }
    }

    public void setReadingLists(List<ReadingList> readingLists) {
        readingListsModel.clear();
        for (ReadingList readingList : readingLists) {
            readingListsModel.addElement(readingList);
        }
    }

    public JButton getBtnLookDetails() {
        return btnLookDetails;
    }

    public JList<ReadingList> getReadingLists() {
        return readingLists;
    }

    private void adjustListSize() {
        int listWidth = getWidth() - 40;
        int listHeight = getHeight() / 3;

        followingList.setPreferredSize(new Dimension(listWidth, listHeight));
        followersList.setPreferredSize(new Dimension(listWidth, listHeight));
        readingLists.setPreferredSize(new Dimension(listWidth, listHeight));

        revalidate();
        repaint();
    }

    private static class ReadingListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof ReadingList readingList) {
                setText(readingList.getReadingListName());
            }

            return this;
        }
    }
}
