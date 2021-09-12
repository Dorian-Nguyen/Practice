package Week3;
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Cold {

	public CSVRecord coldestHourInFile(CSVParser parser) {
		
		CSVRecord smallestSoFar = null;
		for (CSVRecord currentRow : parser) {
			if (smallestSoFar == null){
				smallestSoFar = currentRow;
			}
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
				if (currentTemp  < largestTemp && currentTemp != -9999) {
					smallestSoFar = currentRow;
				}
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar, String Collumn) {
		if(smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		else {
			double currentTemp = Double.parseDouble(currentRow.get(Collumn));
			double smallestTemp = Double.parseDouble(smallestSoFar.get(Collumn));
			if (currentTemp  < smallestTemp) {
				smallestSoFar = currentRow;
				}
		}
		return smallestSoFar;
	}
	
	public String fileWithColdestTemperature() {
		String smallestName = null;
		DirectoryResource dr = new DirectoryResource();
		CSVRecord smallestSoFar = null;
		
		for (File f : dr.selectedFiles()) {
			CSVRecord smallestbefore = smallestSoFar;
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
			if(smallestbefore != smallestSoFar) {
				smallestName = f.getName();
			}
		}
		return smallestName; 
	}
	
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		
		CSVRecord smallestSoFar = null;
		for (CSVRecord currentRow : parser) {
			if (smallestSoFar == null){
				smallestSoFar = currentRow;
			}
			else {
				if (! currentRow.get("Humidity").contains("N/A")) {
					double currentHumid = Double.parseDouble(currentRow.get("Humidity"));
					double largestHumid = Double.parseDouble(smallestSoFar.get("Humidity"));
					if (currentHumid  < largestHumid) {
						smallestSoFar = currentRow;
					}
				}
			}
		}
		return smallestSoFar;
	}
	
	public CSVRecord lowestHUmidInManyDays() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowestHumidSoFar = null;
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			lowestHumidSoFar = getSmallestOfTwo(currentRow, lowestHumidSoFar, "Humidity");
		}
		return lowestHumidSoFar;
	}

	public Double averageTemperatureInFile(CSVParser parser) {
		Double TempSum = 0.0;
		for (CSVRecord currentRow : parser) {
			TempSum = TempSum + Double.parseDouble(currentRow.get("TemperatureF"));
			}
		Double avgTemp = TempSum/24;
		
		return avgTemp;
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double TempSum = 0.0;
		int count = 0;
		for (CSVRecord currentRow : parser) {
			if (currentRow.get("Humidity") != "N/A") {
				if (Double.parseDouble(currentRow.get("Humidity")) > value){
					TempSum = TempSum + Double.parseDouble(currentRow.get("TemperatureF"));
					count++;
					}
				}			
			}
		Double avgTemp = TempSum/count;
		
		return avgTemp;
	}
	
	public void testFile() {
		//FileResource fr = new FileResource();
		//System.out.println(coolest.get("DateUTC") + coolest.get("Humidity"));
		System.out.println(fileWithColdestTemperature());
		//System.out.println(averageTemperatureInFile(fr.getCSVParser()));
	}
	
	public static void main(String[] args) {
		Cold test = new Cold();
		test.testFile();
	}
}