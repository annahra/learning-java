package module3;

//Java utilities libraries
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;
import java.util.Map;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

public class Coronavirus extends PApplet {
	private static final long serialVersionUID = 1L;
	
	private String dateOfInterest = "2020-05-02";
	
	UnfoldingMap map;
	Map<String, Integer> covidCasesByCountry;
	
	List<Feature> countries;
	List<Marker> countryMarkers;
	
	public void setup() {
		size(800,600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Microsoft.HybridProvider());
		
		//double clicking leads to zooming in, and can pan around
		MapUtils.createDefaultEventDispatcher(this, map);
		map.zoomToLevel(2);
		
		covidCasesByCountry = loadCovidCasesFromCSV("../data/countries-aggregated.csv");
		//grab countries and their locations
		countries = GeoJSONReader.loadData(this, "../data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(countryMarkers);
		shadeCountries();
	}
	
	private void shadeCountries() {
		for(Marker marker : countryMarkers) {
			String countryId = marker.getStringProperty("name");
			//System.out.println(countryId);
			if(covidCasesByCountry.containsKey(countryId)) {
				Integer covidCases = covidCasesByCountry.get(countryId);
				
				if(covidCases < 10000) {marker.setColor(color(157, 158, 212));}
				else if(covidCases > 10000 && covidCases < 50000) {
					marker.setColor(color(90, 92, 165));
				}
				else if(covidCases > 50000 && covidCases < 100000) {
					marker.setColor(color(39, 41, 118));
				}
				else if(covidCases > 100000 && covidCases < 500000){
					marker.setColor(color(19, 21, 91));
				}
				else {
					marker.setColor(color(0, 0, 10));
				}
				
				//taking num of covid cases, we know it ranges between 0 and 1.5mil
				//translate this to something to do with color, hence 10->255
				//int colorLevel = (int) map(covidCases, 500000, 1000000, 10, 255);
				//set countries with low covid cases to be bright blue, 
				//countries with high covid cases to be bright red
				//marker.setColor(color(colorLevel, 100, 255-colorLevel));
				//System.out.println(covidCases);
			}
			else {
				//if there is no value for covid in the current country,
				//we color the marker gray
				marker.setColor(color(150,150,150));
			}
		}
		
	}

	public void draw() {
		map.draw();
	}
	
	private Map<String, Integer> loadCovidCasesFromCSV(String filename){
		Map<String, Integer> countryMap = new HashMap<String, Integer>(); 
		String[] rows = loadStrings(filename);
		for (String row : rows) {
			String[] columns = row.split(",");
			String currDate = columns[0];
			//check if date is date of interest
			if(currDate.equals(dateOfInterest)) {
				//grab the country
				String currCountry = columns[1];
				//System.out.println(currCountry+" "+currDate);
				//if the country isn't in the hashmap
				if(!countryMap.containsKey(currCountry)) {
					//grab case count
					Integer currCaseCount = Integer.parseInt(columns[2]);
					//put the country and case count there
					countryMap.put(currCountry,currCaseCount);
				}
			}
		}
		
		return countryMap;
	}
	
}