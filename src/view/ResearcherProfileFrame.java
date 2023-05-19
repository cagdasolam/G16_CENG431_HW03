package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResearcherProfileFrame extends JFrame {
    private JLabel lblName;
    private DefaultListModel<String> followingListModel;
    private JList<String> followingList;
    private DefaultListModel<String> followersListModel;
    private JList<String> followersList;

    public ResearcherProfileFrame() {
        super("Researcher Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel();
        followingListModel = new DefaultListModel<>();
        followingList = new JList<>(followingListModel);
        followersListModel = new DefaultListModel<>();
        followersList = new JList<>(followersListModel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Researcher Name:"));
        add(lblName);
        add(new JLabel("Following:"));
        add(new JScrollPane(followingList));
        add(new JLabel("Followers:"));
        add(new JScrollPane(followersList));

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
}

