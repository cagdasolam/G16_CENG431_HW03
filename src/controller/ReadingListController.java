package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import generator.JsonParser;
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

	public boolean createNewReadingList(Researcher researcher, String readingListName) {
		File jsonFile = new File("readingList.json");

		List<ReadingList> existingReadingLists = readExistingReadingLists(jsonFile);

		int id = existingReadingLists.stream().mapToInt(ReadingList::getReadingListId).max().orElse(0) + 1;

		if (!isNameUnique(readingListName, existingReadingLists)) {
			System.out.println("Name is not unique");
			return false;
		}

		ReadingList createdReadingList = new ReadingList(id, researcher.getName(), readingListName, 0, new ArrayList<>());

		existingReadingLists.add(createdReadingList);

		jsonParser.updateJsonFile(jsonFile, existingReadingLists);

		return true;
	}

	public boolean addPaperToReadingList(String readingListName, String paperName) {
		File jsonFile = new File("readingList.json");

		List<ReadingList> existingReadingLists = readExistingReadingLists(jsonFile);

		ReadingList updatedReadingList = findByReadingListName(readingListName, existingReadingLists);

		if (updatedReadingList != null) {
			if (!isPaperInList(paperName, updatedReadingList.getNameOfPapers())){
				System.out.println("Paper already in list");
				return false;
			}
			updatedReadingList.getNameOfPapers().add(paperName);
		} else {
			System.out.println("ReadingList not found!");
			return false;
		}

		return jsonParser.updateJsonFile(jsonFile, existingReadingLists);
	}

	private List<ReadingList> readExistingReadingLists(File jsonFile) {
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

	private boolean isNameUnique(String newListName, List<ReadingList> readingLists) {
		return readingLists.stream()
				.noneMatch(readingList -> readingList.getReadingListName().equals(newListName));
	}

//	public List<ReadingList> getAllReadingList()

	public ReadingList findByReadingListName(String readingListName, List<ReadingList> readingLists) {
		return readingLists.stream()
				.filter(readingList -> readingList.getReadingListName().equals(readingListName))
				.findFirst().orElse(null);
	}

	private boolean isPaperInList(String paperName, List<String> papers){
		return papers.stream()
				.noneMatch(paper -> paper.equals(paperName));
	}
}
