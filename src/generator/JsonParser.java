package generator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.ReadingList;
import model.Researcher;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

	public void createJsonFile() {
		File jsonFile = new File("readingList.json");

		List<ReadingList> readingLists = new ArrayList<>();

		if (jsonFile.exists()) {
			return;
		}

		try (FileWriter writer = new FileWriter(jsonFile)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(readingLists, writer);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write JSON file", e);
		}
	}

	public boolean createNewReadingList(Researcher researcher, String readingListName) {
		File jsonFile = new File("readingList.json");

		List<ReadingList> existingReadingLists = readExistingReadingLists(jsonFile);

		int id = existingReadingLists.stream().mapToInt(ReadingList::getReadingListId).max().orElse(0) + 1;

		if (!isNameUnique(readingListName, existingReadingLists)) {
			return false;
		}

		ReadingList createdReadingList = new ReadingList(id, researcher.getName(), readingListName, 0, new ArrayList<>());

		existingReadingLists.add(createdReadingList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonContent = gson.toJson(existingReadingLists);

		try (FileWriter writer = new FileWriter(jsonFile)) {
			writer.write(jsonContent);
		} catch (IOException e) {
			throw new RuntimeException("Failed to write JSON file", e);
		}

		return true;
	}

//	TODO: add paper to list

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

}
