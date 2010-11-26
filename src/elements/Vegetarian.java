package elements;

import processing.core.PVector;
import application.Emergence;
import application.Manager;
import elements.abstracts.LivingBeing;
import elements.abstracts.MovableLivingBeing;

public class Vegetarian extends MovableLivingBeing {
	
	
	public static final int TYPE = 1;
	
	public Vegetarian(int x, int y) {
		super(x, y, Emergence.VEGETARIAN_SIZE);
		this.maxAge = 1000;
		this.food = Vegetable.TYPE;
		this.color = app.color(0,255,255);
	}

	
	protected void die(){
		Manager.getInsManager().removeLivingBeing(this);
	}

	
	protected void findFood(float distance) {
		Vegetable[] vegetables = Manager.getInsManager().getVegetables();
		for (int i = 0; i < vegetables.length; i++) {
			if (PVector.dist(center, vegetables[i].center) < distance) {
				nearest = vegetables[i];
			}
		}
		
	}

	public void consume() {
		if(nearest instanceof Vegetable)
			Manager.getInsManager().consumeFood(this, (LivingBeing)nearest);
		a = Manager.getInsManager().createAttractors(this);
		nearest = null;
		
	}
	
	
	protected void findMate(float distance) {
		Vegetarian[] vegetarian = Manager.getInsManager(app).getvegetarians();
		for (int i = 0; i < vegetarian.length; i++)
			if(!this.equals(vegetarian[i]))
				if (PVector.dist(center, vegetarian[i].center) < distance)
					reproduce(vegetarian[i]);
	}

	
	public void reproduce(MovableLivingBeing mate) {
		if(this.foodEaten == FERTILE_AGE && mate.getFoodEaten() == FERTILE_AGE){
			Manager.getInsManager().createVegetarian();
			System.out.println("Vegetarians: "+Manager.getInsManager().getvegetarians().length);
		}
		
	}

	

}
