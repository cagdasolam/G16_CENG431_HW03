package observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Researcher;
import view.ResearcherListFrame;
import view.ResearcherProfileFrame;

import javax.swing.*;

public class ResearcherListFrameObserver {
    private ResearcherListFrame researcherListFrame;
    private List<Researcher> researchers;
    private ResearcherProfileFrameObserver researcherProfileController;
    private ResearcherProfileFrame researcherProfileFrame;
    private Researcher loggedResearcher;


    public ResearcherListFrameObserver(List<Researcher> researchers, Researcher loggedResearcher) {
        this.researcherProfileFrame = new ResearcherProfileFrame();
        this.researcherListFrame = new ResearcherListFrame();
        this.researchers = researchers;
        this.loggedResearcher = loggedResearcher;

        loadResearchers();
        researcherListFrame.getBtnResearcher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               showResearcherList();
            }
        });
        researcherListFrame.setVisible(true);
    }

    private void loadResearchers() {
        researcherListFrame.setResearchers(researchers);
    }

    private void openResearcherProfile(Researcher loggedResearcher, Researcher selectedResearcher) {
        new ResearcherProfileFrameObserver(loggedResearcher, selectedResearcher);
    }

    private void showResearcherList() {
        Researcher selectedResearcher =  researcherListFrame.getResearcherList().getSelectedValue();

        if (selectedResearcher != null) {
            openResearcherProfile(loggedResearcher, selectedResearcher);
        } else {
            JOptionPane.showMessageDialog(researcherProfileFrame, "Please select a researcher list");
        }
    }


}
