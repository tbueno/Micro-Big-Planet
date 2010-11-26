package elements;

import application.Emergence;
import elements.abstracts.LivingBeing;

public class Vegetable extends LivingBeing {

	public static final int TYPE = 0;

	public Vegetable(int x, int y) {
		super(x, y, Emergence.VEGETABLE_SIZE);
		this.color = app.color(0, 255, 0);
	}

	protected void die() {

	}

}
