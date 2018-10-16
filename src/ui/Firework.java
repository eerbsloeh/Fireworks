package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Firework {
	
	public static Random random = new Random();

	public static int STATE_ASCENDING = 1, STATE_EXPLODED = 2, STATE_FINISHED = 3;
	public static float gravity = 0;
	
	private int state;
	private int lifeLengthExplode;
	private int lifeLength;
	private int lifeCount = 0;
	
	private Color color;
	private int sizeX = 10, sizeY = 10;
	
	int posX, posY;
	float moveX, moveY;
	float accX, accY;
	
	Spark[] sparks;
	
	public Firework(int posX, int posY, int lifeLength, int afterglow) {
		this.posX = posX;
		this.posY = posY;
		this.lifeLengthExplode = lifeLength;
		this.lifeLength = lifeLength + afterglow;
		
		this.state = STATE_ASCENDING;
		
		this.color = Color.BLUE;
		
		sparks = new Spark[7];
		
	}
	
	private void physicsTick() {
		moveX += accX;
		moveY += accY;
		accX = 0;
		accY = 0;
		
		posX += moveX;
		posY += moveY;
		
		moveY += gravity;
		
		if (lifeCount == lifeLengthExplode) {
			explode();
		}
	}
	
	private void explode() {
		
		for (int i = 0; i < sparks.length; i++) {
			sparks[i] = new Spark(posX, posY);
			sparks[i].applyForce(random.nextFloat()*15, random.nextFloat()*15);
		}
		
		this.state = STATE_EXPLODED;
	}
	
	public void applyForce(float accX, float accY) {
		this.accX = accX;
		this.accY = accY;
	}
	
	
	public boolean paint(Graphics g) {
		lifeCount++;
		
		physicsTick();
		
		if (state == STATE_EXPLODED) {
			for (int i = 0; i < sparks.length; i++) {
				sparks[i].paint(g);
			}
		}
		else if (state == STATE_ASCENDING){
			g.setColor(color);
			g.fillOval(posX, posY, sizeX, sizeY);
		}
		
		if (lifeCount == lifeLength)	this.state = STATE_FINISHED;
		
		return this.state == STATE_FINISHED;
	}
	
}
