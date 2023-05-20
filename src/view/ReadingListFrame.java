package view;

import model.Paper;
import model.ReadingList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReadingListFrame extends JFrame {
    private DefaultListModel<Paper> paperJlistModel;
    private JList<Paper> paperJlist;

    public ReadingListFrame(ReadingList readingList) {
        super("Reading Lists");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        paperJlistModel = new DefaultListModel<>();
        paperJlist = new JList<>(paperJlistModel);
        paperJlist.setCellRenderer(new PaperCellRenderer());

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Reading Lists:"));

        List<Paper> paperList = readingList.getPapers();
        add(new JScrollPane(paperJlist));

        pack();
        setLocationRelativeTo(null); // center the frame
    }

    public void setReadingList(ReadingList readingList) {
        paperJlistModel.clear();
        for (Paper paper : readingList.getPapers()) {
            paperJlistModel.addElement(paper);
        }
    }

    private static class PaperCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Paper paper) {
                setText(paper.getTitle());
            }

            return this;
        }
    }
}

