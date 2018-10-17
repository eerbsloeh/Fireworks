package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Firework {
	
	public static Random random = new Random();

	//static
	public static int STATE_ASCENDING = 1, STATE_EXPLODED = 2, STATE_FINISHED = 3;
	public static float gravity = 2;
	
	//logic
	private int state;
	private int lifeLengthExplode;
	private int lifeLength;
	private int lifeCount = 0;
	
	//graphic
	private Color color;
	private int sizeX = 10, sizeY = 10;
	
	//movement
	int posX, posY;
	float moveX, moveY;
	float accX, accY;
	
	Spark[] sparks;
	
	ArrayList<Point> lastPos;
	
	public Firework(int posX, int posY, int lifeLength, int afterglow) {
		this.posX = posX;
		this.posY = posY;
		this.lifeLengthExplode = lifeLength;
		this.lifeLength = lifeLength + afterglow;
		
		this.state = STATE_ASCENDING;
		
		this.color = Color.BLUE;
		
		sparks = new Spark[random.nextInt(13)+7];
		
		lastPos = new ArrayList<>();
		
	}
	
	
	//useless method just to make codebeat cringe
	public void useless(int a, int b, int c, float d, float e, float f, boolean g, boolean h, boolean i) {
		
		a = b;
		b = c;
		c = d;
		d = e;
		e = f;
		f = g;
		g = h;
		h = i;
		i = a;
		a = b;
		b = c;
		c = d;
		d = e;
		e = f;
		f = g;
		g = h;
		h = i;
		i = a;
		a = b;
		b = c;
		c = d;
		d = e;
		e = f;
		f = g;
		g = h;
		h = i;
		i = a;
		a = b;
		b = c;
		c = d;
		d = e;
		e = f;
		f = g;
		g = h;
		h = i;
		i = a;
		
	}
	
	
	
	private void physicsTick() {
		moveX += accX;
		moveY += accY;
		accX = 0;
		accY = 0;
		
		posX += moveX;
		posY += moveY;
		
		moveY += gravity;
		
		lastPos.add(0, new Point(posX, posY));
		
		try {
			lastPos.remove(4);
		} catch(Exception ex) {
			//do nothing
		}
		
		if (lifeCount == lifeLengthExplode) {
			explode();
		}
	}
	
	private void explode() {
		
		for (int i = 0; i < sparks.length; i++) {
			sparks[i] = new Spark(posX, posY);
			
			//b.addForce((r.nextInt(60)-30) * 2, (r.nextInt(40)-30) * 2);
			sparks[i].applyForce(random.nextInt(60)-30, random.nextInt(40)-30);
			
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

			for (int i = 0; i < lastPos.size(); i++) {
				Point p = lastPos.get(i);
				
				int sizeX = this.sizeX - i*2;
				int sizeY = this.sizeY - i*2;
				
				//p.x += (this.sizeX - sizeX)/2;
				p.x += i-1;
				
				g.fillOval(p.x, p.y, sizeX, sizeY);
				
			}
			
		}
		
		if (lifeCount == lifeLength)	this.state = STATE_FINISHED;
		
		return this.state == STATE_FINISHED;
	}
	
}
