package Week3;
import edu.duke.*;

import java.io.*;

import org.apache.commons.csv.*;

public class weather {
	public CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;
		for (CSVRecord currentRow : parser) {
			if (largestSoFar == null){
				largestSoFar = currentRow;
			}
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				if (currentTemp  > largestTemp) {
					largestSoFar = currentRow;
				}
			}
		}
		return largestSoFar;
	}
	
	public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
		if(largestSoFar == null) {
			largestSoFar = currentRow;
		}
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			if (currentTemp  > largestTemp) {
				largestSoFar = currentRow;
				}
		}
		return largestSoFar;
	}
	
	public CSVRecord hottestInManyDays() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord largestSoFar = null;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}
	
	public void tester() {
		CSVRecord largest = hottestInManyDays();
		System.out.println(largest.get("DateUTC"));
	}
	public static void main(String[] args) {
		weather test = new weather();
		test.tester();
	}
}
 