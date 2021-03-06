package elements.abstracts;

public abstract class LivingBeing extends Element implements IDrawable {

	protected int age;
	protected int maxAge;
	protected int color;

	public LivingBeing(int x, int y, int side) {
		super(x, y, side);
		age = 0;
	}

	protected void getOld() {
		age++;
		if (age > maxAge) {
			die();
		}
	}

	public void draw() {
		app.fill(color);
		app.ellipse(x, y, side, side);
		getOld();
	
	}

	protected abstract void die();

}
