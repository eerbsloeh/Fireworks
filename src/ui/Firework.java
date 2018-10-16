package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Firework {

	public static int STATE_ASCENDING = 1, STATE_EXPLODED = 2, STATE_FINISHED = 3;
	
	private int state;
	private int lifeLength;
	private int lifeCount = 0;
	
	private Color color;
	private int sizeX = 10, sizeY = 10;
	
	int posX, posY;
	float moveX, moveY;
	float accX, accY;
	
	
	public Firework(int posX, int posY, int lifeLength) {
		this.posX = posX;
		this.posY = posY;
		this.lifeLength = lifeLength;
		
		this.state = STATE_ASCENDING;
		
		this.color = Color.BLUE;
	}
	
	private void physicsTick() {
		lifeCount++;
		
		moveX += accX;
		moveY += accY;
		accX = 0;
		accY = 0;
		
		posX += moveX;
		posY += moveY;
		
		if (lifeCount == lifeLength) {
			explode();
		}
	}
	
	private void explode() {
		
	}
	
	public void applyForce(float accX, float accY) {
		this.accX = accX;
		this.accY = accY;
	}
	
	
	public boolean paint(Graphics g) {
		
		physicsTick();
		
		g.setColor(color);
		g.fillOval(posX, posY, sizeX, sizeY);
		
		return this.state == STATE_FINISHED;
	}
	
}
