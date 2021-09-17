package Class3W1;
import edu.duke.*;

public class WordLengths {
	public void countWordLengths(FileResource resource, int[] counts) {
		int wordLength = 0;
		for(String word : resource.words()){
			wordLength = word.length();
			if (Character.isLetter(word.charAt(0)) == false) {
				wordLength --;
			}
			if (Character.isLetter(word.charAt((word.length()-1))) == false) {
				wordLength --;
			}
			if (wordLength < counts.length) {
				counts[wordLength] +=1;
			}
			else {
				counts[counts.length - 1] +=1;
			}
		}
		for(int k = 1; k < counts.length; k++) {
			System.out.println(k + "letter words= " + counts[k]);
		}
		
	}
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		FileResource fr = new FileResource();
		int[] a = new int [10];
		wl.countWordLengths(fr,a);
	}
}
