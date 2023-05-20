package view;

import model.Researcher;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private JButton btnViewProfile;
	private JButton createNewReadingList;
	private JButton btnViewPapers;
	private JButton btnViewResearchers;

	public MainFrame(Researcher researcher) {
		super("OpenResearch - Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// Header Panel
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel lblGreeting = new JLabel("Hello " + researcher.getName() + "!");
		lblGreeting.setFont(new Font("Arial", Font.BOLD, 18));
		btnViewProfile = new JButton("View Profile");
		headerPanel.add(lblGreeting, BorderLayout.WEST);
		headerPanel.add(btnViewProfile, BorderLayout.EAST);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);

		createNewReadingList = new JButton("Create new Reading List");
		btnViewPapers = new JButton("View Papers");
		btnViewResearchers = new JButton("View Researchers");

		contentPanel.add(createNewReadingList, gbc);
		gbc.gridy++;
		contentPanel.add(btnViewPapers, gbc);
		gbc.gridy++;
		contentPanel.add(btnViewResearchers, gbc);

		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		setContentPane(mainPanel);
		setPreferredSize(new Dimension(800, 600)); // Set the default size of the frame
		pack();
		setLocationRelativeTo(null); // Center the frame

		// Add component listener for responsive behavior
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				adjustButtonSize();
			}
		});
	}

	private void adjustButtonSize() {
		int buttonWidth = getWidth() / 3 - 30;
		int buttonHeight = 50;

		createNewReadingList.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		btnViewPapers.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		btnViewResearchers.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

		revalidate();
		repaint();
	}

	public JButton getBtnViewProfile() {
		return btnViewProfile;
	}

	public JButton getCreateNewReadingList() {
		return createNewReadingList;
	}

	public JButton getBtnViewPapers() {
		return btnViewPapers;
	}

	public JButton getBtnViewResearchers() {
		return btnViewResearchers;
	}
}

