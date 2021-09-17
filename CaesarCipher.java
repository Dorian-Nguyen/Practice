package Class3W1;
import edu.duke.*;

public class CaesarCipher {
	public String encrypt(String input, int key) {
		//System.out.println(input);
		StringBuilder encrypted = new StringBuilder(input);
		String Ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String UshiftedAlphabet = Ualphabet.substring(key) + Ualphabet.substring(0,key);
		String Lalphabet = "abcdefghijklmnopqrstuvwxyz";
		String LshiftedAlphabet = Lalphabet.substring(key) + Lalphabet.substring(0,key);
		
		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int uidx = Ualphabet.indexOf(currChar);
			int lidx = Lalphabet.indexOf(currChar);
			if (uidx != -1) {
				char newChar = UshiftedAlphabet.charAt(uidx);
				encrypted.setCharAt(i, newChar);
			}
			if (lidx != -1) {
				char newChar = LshiftedAlphabet.charAt(lidx);
				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString(); 
	}
	public String encryptTwoKeys(String input, int key1, int key2) {
		StringBuilder encrypted = new StringBuilder(input);
		String cum = "";
		for (int i = 0; i < encrypted.length(); i++) {
			if (i==0 || i%2 == 0) {
				cum = cum + encrypt(input.substring(i,i+1), key1);
			}
			else {
				cum = cum + encrypt(input.substring(i,i+1), key2);
			}
		}
		return cum;
	}
	
	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher();
		System.out.println(cc.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
		System.out.println(cc.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8,21));
	}
}
