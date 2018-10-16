package ui;

import java.awt.Color;
import java.awt.Graphics;

public class Spark {

	public static float gravity = 5;
	
	private Color color;
	private int sizeX = 5, sizeY = 5;
	
	int posX, posY;
	float moveX, moveY;
	float accX, accY;
	
	public Spark(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		
		this.color = Color.yellow;
	}
	
	public void physicsTick() {
		moveX += accX;
		moveY += accY;
		accX = 0;
		accY = 0;
		
		posX += moveX;
		posY += moveY;
		
		moveY += gravity;
	}
	
	public void applyForce(float accX, float accY) {
		this.accX = accX;
		this.accY = accY;
	}
	
	public void addForce(float accX, float accY) {
		this.accX = accX;
		this.accY = accY;
	}
	
	public void paint(Graphics g) {
		physicsTick();
		
		g.setColor(color);
		g.fillOval(posX, posY, sizeX, sizeY);
		
	}
	
}
