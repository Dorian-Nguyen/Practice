package Class3W2;

import java.util.HashMap;

public class codonCount {
	private HashMap<String,Integer> numCodons;
	
	public codonCount() {
		numCodons = new HashMap<String,Integer>();
	}

	public void buildCodonMap(int start, String dna) {
		numCodons.clear();
		for (int k = start; k+3<=dna.length(); k +=3) {
			String codon = dna.substring(k,k+3);
			if (!numCodons.containsKey(codon)) {
				numCodons.put(codon, 1);
			}
			else {
				numCodons.put(codon,numCodons.get(codon)+1);				
			}
		}
		for(String w : numCodons.keySet()){
			int value = numCodons.get(w);
			System.out.println(value+"\t"+w);
		}
		System.out.println(numCodons.keySet().size());
	}
	
	public String getMostCommonCodon() {
		int mostCodonNum = 0;
		String mostCodon = null;
		for (String codon : numCodons.keySet()) {
			if (numCodons.get(codon) > mostCodonNum) {
				mostCodonNum = numCodons.get(codon);
				mostCodon = codon;
			}
		}
		
		
		return mostCodon;
	}

	public void printCodonCounts(int start, int end) {
		for (String codon : numCodons.keySet()) {
			if (numCodons.get(codon) >= start && numCodons.get(codon) <= end) {
				System.out.println(codon + " " + numCodons.get(codon));
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		codonCount CC = new codonCount();
		CC.buildCodonMap(0, "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC");
		CC.printCodonCounts(6,100);
	}

}
