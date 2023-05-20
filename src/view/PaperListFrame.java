package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaperListFrame extends JFrame {
    private DefaultListModel<String> paperListModel;
    private JList<String> paperList;
    private JButton btnViewDetails;

    public PaperListFrame() {
        super("Paper List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        paperListModel = new DefaultListModel<>();
        paperList = new JList<>(paperListModel);
        btnViewDetails = new JButton("View Details");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Papers:"));
        add(new JScrollPane(paperList));
        add(btnViewDetails);

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setPapers(List<String> papers) {
        paperListModel.clear();
        for (String name : papers) {
            paperListModel.addElement(name);
        }
    }
    
    public JButton getBtnViewDetails() {
        return btnViewDetails;
    }

    public JList<String> getPaperList() {
        return paperList;
    }
    
    
}

