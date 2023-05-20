package view;

import model.Paper;
import model.Researcher;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResearcherListFrame extends JFrame {
    private DefaultListModel<Researcher> researcherListModel;
    private JList<Researcher> researcherList;
    private final JButton btnResearcher;


    public ResearcherListFrame() {
        super("Researcher List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        researcherListModel = new DefaultListModel<>();
        researcherList = new JList<>(researcherListModel);
        researcherList.setCellRenderer(new ResearcherCellRenderer());
        btnResearcher = new JButton("Look Profile");
        btnResearcher.setPreferredSize(new Dimension(120, btnResearcher.getPreferredSize().height));


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnResearcher);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Researchers:"));
        add(new JScrollPane(researcherList));

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setResearchers(List<Researcher> researchers) {
        researcherListModel.clear();
        for (Researcher researcher : researchers) {
            researcherListModel.addElement(researcher);
        }
    }

    public JList<Researcher> getResearcherList() {
        return researcherList;
    }

    public JButton getBtnResearcher() {
        return btnResearcher;
    }


    private static class ResearcherCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Researcher researcher) {
                setText(researcher.getName());
            }

            return this;
        }
    }
}

