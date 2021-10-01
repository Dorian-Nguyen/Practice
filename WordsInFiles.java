package Class3W2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.*;


public class WordsInFiles {
	private HashMap<String,ArrayList<String>> Words;
	
	public WordsInFiles() {
		Words = new HashMap<String,ArrayList<String>>();
	}
	
	public void addWordsFromFile(File f) {
		FileResource fr = new FileResource(f);
		for (String word : fr.words()) {
			if (Words.containsKey(word)) {
				ArrayList<String> aList = Words.get(word);
				aList.add(f.getName());
				Words.put(word, aList);
			}
			else {
				ArrayList<String> aList = new ArrayList<>();
				aList.add(f.getName());
				Words.put(word, aList);
			}
		}
	}
	
	public void buildWordFileMap() {
		Words.clear();
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		}
		//for (String name : Words.keySet()) {
			//System.out.println(name.toString() + " appears in " + Words.get(name));
		//}
	}
	
	public int maxNumber() {
		int maxSize = 0;
		for (String key : Words.keySet()) {
			int currSize = Words.get(key).size();
			if (maxSize < currSize) {
				maxSize = currSize;
			}
		}
		return maxSize;
	}
	
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> words = new ArrayList<String>();
		for (String key : Words.keySet()) {
			if(Words.get(key).size() == number) {
				words.add(key);
			}
		}
		System.out.println(words.size());
		return words;
	}
	
	public void printFilesIn(String word) {
		ArrayList<String> ans = new ArrayList<String>(Words.get(word));
		for (String file : ans) {
			System.out.println(file);
		}
	}
	public static void main(String[] args) {
		WordsInFiles WiF = new WordsInFiles();
		WiF.buildWordFileMap();
		//WiF.wordsInNumFiles(5);
		WiF.printFilesIn("laid");
		
	}

}
