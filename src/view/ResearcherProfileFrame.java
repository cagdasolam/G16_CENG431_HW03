package view;

import javax.swing.*;

import model.ReadingList;

import java.awt.*;
import java.util.List;

public class ResearcherProfileFrame extends JFrame {
    private JLabel lblName;
    private DefaultListModel<String> followingListModel;
    private JList<String> followingList;
    private DefaultListModel<String> followersListModel;
    private JList<String> followersList;
    private DefaultListModel<String> readingListsModel;
    private JList<String> readingLists;
    
    private JButton btnLookDetails;

    public ResearcherProfileFrame() {
        super("Researcher Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel();
        followingListModel = new DefaultListModel<>();
        followingList = new JList<>(followingListModel);
        followersListModel = new DefaultListModel<>();
        followersList = new JList<>(followersListModel);
        readingListsModel = new DefaultListModel<>();
        readingLists = new JList<>(readingListsModel);
        
        btnLookDetails = new JButton("Look Details");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Researcher Name:"));
        add(lblName);
        add(new JLabel("Following:"));
        add(new JScrollPane(followingList));
        add(new JLabel("Followers:"));
        add(new JScrollPane(followersList));
        add(new JLabel("Reading Lists:"));
        add(new JScrollPane(readingLists));
        
        add(btnLookDetails);
        pack();
        setLocationRelativeTo(null); // center the frame
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
            readingListsModel.addElement(readingList.getReadingListName());
        }
    }
    
    public JButton getBtnLookDetails() {
        return btnLookDetails;
    }

    public JList<String> getReadingLists() {
        return readingLists;
    }
}

