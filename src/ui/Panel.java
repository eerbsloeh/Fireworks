package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

	Firework f;
	
	
	public Panel() {
		super();
		f = new Firework(Frame.size.height, Frame.size.width/2, 20);
		f.applyForce(0, -30);
	}
	
	@Override
	public void paint(Graphics g) {
		
		f.paint(g);
		
	}
	
}
