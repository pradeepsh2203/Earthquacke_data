package GuiModule;
import processing.core.PApplet;
public class newApplet extends PApplet {
	
	public void setup() {
		size(400,400);
		background(200,200,200);
	}
	
	public void draw() {
		fill(255,209,0);
		ellipse(200,200,390,390);
		fill(0,0,0);
		ellipse(120,130,50,70);
		ellipse(280,130,50,70);
		noFill();
		arc(200,280,130,100,0,PI);
	}
}
