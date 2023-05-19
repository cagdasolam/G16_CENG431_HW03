package controller;

import model.Researcher;
import parser.XmlParser;

public class ResearcherController {

	XmlParser xmlParser = new XmlParser();

	public boolean followResearcher(Researcher follower, Researcher followed){
		return xmlParser.followResearcher(follower, followed);
	}

	public boolean unFollowResearcher(Researcher follower, Researcher followed){
		return xmlParser.unfollowResearcher(follower, followed);
	}
}
