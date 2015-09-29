import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordCount {

	public void wordCount(String input, String output) {

		FileReader fileReader;
		BufferedReader fileBufferedReader = null;
		FileWriter fileWriter;
		BufferedWriter fileBufferedWriter = null;
		String line;
		try {
			fileReader = new FileReader(input);
			fileBufferedReader = new BufferedReader(fileReader);
			fileWriter = new FileWriter(output + File.separator + "ft1.txt");
			fileBufferedWriter = new BufferedWriter(fileWriter);
			Map<String, Integer> wordMap = new TreeMap<String, Integer>();
			line = fileBufferedReader.readLine();
			while (line != null) {
				String[] words = line.split("\\s");
				for (String wordKey : words) {
					if (wordMap.containsKey(wordKey)) {
						wordMap.put(wordKey, wordMap.get(wordKey) + 1);
					} else {
						wordMap.put(wordKey, 1);
					}
				}
				line = fileBufferedReader.readLine();
			}
			Iterator<Entry<String, Integer>> wordMapIterator = wordMap
					.entrySet().iterator();

			while (wordMapIterator.hasNext()) {
				Map.Entry<String, Integer> wordCount = wordMapIterator.next();
				fileBufferedWriter.write(wordCount.getKey() + " "
						+ wordCount.getValue());
				fileBufferedWriter.newLine();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				fileBufferedReader.close();
				fileBufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		WordCount wordCount = new WordCount();
		wordCount.wordCount(args[0], args[1]);

	}

}
