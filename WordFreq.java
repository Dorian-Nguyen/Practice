package Class3W2;
import edu.duke.FileResource;
import java.util.ArrayList;

public class WordFreq {

	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFreq() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	public void findUnique() {
		myWords.clear();
		myFreqs.clear();
		FileResource res = new FileResource();
		for(String s : res.words()) {
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if (index == -1) {
				myWords.add(s);
				myFreqs.add(1);
			}
			else {
				int value = myFreqs.get(index);
				myFreqs.set(index,value+1);
				}
			}
		}
	
	private int findIndexOfMax() {
		int maxSize = 0;
		int maxInd = 0;
		for (int size : myFreqs) {
			int currSize = myFreqs.get(size);
			if (currSize > maxSize) {
				maxInd = size;
				maxSize = currSize;
			}
		}		
		return maxInd;
	}
	
	public void test() {
		findUnique();
		System.out.println("#Unique words = #" + myWords.size());
		for(int k=0; k<myWords.size(); k++) {
			System.out.println(myFreqs.get(k) + " "+myWords.get(k));
		}
		System.out.println(findIndexOfMax());
	}
	public static void main(String[] args) {
		WordFreq wf = new WordFreq();
		wf.test();
	}
}
