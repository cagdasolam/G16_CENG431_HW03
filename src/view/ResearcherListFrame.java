package view;

import model.Researcher;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResearcherListFrame extends JFrame {
	private DefaultListModel<Researcher> researcherListModel;
	private JList<Researcher> researcherList;
	private JButton btnResearcher;

	public ResearcherListFrame() {
		super("Researcher List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		researcherListModel = new DefaultListModel<>();
		researcherList = new JList<>(researcherListModel);
		researcherList.setCellRenderer(new ResearcherCellRenderer());
		btnResearcher = new JButton("View Profile");
		btnResearcher.setPreferredSize(new Dimension(120, btnResearcher.getPreferredSize().height));

		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel listPanel = new JPanel(new BorderLayout());

		listPanel.add(new JLabel("Researchers:"), BorderLayout.NORTH);
		listPanel.add(new JScrollPane(researcherList), BorderLayout.CENTER);

		mainPanel.add(listPanel, BorderLayout.CENTER);
		mainPanel.add(btnResearcher, BorderLayout.SOUTH);

		setContentPane(mainPanel);
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
