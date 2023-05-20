package controller;

import model.Researcher;
import parser.XmlParser;

import java.util.List;

public class ResearcherController {

	XmlParser xmlParser = new XmlParser();

	public List<Researcher> getResearchers(){
		return xmlParser.getResearchersFromXml();
	}

	public boolean login(String userName, String password){
		List<Researcher> researchers = getResearchers();
		return researchers.stream()
				.anyMatch(researcher -> researcher.getName().equals(userName) && researcher.getPassword().equals(password));
	}

	public Researcher getResearcher(String name){
		List<Researcher> researchers = getResearchers();
		return researchers.stream().filter(researcher -> researcher.getName().equals(name)).findFirst().orElse(null);
	}

	public boolean followResearcher(Researcher follower, Researcher followed){
		return xmlParser.followResearcher(follower, followed);
	}

	public boolean unFollowResearcher(Researcher follower, Researcher followed){
		return xmlParser.unfollowResearcher(follower, followed);
	}
}
