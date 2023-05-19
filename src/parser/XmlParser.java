package parser;

import model.Researcher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

	public List<Researcher> getResearchersFromXml() {
		List<Researcher> researchers = new ArrayList<>();
		try {
			Document document = loadXmlFile();
			NodeList researcherNodes = document.getElementsByTagName("researcher");
			for (int i = 0; i < researcherNodes.getLength(); i++) {
				Element researcherElement = (Element) researcherNodes.item(i);
				Researcher researcher = extractResearcherFromElement(researcherElement);
				researchers.add(researcher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return researchers;
	}

	private Document loadXmlFile() throws ParserConfigurationException, IOException, SAXException {
		File xmlFile = new File("researchers.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(xmlFile);
	}

	private Researcher extractResearcherFromElement(Element researcherElement) {
		String researcherName = getTextContentByTagName(researcherElement, "researcher_name");
		String password = getTextContentByTagName(researcherElement, "password");
		List<String> followingResearchers = extractNamesFromNodeList(researcherElement, "following_researcher_names");
		List<String> followerResearchers = extractNamesFromNodeList(researcherElement, "follower_researcher_names");
		return new Researcher(researcherName, password, followingResearchers, followerResearchers);
	}

	private String getTextContentByTagName(Element element, String tagName) {
		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}

	private List<String> extractNamesFromNodeList(Element element, String parentTagName) {
		List<String> names = new ArrayList<>();
		NodeList nodes = element.getElementsByTagName(parentTagName);
		if (nodes.getLength() > 0) {
			Element parentElement = (Element) nodes.item(0);
			NodeList nameNodes = parentElement.getElementsByTagName("name");
			for (int i = 0; i < nameNodes.getLength(); i++) {
				names.add(nameNodes.item(i).getTextContent());
			}
		}
		return names;
	}

	public boolean followResearcher(Researcher follower, Researcher followed) {
		try {
			Document document = loadXmlFile();
			NodeList researcherNodes = document.getElementsByTagName("researcher");
			Element followingResearcherElement = findResearcher(follower, researcherNodes);
			Element followingNamesElement = getOrCreateFollowingNamesElement(followingResearcherElement);
			if (isResearcherAlreadyExist(followingNamesElement, followed.getName())) {
				System.out.println("The researcher is already being followed.");
				return false;
			}
			appendNameToElement(followingNamesElement, "name", followed.getName());

			Element followedResearcherElement = findResearcher(followed, researcherNodes);
			Element followerNamesElement = getOrCreateFollowerNamesElement(followedResearcherElement);
			if (isResearcherAlreadyExist(followerNamesElement, follower.getName())) {
				System.out.println("The follower already exists.");
				return false;
			}
			appendNameToElement(followerNamesElement, "name", follower.getName());

			return saveXmlFile("researchers.xml", document);
		} catch (SAXException | IOException | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Element findResearcher(Researcher researcher, NodeList researcherNodes) {
		for (int i = 0; i < researcherNodes.getLength(); i++) {
			Element researcherElement = (Element) researcherNodes.item(i);
			String researcherName = getTextContentByTagName(researcherElement, "researcher_name");
			if (researcherName.equals(researcher.getName())) {
				return researcherElement;
			}
		}
		return null;
	}

	private Element getOrCreateFollowingNamesElement(Element researcherElement) {
		NodeList followingResearcherNodes = researcherElement.getElementsByTagName("following_researcher_names");
		if (followingResearcherNodes.getLength() > 0) {
			return (Element) followingResearcherNodes.item(0);
		} else {
			Document document = researcherElement.getOwnerDocument();
			Element followingNamesElement = document.createElement("following_researcher_names");
			researcherElement.appendChild(followingNamesElement);
			return followingNamesElement;
		}
	}

	private boolean isResearcherAlreadyExist(Element followingNamesElement, String researcherName) {
		NodeList followingNameNodes = followingNamesElement.getElementsByTagName("name");
		for (int i = 0; i < followingNameNodes.getLength(); i++) {
			String existingName = followingNameNodes.item(i).getTextContent();
			if (existingName.equals(researcherName)) {
				return true;
			}
		}
		return false;
	}

	private void appendNameToElement(Element element, String tagName, String name) {
		Document document = element.getOwnerDocument();
		Element nameElement = document.createElement(tagName);
		nameElement.setTextContent(name);
		element.appendChild(nameElement);
	}

	private Element getOrCreateFollowerNamesElement(Element researcherElement) {
		NodeList followerResearcherNodes = researcherElement.getElementsByTagName("follower_researcher_names");
		if (followerResearcherNodes.getLength() > 0) {
			return (Element) followerResearcherNodes.item(0);
		} else {
			Document document = researcherElement.getOwnerDocument();
			Element followerNamesElement = document.createElement("follower_researcher_names");
			researcherElement.appendChild(followerNamesElement);
			return followerNamesElement;
		}
	}


	private boolean saveXmlFile(String fileName, Document document) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		// Remove the XML declaration
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		// Save the XML to a string
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);
		String xmlString = writer.toString();

		// Remove empty lines from the XML string
		String formattedXmlString = xmlString.replaceAll("(?m)^[ \t]*\r?\n", "");

		// Save the formatted XML string to the file
		try (PrintWriter out = new PrintWriter(fileName)) {
			out.print(formattedXmlString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("XML file updated successfully.");
		return true;
	}

	public boolean unfollowResearcher(Researcher follower, Researcher unfollowed) {
		try {
			Document document = loadXmlFile();
			NodeList researcherNodes = document.getElementsByTagName("researcher");
			Element followingResearcherElement = findResearcher(follower, researcherNodes);
			Element followingNamesElement = getOrCreateFollowingNamesElement(followingResearcherElement);
			removeNameFromElement(followingNamesElement, unfollowed.getName());

			Element unfollowedResearcherElement = findResearcher(unfollowed, researcherNodes);
			Element followerNamesElement = getOrCreateFollowerNamesElement(unfollowedResearcherElement);
			removeNameFromElement(followerNamesElement, follower.getName());

			return saveXmlFile("researchers.xml", document);

		} catch (SAXException | IOException | TransformerException |
		         ParserConfigurationException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void removeNameFromElement(Element element, String name) {
		NodeList nameNodes = element.getElementsByTagName("name");
		for (int i = 0; i < nameNodes.getLength(); i++) {
			Element nameElement = (Element) nameNodes.item(i);
			String existingName = nameElement.getTextContent();
			if (existingName.equals(name)) {
				element.removeChild(nameElement);
				break;
			}
		}
	}


}
