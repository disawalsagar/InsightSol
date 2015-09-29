import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordCount {

	public static void wordCount(String input ,String output ) {
		

		FileReader fileReader = null;
		BufferedReader fileBufferedReader;
		FileWriter fileWriter = null;
		BufferedWriter fileBufferedWriter;
		String line; try {
			fileReader = new FileReader(input); 
			fileBufferedReader = new BufferedReader(fileReader);
			fileWriter = new FileWriter(output + File.separator + "ft1.txt");
			fileBufferedWriter = new BufferedWriter(fileWriter);
			Map<String, Integer> wordMap = new HashMap<String, Integer>();
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

			Map<String, Integer> sortedWordMap = new TreeMap<String, Integer>(wordMap);

			Iterator<Entry<String, Integer>> wordMapIterator = sortedWordMap.entrySet().iterator();

			while (wordMapIterator.hasNext()) {
				Map.Entry<String, Integer> wordCount = wordMapIterator.next();
				fileBufferedWriter.write(wordCount.getKey() + " " + wordCount.getValue());
				fileBufferedWriter.newLine();
			}

			System.out.println("Done");
		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		finally {
			try {
				fileReader.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		WordCount.wordCount(args[0],args[1]);
}

}
