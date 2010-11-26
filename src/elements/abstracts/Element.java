package elements.abstracts;

import processing.core.PApplet;
import processing.core.PVector;
import application.Manager;

public abstract class Element {
	public PVector center;
	public int x, y, side;
	
	protected PApplet app;

	public Element(int x, int y, int side) {
		this.x = x;
		this.y = y;
		this.side = side;
		center = new PVector(x + (side / 2), y + (side / 2));
		this.app = Manager.processing();
	}
	
	public int getSize(){
		return side;
	}
	
	public abstract void draw();
	
	
}
