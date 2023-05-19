package parser;

import org.apache.commons.csv.CSVFormat;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Article;
import model.ConferencePaper;
import model.Paper;

public class CsvParser {
	
	public List<Paper> getPapers(String filePath) {
	    List<Paper> papers = new ArrayList<>();

	    try {
	        File csvFile = new File(filePath);
	        FileReader fileReader = new FileReader(csvFile);
	        CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').parse(fileReader);

	        for (CSVRecord record : parser.getRecords()) {
	            String paperType = record.get(0);
	            Paper paper;

	            List<String> authors = Arrays.asList(record.get(1).split(","));
	            String title = record.get(2);
	            int year = Integer.parseInt(record.get(3));
	            String DOI = record.get(4);
	            int numDownloads;

	            if (paperType.equals("Conference paper")) {
	                String bookTitle = record.get(5);
	                numDownloads = Integer.parseInt(record.get(6));
	                paper = new ConferencePaper(authors, title, year, DOI, bookTitle, numDownloads);
	            } else {
	                int volume = Integer.parseInt(record.get(5));
	                int number = Integer.parseInt(record.get(6));
	                String journal = record.get(7);
	                numDownloads = Integer.parseInt(record.get(8));
	                paper = new Article(authors, title, year, DOI, volume, number, journal, numDownloads);
	            }

	            papers.add(paper);
	        }

	        parser.close();
	        fileReader.close();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return papers;
	}


	public void updateCSV(String filePath, String paperTitle) {
		try {
			File csvFile = new File(filePath);
			File tempFile = File.createTempFile("temp", null);

			FileReader fileReader = new FileReader(csvFile);
			FileWriter fileWriter = new FileWriter(tempFile);

			CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').parse(fileReader);
			CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withDelimiter(';'));

			for (CSVRecord record : parser.getRecords()) {
				String[] recordArray = toArray(record);
				int numDownloadIndex = 6;
				if (record.get(0).equals("Article paper")) {
					numDownloadIndex = 8;
				}
				String name = record.get(2);
				if (name.equals(paperTitle)) {
					recordArray[numDownloadIndex] = String.valueOf(Integer.parseInt(recordArray[numDownloadIndex]) + 1);
				}
				printer.printRecord(recordArray);
			}

			printer.flush();
			printer.close();
			parser.close();
			fileWriter.close();
			fileReader.close();

			// Replace the original file with the temporary file
			if (!csvFile.delete()) {
				throw new IOException("Failed to delete the original file.");
			}
			if (!tempFile.renameTo(csvFile)) {
				throw new IOException("Failed to rename the temporary file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] toArray(CSVRecord rec) {
		String[] arr = new String[rec.size()];
		int i = 0;
		for (String str : rec) {
			arr[i++] = str;
		}
		return arr;
	}
}
