package module3;
import processing.core.*;

public class MyPApplet extends PApplet {
	private String URL = "http://i.huffpost.com/gen/1286511/images/o-ASTRONAUT-facebook.jpg";
	private PImage backgroundImg;
	
	public void setup() {
		//runs once
		size(300,300);
		backgroundImg = loadImage(URL,"jpg");
		backgroundImg.resize(0,height);
		image(backgroundImg,0,0);
	}

	public void draw() {
		//constantly runs, resets
		//image will be full height of canvas and java calculates the width
		//height allows the image to be dynamic
		
		int[] color = sunColorSec(second());
		//fill in the ellipse
		fill(color[0], color[1], color[2]);
		//use library method ellipse to draw an ellipse dynamically
		//						  size of ellipse
		//                        vv       vv
		ellipse(width/6,height/5,width/5,height/5);
	}

	public int[] sunColorSec(float seconds) {
		//scale brightness based on seconds. 30 seconds is black 0 secs is bright
		int[] rgb = new int[3];
		//calc difference between current time and 30 seconds
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		//cast to int becasue ratio is float
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		
		return rgb;
	}
}