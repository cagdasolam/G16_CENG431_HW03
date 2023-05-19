package parser;

import model.Researcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

	public List<Researcher> getResearchersFromXml(){
		List<Researcher> researchers = new ArrayList<>();

		try {
			// Load the XML file
			File xmlFile = new File("researchers.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			// Get the list of researcher nodes
			NodeList researcherNodes = document.getElementsByTagName("researcher");

			// Iterate over the researcher nodes
			for (int i = 0; i < researcherNodes.getLength(); i++) {
				Element researcherElement = (Element) researcherNodes.item(i);

				// Extract researcher details
				String researcherName = researcherElement.getElementsByTagName("researcher_name").item(0).getTextContent();
				String password = researcherElement.getElementsByTagName("password").item(0).getTextContent();

				// Extract following researcher names
				List<String> followingResearchers = new ArrayList<>();
				NodeList followingResearcherNodes = researcherElement.getElementsByTagName("following_researcher_names");
				Element followingResearchersElement = (Element) followingResearcherNodes.item(0);
				NodeList followingResearcherNameNodes = followingResearchersElement.getElementsByTagName("name");
				for (int j = 0; j < followingResearcherNameNodes.getLength(); j++) {
					followingResearchers.add(followingResearcherNameNodes.item(j).getTextContent());
				}

				// Extract follower researcher names
				List<String> followerResearchers = new ArrayList<>();
				NodeList followerResearcherNodes = researcherElement.getElementsByTagName("follower_researcher_names");
				if (followerResearcherNodes.getLength() > 0) {
					Element followerResearchersElement = (Element) followerResearcherNodes.item(0);
					NodeList followerResearcherNameNodes = followerResearchersElement.getElementsByTagName("name");
					for (int j = 0; j < followerResearcherNameNodes.getLength(); j++) {
						followerResearchers.add(followerResearcherNameNodes.item(j).getTextContent());
					}
				}

				// Create Researcher object
				Researcher researcher = new Researcher(researcherName, password, followingResearchers, followerResearchers);
				researchers.add(researcher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return researchers;
	}

	public boolean updateXml(Researcher follower, Researcher followed) {
		try {
			// Load the XML file
			File xmlFile = new File("researchers.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			// Find the researcher who is following
			NodeList researcherNodes = document.getElementsByTagName("researcher");

			Element followingResearcherElement = findResearcher(follower, researcherNodes);

			// Update the following_researcher_names of the following researcher
			Element followingNamesElement = (Element) followingResearcherElement.getElementsByTagName("following_researcher_names").item(0);
			String followedResearcherName = followed.getName();

			// Check if the researcher being followed is already in the list
			NodeList followingNameNodes = followingNamesElement.getElementsByTagName("name");
			for (int i = 0; i < followingNameNodes.getLength(); i++) {
				Node nameNode = followingNameNodes.item(i);
				String existingName = nameNode.getTextContent();
				if (existingName.equals(followedResearcherName)) {
					System.out.println("The researcher is already being followed.");
					return false;
				}
			}

			// Add the researcher being followed to the list
			Element newFollowingNameElement = document.createElement("name");
			newFollowingNameElement.setTextContent(followedResearcherName);
			followingNamesElement.appendChild(newFollowingNameElement);

			// Find the researcher being followed
			Element followedResearcherElement = findResearcher(followed, researcherNodes);

			// Update the follower_researcher_names of the followed researcher
			Element followerNamesElement = (Element) followedResearcherElement.getElementsByTagName("follower_researcher_names").item(0);
			String followerResearcherName = follower.getName();

			// Check if the follower is already in the list
			NodeList followerNameNodes = followerNamesElement.getElementsByTagName("name");
			for (int i = 0; i < followerNameNodes.getLength(); i++) {
				Node nameNode = followerNameNodes.item(i);
				String existingName = nameNode.getTextContent();
				if (existingName.equals(followerResearcherName)) {
					System.out.println("The follower already exists.");
					return false;
				}
			}

			// Add the follower to the list
			Element newFollowerNameElement = document.createElement("name");
			newFollowerNameElement.setTextContent(followerResearcherName);
			followerNamesElement.appendChild(newFollowerNameElement);

			// Save the updated XML to the file
			return saveXml(xmlFile, document);

		} catch (SAXException | IOException | TransformerException |
		         ParserConfigurationException e) {
			e.printStackTrace();
		}

		return false;
	}

	private Element findResearcher(Researcher researcher, NodeList researcherNodes) {
		for (int i = 0; i < researcherNodes.getLength(); i++) {
			Element researcherElement = (Element) researcherNodes.item(i);
			String researcherName = researcherElement.getElementsByTagName("researcher_name").item(0).getTextContent();
			if (researcherName.equals(researcher.getName())) {
				return researcherElement;
			}
		}
		return null;
	}

	public boolean unfollowXml(Researcher follower, Researcher unfollowed) {
		try {
			// Load the XML file
			File xmlFile = new File("researchers.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);

			// Find the researcher who is following
			NodeList researcherNodes = document.getElementsByTagName("researcher");
			Element followingResearcherElement = findResearcher(follower, researcherNodes);

			// Remove the unfollowed researcher from the following_researcher_names of the following researcher
			Element followingNamesElement = (Element) followingResearcherElement.getElementsByTagName("following_researcher_names").item(0);
			String unfollowedResearcherName = unfollowed.getName();
			NodeList followingNameNodes = followingNamesElement.getElementsByTagName("name");
			removeNode(followingNamesElement, unfollowedResearcherName, followingNameNodes);

			// Find the unfollowed researcher
			Element unfollowedResearcherElement = findResearcher(unfollowed, researcherNodes);

			// Remove the follower from the follower_researcher_names of the unfollowed researcher
			Element followerNamesElement = (Element) unfollowedResearcherElement.getElementsByTagName("follower_researcher_names").item(0);
			String followerResearcherName = follower.getName();
			NodeList followerNameNodes = followerNamesElement.getElementsByTagName("name");
			removeNode(followerNamesElement, followerResearcherName, followerNameNodes);

			// Save the updated XML to the file
			return saveXml(xmlFile, document);

		} catch (SAXException | IOException | TransformerException |
		         ParserConfigurationException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void removeNode(Element followerNamesElement, String followerResearcherName, NodeList followerNameNodes) {
		for (int i = 0; i < followerNameNodes.getLength(); i++) {
			Element nameElement = (Element) followerNameNodes.item(i);
			String existingName = nameElement.getTextContent();
			if (existingName.equals(followerResearcherName)) {
				followerNamesElement.removeChild(nameElement);
				break;
			}
		}
	}

	private boolean saveXml(File xmlFile, Document document) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(xmlFile);
		transformer.transform(source, result);

		System.out.println("XML file updated successfully.");
		return true;
	}

}

