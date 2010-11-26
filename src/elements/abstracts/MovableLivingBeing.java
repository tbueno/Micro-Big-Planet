package elements.abstracts;

import processing.core.PVector;
import elements.Attractor;

public abstract class MovableLivingBeing extends LivingBeing implements
		IMovable {
	public Attractor a[];
	protected int food;
	protected Element nearest;
	protected int foodEaten;
	
	protected int FERTILE_AGE = 2;
	
	public MovableLivingBeing(int x, int y, int side) {
		super( x, y, side);
		a = new Attractor[3];
		foodEaten = 0;
	}

	public void eat() {
		age = 0;	
		foodEaten++;
	}
	
	public void draw(){
		super.draw();
		move();
	}
	
	public int getFoodEaten(){
		return foodEaten;
	}

	public abstract void consume(); 
	
	public abstract void reproduce(MovableLivingBeing mate); 
	
	public void move() {
		if (nearest == null)
			findNearest();

		boolean reachX = false;
		boolean reachY = false;

		if (x < nearest.x)
			moveRight();
		else if (x > nearest.x)
			moveLeft();
		else
			reachX = true;

		if (y < nearest.y)
			moveDown();
		else if (y > nearest.y)
			moveUp();
		else
			reachY = true;

		if (reachX && reachY) {
			consume();			
		}

	}
	
	protected abstract void findFood(float distance);
	
	protected abstract void findMate(float distance);
	
	
	protected void findNearest() {
		float d = 100000;
		// Acha o attrator mais proximo
		for (int i = 0; i < a.length; i++) {
			if (PVector.dist(center, a[i].center) < d) {
				d = PVector.dist(center, a[i].center);
				nearest = a[i];
			}
		}
		// Verifica se a comida est‡ pr—xima
		findFood(d);
		// Verifica se tem parceiro por perto
		findMate(d);

	}
	
	
	protected void moveDown() {
		y++;
		center.y++;
	}

	protected void moveUp() {
		y--;
		center.y--;
	}

	protected void moveLeft() {
		x--;
		center.x--;
	}

	protected void moveRight() {
		x++;
		center.x++;
	}
	
	


}
