package elements;

import processing.core.PVector;
import application.Emergence;
import application.Manager;
import elements.abstracts.LivingBeing;
import elements.abstracts.MovableLivingBeing;

public class Carnivore extends MovableLivingBeing{
	
		

	public Carnivore(int x, int y) {
		super(x, y, Emergence.CARNIVORE_SIZE);
		this.food = Vegetarian.TYPE;
		this.maxAge = 1000;
		this.color = app.color(255,0,0);
	}
	
	protected void die() {
		Manager.getInsManager().removeLivingBeing(this);

	}


	protected void findFood(float distance) {
		Vegetarian[] vegetarian = Manager.getInsManager(app).getvegetarians();
		for (int i = 0; i < vegetarian.length; i++) {
			if (PVector.dist(center, vegetarian[i].center) < distance){
				nearest = vegetarian[i];
			}
		}
			
	}
	
	protected void findMate(float distance) {
		Carnivore[] carnivores = Manager.getInsManager(app).getCarnivores();
		for (int i = 0; i < carnivores.length; i++) 
			if(!this.equals(carnivores[i]))
				if (PVector.dist(center, carnivores[i].center) < distance)
					reproduce(carnivores[i]);
	}

	
	public void consume() {
		if(nearest instanceof Vegetarian)
			Manager.getInsManager().consumeFood(this, (LivingBeing)nearest);
		a = Manager.getInsManager().createAttractors(this);
		nearest = null;
		
	}

	
	public void reproduce(MovableLivingBeing mate) {
		if(this.foodEaten == FERTILE_AGE && mate.getFoodEaten() == FERTILE_AGE)
			Manager.getInsManager().createCarnivore();
	}


}
