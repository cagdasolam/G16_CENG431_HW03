package observer;

import controller.ResearcherController;
import generator.PaperGenerator;
import model.Paper;
import model.Researcher;
import view.MainFrame;

import java.io.IOException;
import java.util.List;

public class MainFrameObserver {
    private MainFrame mainFrame;
    private Researcher loggedResearcher; // the currently logged-in researcher
    private List<Researcher> researchers;
    private List<Paper> papers;
    private ResearcherController researcherController;

    public MainFrameObserver(Researcher loggedResearcher) throws IOException {
        this.mainFrame = new MainFrame(loggedResearcher);
        this.loggedResearcher = loggedResearcher;
        this.researcherController = new ResearcherController();
        this.researchers = researcherController.getResearchers();
        this.papers = new PaperGenerator().getPapersFromCsv();
        
        initController();
        mainFrame.setVisible(true);
    }
    
    private void initController() {
        mainFrame.getBtnViewProfile().addActionListener(e -> openProfile());
        mainFrame.getCreateNewReadingList().addActionListener(e -> createNewReadingList());
        mainFrame.getBtnViewPapers().addActionListener(e -> openPapers());
        mainFrame.getBtnViewResearchers().addActionListener(e -> openResearchers());
    }

    private void openProfile() {
        new ResearcherProfileFrameObserver(loggedResearcher, loggedResearcher);
    }

    private void createNewReadingList() {
        new CreateReadingListFrameObserver(loggedResearcher, papers);
    }

    private void openPapers() {

        new ReadingListFrameObserver("All Papers", papers, loggedResearcher);

    }

    private void openResearchers() {
        new ResearcherListFrameObserver(researchers, loggedResearcher);
    }
}

