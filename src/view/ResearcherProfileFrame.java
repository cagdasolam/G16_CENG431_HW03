package view;

import javax.swing.*;
import javax.swing.border.Border;

import model.ReadingList;

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

    private final JButton btnLookReadingListDetails;
    private final JButton btnFollow;
    private final JButton btnUnFollow;

    public ResearcherProfileFrame() {
        super("Researcher Profile");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);

        lblName = new JLabel();
        followingListModel = new DefaultListModel<>();
        followingList = new JList<>(followingListModel);
        followersListModel = new DefaultListModel<>();
        followersList = new JList<>(followersListModel);
        readingListsModel = new DefaultListModel<>();
        readingLists = new JList<>(readingListsModel);
        readingLists.setCellRenderer(new ReadingListRenderer());

        btnLookReadingListDetails = new JButton("Look Details");
        btnFollow = new JButton("Follow");
        btnUnFollow = new JButton("Unfollow");
        btnLookReadingListDetails.setPreferredSize(new Dimension(120, btnLookReadingListDetails.getPreferredSize().height));
        btnFollow.setPreferredSize(new Dimension(120, btnFollow.getPreferredSize().height));
        btnUnFollow.setPreferredSize(new Dimension(120, btnUnFollow.getPreferredSize().height));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Researcher Name:");
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border border = BorderFactory.createLineBorder(Color.RED, 1);
        nameLabel.setBorder(border);
        profilePanel.setBorder(border);
        profilePanel.add(nameLabel);
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

        JPanel readingListButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel followButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));


        readingListButtonPanel.add(btnLookReadingListDetails);
        followButtonPanel.add(btnFollow);
        followButtonPanel.add(btnUnFollow);
        mainPanel.add(readingListButtonPanel, BorderLayout.SOUTH);
        mainPanel.add(followButtonPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
        setSize(1000, 600);
        setLocationRelativeTo(null); // center the frame

        // Add component listener for responsive behavior
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
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

    public JButton getBtnLookReadingListDetails() {
        return btnLookReadingListDetails;
    }

    public JButton getBtnFollow() {
        return btnFollow;
    }

    public JButton getBtnUnFollow() {
        return btnUnFollow;
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

    public void addFollowing(String name) {
        followingListModel.addElement(name);
    }

    public void removeFollowing(String name) {
        followingListModel.removeElement(name);
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