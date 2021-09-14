package Week4;
import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

public class BabyBirths {
	
	public void printNames() {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) + " Gender: " + rec.get(1) + " Num Born: " + rec.get(2));
			}
		}
	}
	
	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalBoysName = 0;
		int totalGirls = 0;
		int totalGirlsName = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysName +=1;
			}
			else {
				totalGirls += numBorn;
				totalGirlsName +=1;
			}
			
		}
		System.out.println("total births: " + totalBirths);
		System.out.println("total boys: " + totalBoys);
		System.out.println("total boys name: " + totalBoysName);
		System.out.println("total girls: " + totalGirls);
		System.out.println("total girls name: " + totalGirlsName);
		
	}
	
	public int getRank(int year, String name, String gender) {
		int rank = 0;
		
		FileResource fr = new FileResource("C:\\Users\\doria\\Desktop\\JavaPrograms\\us_babynames_by_year\\yob" + year + ".csv");
		for (CSVRecord rec : fr.getCSVParser(false)) {
			//System.out.println("girl rank: " + girlRank);
			//System.out.println("boy rank:  " + boyRank);
			if (rec.get(1).equals(gender)) {
				rank++;
				}
			if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
				return rank;
				}
		}
		return -1;
	}  
	
	public int getRankFile(FileResource fr, String name, String gender) {
		int rank = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			//System.out.println("girl rank: " + girlRank);
			//System.out.println("boy rank:  " + boyRank);
			if (rec.get(1).equals(gender)) {
				rank++;
				}
			if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
				return rank;
				}
		}
		return 0;
	}  

	public String getName(int year, int rank, String gender) {
		String name = "NO NAME";
		int currRank = 0;

		FileResource fr = new FileResource("C:\\Users\\doria\\Desktop\\JavaPrograms\\us_babynames_by_year\\yob" + year + ".csv");
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				currRank++;
			}
			if (currRank == rank) {
				name = rec.get(0);
			}
		}
		return name;
	}
	
	public String whatIsNameInYear(String name, int year, int newYear, String gender) {
		int yearRank = getRank(year, name, gender);
		String newName = getName(newYear, yearRank, gender);
		
		return name + " born in " + year + " would be " + newName + " if they were born in " + newYear;
	}
	
	public int yearOfHighestRank(String name, String gender) {
		int highestRank = -1;
		int highestYear = -1;

		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			int currentRank = -1;
			int rank = 0;
			int currentYear = -1;
			FileResource fr = new FileResource(f);
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if (rec.get(1).equals(gender)) {
					rank++;
					}
				if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
					currentYear = Integer.parseInt(f.getName().substring(3,7));
					currentRank = rank;
					System.out.println(currentYear + ", " + currentRank);
				}
			}
			if (highestRank == -1) {
				highestRank = currentRank;
				highestYear = currentYear;
					}
			
			if (highestRank > currentRank && currentRank != -1) {
				highestRank = currentRank;
				highestYear = currentYear;
				}
			}
		return highestYear;
	}
		
	public double getAverageRank(String name, String gender) {
		double sum = 0.0;
		int numOfFiles = 0;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			sum += getRankFile(fr, name, gender);
			numOfFiles ++;
		}
		return sum/numOfFiles;
	}
	
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		FileResource fr = new FileResource("C:\\Users\\doria\\Desktop\\JavaPrograms\\us_babynames_by_year\\yob" + year + ".csv");
		int nameRank = getRank(year, name, gender);
		int nameSum = 0;
		int currRank = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				currRank++;
				if (currRank == nameRank) {
					return nameSum;
				}
				else {
					nameSum += Integer.parseInt(rec.get(2));
				}
			}
		}
		return -1;
	}
	

	public static void main(String[] args) {
		BabyBirths test = new BabyBirths();
		//FileResource fr = new FileResource("C:\\Users\\doria\\Desktop\\JavaPrograms\\us_babynames_by_year\\yob1905.csv");
		//test.totalBirths(fr);
		//System.out.println(test.getRank(1971,"Frank", "M"));
		//System.out.println(test.getName(1982, 450, "M"));
		//System.out.println(test.whatIsNameInYear("Owen",1974, 2014, "M"));
		//System.out.println(test.yearOfHighestRank("Mich", "M"));
		//System.out.println(test.getAverageRank("Robert", "M"));
		System.out.println(test.getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}
}
