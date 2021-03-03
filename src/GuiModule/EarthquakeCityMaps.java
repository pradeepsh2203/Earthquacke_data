package GuiModule;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.*;
import java.util.*;
public class EarthquakeCityMaps extends PApplet {
	private UnfoldingMap map1;
	public AbstractMapProvider provider;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	private List<Marker> markercreater(List<PointFeature> features){
		List<Marker> created=new ArrayList<Marker>();
		for(PointFeature f:features) {
			Marker newlyCreated=new SimplePointMarker(f.getLocation(),f.getProperties());
			created.add(newlyCreated);
		}
		return created;
	}
	private void allKey(){
		fill(255,209,0);
		noStroke();
		rect(25,50,200,500,10);
		fill(0);
		//Here is the text
		String s="Earthquake key points\n		          9<=value<=12\n		"
				+ "          6<=value<=9\n		          3<=value<=6\n		          0<=value<=3";
		textSize(16);
		textLeading(80);
		text(s,35,65);
		//Now the points are drawn on the screen 
		fill(52, 235, 195);
		ellipse(50,140,10,10);
		//
		fill(171, 52, 235);
		ellipse(50,220,10,10);
		//
		fill(235, 171, 52);
		ellipse(50,300,10,10);
		//
		fill(235, 52, 171);
		ellipse(50,380,10,10);
		
	}
	private void markerShader(List<Marker> countries) {
		List<Marker> countries1=countries;
		for(Marker m:countries1) {
			float value=(float) m.getProperty("magnitude");
			if(value>=5) {m.setColor(color(255,0,0));}
			else if(value<5&&value>=3) {m.setColor(color(255,209,0));}
			else {m.setColor(color(0,255,0));}
		}
	}
	public void setup() {
		size(900,600,P2D);     //Creating the applet window
		provider= new Google.GoogleMapProvider();    //Setting up the provider
		map1=new UnfoldingMap(this,250,50,600,500,provider);   //Creating the map
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		List<Marker> m1=markercreater(earthquakes);
		/*for(Marker m:m1) {
			System.out.println(m.getProperties());
		}*/
		markerShader(m1);
		map1.addMarkers(m1);
		
		map1.zoomLevel(2);     //setUp the zoomLevel
		MapUtils.createDefaultEventDispatcher(this,map1);      //Add some features
	}
	public void draw() {
		background(210);     //background 
		map1.draw();// draw the map
		allKey();
	}
}
