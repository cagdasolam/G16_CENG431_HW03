package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ReadingList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	public boolean updateJsonFile(File jsonFile, List<ReadingList> readingLists) {
		try {
			// Write the updated existingReadingLists back to the JSON file
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			FileWriter writer = new FileWriter(jsonFile);
			gson.toJson(readingLists, writer);
			writer.close();

			System.out.println("JSON file updated successfully!");

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
