package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import parser.JsonParser;
import model.ReadingList;
import model.Researcher;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReadingListController {

	JsonParser jsonParser = new JsonParser();
	List<ReadingList> readingLists;
	File jsonFile ;


	public List<ReadingList> getReadingLists() {
		return readingLists;
	}

	public void setReadingLists(List<ReadingList> readingLists) {
		this.readingLists = readingLists;
	}

	public ReadingListController() {
		this.jsonFile = new File("readingList.json");
		this.readingLists = readExistingReadingLists();
	}

	public boolean createNewReadingList(Researcher researcher, String readingListName) {

		List<ReadingList> existingReadingLists = readExistingReadingLists();

		int id = existingReadingLists.stream().mapToInt(ReadingList::getReadingListId).max().orElse(0) + 1;

		if (!isNameUnique(readingListName)) {
			System.out.println("Name is not unique");
			return false;
		}

		ReadingList createdReadingList = new ReadingList(id, researcher.getName(), readingListName, 0, new ArrayList<>());

		existingReadingLists.add(createdReadingList);

		jsonParser.updateJsonFile(jsonFile, existingReadingLists);

		return true;
	}

	public boolean addPaperToReadingList(String readingListName, String paperName) {

		ReadingList updatedReadingList = findByReadingListName(readingListName);

		if (updatedReadingList != null) {
			if (!isPaperInList(paperName, updatedReadingList.getNameOfPapers())){
				System.out.println("Paper already in list");
				return false;
			}
			updatedReadingList.getNameOfPapers().add(paperName);
			updatedReadingList.setNumberOfPapers(updatedReadingList.getNameOfPapers().size());
		} else {
			System.out.println("ReadingList not found!");
			return false;
		}

		return jsonParser.updateJsonFile(jsonFile, readingLists);
	}

	private List<ReadingList> readExistingReadingLists() {
		List<ReadingList> existingReadingLists = new ArrayList<>();

		try (FileReader reader = new FileReader(jsonFile)) {
			Type listType = new TypeToken<List<ReadingList>>() {}.getType();
			Gson gson = new Gson();
			existingReadingLists = gson.fromJson(reader, listType);
		} catch (IOException e) {
			System.out.println("Existing JSON file not found. Creating a new one.");
		}

		return existingReadingLists;
	}

	private boolean isNameUnique(String newListName) {
		return readingLists.stream()
				.noneMatch(readingList -> readingList.getReadingListName().equals(newListName));
	}

	public ReadingList findByReadingListName(String readingListName) {
		return readingLists.stream()
				.filter(readingList -> readingList.getReadingListName().equals(readingListName))
				.findFirst().orElse(null);
	}

	public List<ReadingList> findReadingListByUserName(String userName) {
		return readingLists.stream()
				.filter(readingList -> readingList.getCreatorResearcherName().equals(userName)).toList();
	}

	private boolean isPaperInList(String paperName, List<String> papers){
		return papers.stream()
				.noneMatch(paper -> paper.equals(paperName));
	}
}
