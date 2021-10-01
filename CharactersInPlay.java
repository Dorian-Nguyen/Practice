package Class3W2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> characters;
	private ArrayList<Integer> speakFreq;
	
	public CharactersInPlay() {
		speakFreq = new ArrayList<Integer>();
		characters = new ArrayList<String>();
	}
	
	public void update(String person) {
		int idx = characters.indexOf(person);
		if (idx == -1) {
			characters.add(person);
			speakFreq.add(1);
		}
		else {
			int value = speakFreq.get(idx);
			speakFreq.set(idx, value+1);
		}
	}

	public void findAllCharacters() {
		characters.clear();
		speakFreq.clear();
		FileResource fr = new FileResource();
		for(String line : fr.lines()) {
			int pIdx = line.indexOf(".");
			
			if(pIdx != -1) {
				update(line.substring(0,pIdx));
				}
			}
	}
	public void charactersWithNumParts(int num1, int num2) {
		findAllCharacters();
		for(int k = 0; k < characters.size();k++) {
			if(speakFreq.get(k) >= num1 && speakFreq.get(k) <= num2) {
				System.out.println(characters.get(k) + "   " + Integer.toString(speakFreq.get(k)));
			}
		}
	}
	
	private void tester() {
		charactersWithNumParts(10, 15);
		//findAllCharacters();
		//for(int k = 0; k < characters.size();k++) {
			//if(speakFreq.get(k) >= 2) {
		//		System.out.println(characters.get(k) + "   " + Integer.toString(speakFreq.get(k)));
		//	}
	//	}
	}
	
	public static void main(String[] args) {
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();
		}

	}

