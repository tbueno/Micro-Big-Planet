/*
 * Simulação simples de elementos em um ambiente 
 * 
 * Seres vivos precisam comer para sobreviver e podem se reproduzir 
 * se a comida é abundante.
 * 
 * 
 * 12/2009
 * Autor: Thiago Bueno Silva (tbueno@gmail.com)
 * Use como quiser e me mande por email se criar algo legal em cima 
 * desse código. :-)
 */


package application;

import processing.core.PApplet;
import controlP5.ControlEvent;
import controlP5.ControlFont;
import controlP5.ControlP5;
import controlP5.Textfield;
import controlP5.Textlabel;
import elements.Carnivore;
import elements.Obstacle;
import elements.Vegetable;
import elements.Vegetarian;

public class Emergence extends PApplet {

	public static int THRESHOLD = 5;

	private Manager m;

	public static int WORLD_WIDTH = 400;

	private int controlsWidth = 200;

	public static int VEGETARIAN_SIZE = 8;
	public static int OBSTACLE_SIZE = 20;
	public static int ATTRACTOR_SIZE = 8;
	public static int VEGETABLE_SIZE = 3;
	public static int CARNIVORE_SIZE = 12;

	private Vegetarian vegetarians[];
	private Obstacle obstacles[];
	private Vegetable vegetables[];
	private Carnivore carnivores[];

	private int vegetablesQt = 30;
	private int obstaclesQt = 3;
	private int vegetariansQt = 20;
	private int carnivoresQt = 2;

	// GUI Configuration

	ControlP5 controlP5;
	ControlFont font;
	controlP5.Button b;
	String textValue = "";
	Textfield carnivoresTextfield;
	Textfield vegetariansTextfield;
	Textfield vegetablesTextfield;
	Textlabel vegetariansTextlabel;

	public void setup() {
		size(WORLD_WIDTH + controlsWidth, 400);
		rectMode(CORNER);

		obstacles = new Obstacle[obstaclesQt];
		initialize();
		configureGUI();

	}

	private void initialize() {
		println("--------------------- RESET -----------------------");
		background(0);
		if (m != null)
			m.restart();
		m = Manager.getInsManager(this);
		for (int i = 0; i < vegetariansQt; i++) {
			m.createVegetarian();
		}
		for (int i = 0; i < obstaclesQt; i++) {
			obstacles[i] = m.createObstacle();
		}

		for (int i = 0; i < vegetablesQt; i++) {
			m.createVegetable();
		}

		for (int i = 0; i < carnivoresQt; i++) {
			m.createCarnivore();
		}

	}

	public void draw() {
		vegetarians = m.getvegetarians();
		vegetables = m.getVegetables();
		carnivores = m.getCarnivores();

		background(0);
		for (int i = 0; i < vegetarians.length; i++) {
			vegetarians[i].draw();

		}
		for (int i = 0; i < obstacles.length; i++) {
			obstacles[i].draw();
		}

		for (int i = 0; i < vegetables.length; i++) {
			vegetables[i].draw();
		}

		for (int i = 0; i < carnivores.length; i++) {
			carnivores[i].draw();
		}

	}

	private void configureGUI() {
		controlP5 = new ControlP5(this);
		controlP5.addButton("reset", 10, WORLD_WIDTH, height - 60, 80, 20)
				.setId(1);

		vegetariansTextfield = controlP5.addTextfield("vegetarianos",
				WORLD_WIDTH, 100, 100, 20);
		vegetariansTextfield.setFocus(true);
		carnivoresTextfield = controlP5.addTextfield("carnivoros", WORLD_WIDTH,
				160, 100, 20);
		vegetablesTextfield = controlP5.addTextfield("vegetais", WORLD_WIDTH,
				220, 100, 20);

		stroke(0);
		controlP5.addTextlabel("param", "Parametros", WORLD_WIDTH, 80);

	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "application.Emergence" });
	}

	public void controlEvent(ControlEvent theEvent) {

		if (theEvent.controller().name().equals("carnivoros")) {
			carnivoresQt = Integer
					.parseInt(theEvent.controller().stringValue());
			initialize();
		} else if (theEvent.controller().name().equals("vegetarianos")) {
			vegetariansQt = Integer.parseInt(theEvent.controller()
					.stringValue());
			initialize();
		} else if (theEvent.controller().name().equals("vegetais")) {
			vegetablesQt = Integer
					.parseInt(theEvent.controller().stringValue());
			initialize();
		}

	}

	public void reset(float theValue) {
		initialize();

	}

}
