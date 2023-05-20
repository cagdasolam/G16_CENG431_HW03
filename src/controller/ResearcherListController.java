package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import model.ReadingList;
import model.Researcher;
import view.ResearcherListFrame;
import view.ResearcherProfileFrame;

import javax.swing.*;

public class ResearcherListController {
    private ResearcherListFrame researcherListFrame;
    private List<Researcher> researchers;
    private ResearcherProfileController researcherProfileController;
    private ResearcherProfileFrame researcherProfileFrame;


    public ResearcherListController(List<Researcher> researchers) {
        this.researcherProfileFrame = new ResearcherProfileFrame();
        this.researcherListFrame = new ResearcherListFrame();
        this.researchers = researchers;

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

    private void openResearcherProfile(Researcher researcher) {
        new ResearcherProfileController(researcher);
    }

    private void showResearcherList() {
        Researcher selectedResearcher =  researcherListFrame.getResearcherList().getSelectedValue();

        if (selectedResearcher != null) {
            openResearcherProfile(selectedResearcher);
        } else {
            JOptionPane.showMessageDialog(researcherProfileFrame, "Please select a researcher list");
        }
    }
}
