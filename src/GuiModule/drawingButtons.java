package GuiModule;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.core.Coordinate;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.*;
public class drawingButtons extends PApplet{
	UnfoldingMap map;
	public void setup() {
		size(800,600,P2D);
		map=new UnfoldingMap(this,50,50,700,500,new Microsoft.RoadProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		map.zoomLevel(2);
		
	}
	public void draw() {
		map.draw();
		fill(255);
		rect(75,75,25,25);
		fill(0);
		rect(75,125,25,25);
	}
	 public void mouseReleased(){
		if(mouseX>=75&&mouseX<=100) {
			if(mouseY>=75&&mouseY<=100) {
				background(255);
			}
			else if(mouseY>=125&&mouseY<=150) {
				background(0);
			}
		}
		
	}
}
