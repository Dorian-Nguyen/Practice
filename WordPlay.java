package Class3W1;

import java.lang.*;
public class WordPlay {
	public Boolean isVowel(char ch) {
		String vowels = "aeiouAEIUO";
		int idx = vowels.indexOf(ch);
		if (idx != -1) {
			return true;
		}
		return false;
	}
	
	public String replaceVowels(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		for (int i = 0; i < phrase.length(); i++) {
			char currChar = phrase.charAt(i);
			if (isVowel(currChar) == true) {
				newPhrase.setCharAt(i, ch);
			}
		}
		
		
		return newPhrase.toString();
	}
	
	public String emphasize(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		for (int i = 0; i < phrase.length(); i++) {
			char currChar = phrase.charAt(i);
			char lch = Character.toLowerCase(ch);
			char uch = Character.toUpperCase(ch);
			if (uch == currChar || lch == currChar) {
				if (i == 0 || i%2 == 0) {
					newPhrase.setCharAt(i, '*');
				}
				else {
					newPhrase.setCharAt(i, '+');
				}
			}
		}
		
		return newPhrase.toString();
	}
	public static void main(String[] args) {
		WordPlay wp = new WordPlay();
		//System.out.println(wp.isVowel('1'));
		//System.out.println(wp.replaceVowels("corn", '*'));
		System.out.println(wp.emphasize("Mary Bella Abracadabra", 'a'));
	}
}
