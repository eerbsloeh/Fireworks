package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable {

	
	public static Dimension size;
	
	private int state = 0;
	
	private float currentDarkness = 0.01f;
	private float darknessDiff = 0.03f;
	private int darknessSpeed = 50;
	private float darknessThreshold = 0.4f;
	
	private int sleepTime = 50;
	
	public Frame() {
		super();
		
		size = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(size);
		
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new Panel());
		
		setBackground(new Color(0, 0, 0, currentDarkness));
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (this.state == 2) {
			paintChildren(g);
		}
	}
	
	private boolean darken() {
		currentDarkness += darknessDiff;
		setBackground(new Color(0, 0, 0, currentDarkness));
		return currentDarkness < darknessThreshold;
	}

	@Override
	public void run() {
		try {
			while(!this.isVisible()) {
				Thread.sleep(50);
				//System.out.println("Invisible");
			}
			this.state = 1;
			while(darken()) {
				Thread.sleep(darknessSpeed);
				//System.out.println("Darken");
			}
			//System.out.println("Done");
			this.state = 2;
			while (true) {
				Thread.sleep(sleepTime);
				repaint();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		
		
	}
	
	public static void main(String[] args) {

		Frame f = new Frame();
		new Thread(f).start();
		f.setVisible(true);
		
	}

}
