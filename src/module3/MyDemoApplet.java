package module3;
import processing.core.*;
public class MyDemoApplet extends PApplet {
	private PImage backgroundImage;
	public String url="https://processing.org/img/processing-web.png";
	public void setup() {
		size(400,400,P2D);
		backgroundImage=loadImage("palmTrees.jpg");
		//backgroundImage.resize(width,0);
		//background(backgroundImage);
	}
	public void draw() {
		backgroundImage.resize(width,height);		
		//imageMode(CORNER);
		float hour=hour();
		if(hour<=12) {hour=hour/12;}
		else {hour=23-hour;hour=hour/10;}
		image(backgroundImage,0,0);
		//if(hour<temp) {fill(255*temp,209*temp,0);}
		//else{}
		fill(255*hour,209*hour,0);
		ellipse(width/4,height/4,50,50);
		//textAlign(CENTER);
		textSize(16);
		text("The second is "+hour,0,0);
		fill(255,0,0);
		System.out.println(hour);
		fill(0,0,0);
		textSize(24);
		text("Hello",0,0,100,100);
	}
}