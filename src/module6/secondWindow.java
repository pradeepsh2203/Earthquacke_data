package module6;
import processing.core.*;

import java.util.List;

import de.fhpotsdam.unfolding.marker.*;
public class secondWindow extends PApplet{

	private static final long serialVersionUID = 1L;
	CommonMarker newMarker;
	int cities_drawn=0;
	int quakes_drawn=0;
	float tot_quake_rating=0;
	int quake_past=0;
	public secondWindow(CommonMarker m) {
		this.newMarker=m;
	}
	public void setup() {
		size(350,200,OPENGL);
	}
	public void draw() {
		background(210);
		fill(255);
		rect(25,25,300,150,10);
		String country=newMarker.getStringProperty("country");
		String info="Info";
		textSize(22);
		fill(0);
		textAlign(LEFT,CENTER);
		text(info,150,40);
		textSize(12);
		float avg=0;
		if(quakes_drawn!=0) {avg=tot_quake_rating/quakes_drawn;}
		text("No. of city drawn on the map-"+cities_drawn,35,70);
		text("No. of earthquake drawn on the map-"+quakes_drawn,35,90);
		text("average magnitude of the earthquakes-"+String.format("%.2f", avg),35,110);
		text("Selected Country-"+country,35,130);
		text("Earthquake occured in past day-"+quake_past,35,150);
	}
	public void setAllProperties(List<Marker> markers,List<Marker> quakes) {
		for(Marker m:markers) {
			if(!m.isHidden()) {
				this.cities_drawn++;
			}
		}
		for(Marker quake:quakes) {
			if(!quake.isHidden()) {
				this.quakes_drawn++;
				this.tot_quake_rating+=((EarthquakeMarker)quake).getMagnitude();
				if("Past Day".equals(quake.getStringProperty("age"))||"Past Hour".equals(quake.getStringProperty("age"))){
					this.quake_past++;
				}
			}
		}
	}
	public void keyPressed() {
		if(key=='\b'&&this.frame.isVisible()) {
			this.frame.setVisible(false);;
		}
	}
}
