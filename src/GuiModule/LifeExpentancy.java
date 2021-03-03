package GuiModule;
import processing.core.*;
import java.util.*;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
public class LifeExpentancy extends PApplet {
	UnfoldingMap map;
	List<Feature> featuresOfCountry;
	List<Marker> countryMarkers;
	private Map<String, Float> lifeExpectancyProvider(String fileName){//Map is in java.util//it creates a mapping
		//of the country id with the lifeExpectancy value of that country..
		Map<String, Float> loadMap=new HashMap<String, Float> ();
		String rows[]=loadStrings(fileName);int i=1;//i to verify that first row(which is the header row) is not executed.
		for(String row:rows) {
			String coloumns[]=row.split(",");
			int indexReq=coloumns.length-1;// indexReq takes the advantage of split function which does not create trailling zero in the array
			if(indexReq==-1) {break;}// to stop the loop when row becomes empty
			else if(!coloumns[indexReq].equals("..")&&i!=1) {// main code is here
			float value=Float.parseFloat(coloumns[indexReq]);
			loadMap.put(coloumns[indexReq-1],value);
			}
			else {i--;}// just for changing the value of i once so its value differ from 1
		}
		return loadMap;
	}
	private void shadeAllMarkers(List<Marker> markers,Map<String,Float> countries) {//It takes the mapping and markers
		//then shade the marker on the basis of the LifeExpectancy value
		for(Marker marker:markers) {
			String countryId=marker.getId();
			//System.out.println(countryId);
			if(countries.containsKey(countryId)){
				float value=countries.get(countryId);
				int colorLevel=(int)map(value,40,90,0,255);
				marker.setColor(color(255-colorLevel,100,colorLevel));
			}
			else {marker.setColor(100);}
		}
	}
	public void setup() {
		size(800,600,P2D);
		map=new UnfoldingMap(this,50,50,700,500,new EsriProvider.NatGeoWorldMap());
		Map <String, Float> lifeExpectancyOfEachCountry=lifeExpectancyProvider("LifeExpectancyWorldBankModule3.csv");
		featuresOfCountry=GeoJSONReader.loadData(this,"countries.geo.json");
		for(Feature f:featuresOfCountry)
		System.out.println(f.getProperties());
		countryMarkers=new MarkerFactory().createMarkers(featuresOfCountry);
		shadeAllMarkers(countryMarkers,lifeExpectancyOfEachCountry);
		map.addMarkers(countryMarkers);
		map.zoomLevel(1);
		MapUtils.createDefaultEventDispatcher(this,map);
	}
	public void draw() {
		background(210);
		map.draw();
	}
}
