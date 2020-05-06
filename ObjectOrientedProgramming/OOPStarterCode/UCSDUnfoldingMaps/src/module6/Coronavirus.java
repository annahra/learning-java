package module6;

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
import parsing.ParseFeed;


public class Coronavirus implements Comparable<Coronavirus> {
	
	private String country;
	private Integer cases;
	
	public Coronavirus(String currCountry, Integer numCases) {
		country = currCountry;
		cases = numCases;
	}
	
	public String getCountry() {return country;}
	public Integer getCases() {return cases;}
	
	public int compareTo(Coronavirus covid) { 
		
		Integer callCases = this.getCases();
		Integer otherCases = covid.getCases();
		
		if(callCases > otherCases) {return 1;}
		else if(callCases < otherCases) {return -1;}
		
		return 0;
	}
}