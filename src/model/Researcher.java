package model;

import java.util.List;

public class Researcher {

	private String name;
	private String password;
	private List<String> followingResearcherNames;
	private List<String> followerResearcherNames;

	public Researcher(String name, String password, List<String> followingResearcherNames, List<String> followerResearcherNames) {
		this.name = name;
		this.password = password;
		this.followingResearcherNames = followingResearcherNames;
		this.followerResearcherNames = followerResearcherNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getFollowingResearcherNames() {
		return followingResearcherNames;
	}

	public void setFollowingResearcherNames(List<String> followingResearcherNames) {
		this.followingResearcherNames = followingResearcherNames;
	}

	public List<String> getFollowerResearcherNames() {
		return followerResearcherNames;
	}

	public void setFollowerResearcherNames(List<String> followerResearcherNames) {
		this.followerResearcherNames = followerResearcherNames;
	}

}
