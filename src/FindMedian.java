import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class FindMedian {

	FileReader fileReader = null;
	BufferedReader fileBufferedReader;
	FileWriter fileWriter = null;
	BufferedWriter fileBufferedWriter;
	String line = null;
	Queue<Integer> minWordsHeap = new PriorityQueue<Integer>();
	Queue<Integer> maxWordsHeap = new PriorityQueue<Integer>(50,
			new MyComparator());
	int numOfWords = 0;

	public void findMedianByTweet(String input, String output)
			throws IOException {

		try {
			fileReader = new FileReader(input);
			fileBufferedReader = new BufferedReader(fileReader);
			fileWriter = new FileWriter(output + File.separator + "ft2.txt");
			fileBufferedWriter = new BufferedWriter(fileWriter);

			Set<String> wordSet = new HashSet<String>();
			line = fileBufferedReader.readLine();

			while (line != null) {
				String[] words = line.split("\\s");
				wordSet.clear();
				for (String wordKey : words) {
					wordSet.add(wordKey);
				}
				fileBufferedWriter.write(String.valueOf(populateQueue(Integer
						.valueOf(wordSet.size()))));
				fileBufferedWriter.newLine();
				line = fileBufferedReader.readLine();
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

	public double populateQueue(Integer num) {
		maxWordsHeap.add(num);

		if (numOfWords % 2 == 0) {
			if (minWordsHeap.isEmpty()) {
				numOfWords++;
				return getMedian();
			} else if (maxWordsHeap.peek() > minWordsHeap.peek()) {
				Integer maxHeapRoot = maxWordsHeap.poll();
				Integer minHeapRoot = minWordsHeap.poll();
				maxWordsHeap.add(minHeapRoot);
				minWordsHeap.add(maxHeapRoot);
			}
		} else {
			minWordsHeap.add(maxWordsHeap.poll());
		}
		numOfWords++;
		return getMedian();
	}

	public Double getMedian() {
		if (numOfWords % 2 != 0)
			return new Double(maxWordsHeap.peek());
		else
			return (maxWordsHeap.peek() + minWordsHeap.peek()) / 2.0;
	}

	private class MyComparator implements Comparator<Integer> {
		public int compare(Integer i1, Integer i2) {
			return i2 - i1;
		}
	}

	public static void main(String[] args) throws IOException {
		FindMedian FindMedian = new FindMedian();
		FindMedian.findMedianByTweet(args[0], args[1]);

	}
}
