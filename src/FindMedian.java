import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	Queue<Integer> maxWordsHeap = new PriorityQueue<Integer>(10, new MyComparator());
	int numOfWords;

	public void findMedianByTweet() throws IOException {

		try {
			fileReader = new FileReader("C:\\Users\\Sagar.Disawal\\Desktop\\test.txt");
			fileBufferedReader = new BufferedReader(fileReader);
			fileWriter = new FileWriter("C:\\Users\\Sagar.Disawal\\Desktop\\testoutput.txt");
			fileBufferedWriter = new BufferedWriter(fileWriter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> wordSet = new HashSet<String>();
		line = fileBufferedReader.readLine();

		while (line != null) {
			String[] words = line.split("\\s");
			wordSet.clear();
			for (String wordKey : words) {
				wordSet.add(wordKey);
			}
			wordSet.size();
			System.out.println(getMedia(Integer.valueOf(wordSet.size())));
			line = fileBufferedReader.readLine();
		}
	}

	public double getMedia(Integer num) {
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
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}

	public static void main(String[] args) throws IOException {
		FindMedian FindMedian = new FindMedian();
		FindMedian.findMedianByTweet();

	}
}
