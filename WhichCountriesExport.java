package Week3;
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if(export.contains(exportOfInterest)) {
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
	public void whoExportsCoffee() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
		}

	public String countryInfo(CSVParser parser, String country) {
		for (CSVRecord record : parser) {
			if(record.get("Country").contains(country)){
				String exports = record.get("Exports");
				String value = record.get("Value (dollars)");
				return country + ": " + exports + ": " + value;
			}
		}
		return "NOT FOUND";
	}
	public void listExportersTwoProducts(CSVParser parser, String exportOne, String exportTwo) {
		for (CSVRecord record : parser) {
			if(record.get("Exports").contains(exportOne) && record.get("Exports").contains(exportTwo)){
				System.out.println(record.get("Country"));
			}
		}
	}
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int numOfCountries = 0;
		for (CSVRecord record : parser) {
			if(record.get("Exports").contains(exportItem)){
				numOfCountries++;
			}
		}
		return numOfCountries;
	}
	public void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record : parser) {
			if(record.get("Value (dollars)").length() > amount.length()){
				System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
			}
		}
	}
	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		//System.out.println(countryInfo(parser,"Nauru"));
		//listExportersTwoProducts(parser, "fish", "nuts");
		bigExporters(parser, "$999,999,999,999");
	}
	public static void main(String[] args) {
		WhichCountriesExport runCode = new WhichCountriesExport();
		runCode.tester();
	}
}
