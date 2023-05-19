package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResearcherListFrame extends JFrame {
    private DefaultListModel<String> researcherListModel;
    private JList<String> researcherList;

    public ResearcherListFrame() {
        super("Researcher List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        researcherListModel = new DefaultListModel<>();
        researcherList = new JList<>(researcherListModel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Researchers:"));
        add(new JScrollPane(researcherList));

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setResearchers(List<String> researchers) {
        researcherListModel.clear();
        for (String name : researchers) {
            researcherListModel.addElement(name);
        }
    }

    public JList<String> getResearcherList() {
        return researcherList;
    }
}

