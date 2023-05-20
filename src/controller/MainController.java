package controller;

import model.Researcher;
import view.MainFrame;

import java.util.List;

public class MainController {
	private MainFrame mainFrame;
	private Researcher researcher; // the currently logged-in researcher
	private List<Researcher> researchers;
	private ResearcherController researcherController;

	public MainController(Researcher researcher) {
		this.mainFrame = new MainFrame(researcher);
		this.researcher = researcher;
		this.researcherController = new ResearcherController();
		this.researchers = researcherController.getResearchers();

		initController();

		mainFrame.setVisible(true);
	}

	private void initController() {
		mainFrame.getBtnViewProfile().addActionListener(e -> openProfile(researcher));
		mainFrame.getBtnViewReadingLists().addActionListener(e -> openReadingLists());
		mainFrame.getBtnViewPapers().addActionListener(e -> openPapers());
		mainFrame.getBtnViewResearchers().addActionListener(e -> openResearchers());
	}

	private void openProfile(Researcher researcher) {
		new ResearcherProfileController(researcher);
	}

	private void openReadingLists() {
		// Open reading list view here
	}

	private void openPapers() {
		// Open papers view here
	}

	private void openResearchers() {
		// Open researchers view here
	}
}

