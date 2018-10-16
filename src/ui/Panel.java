package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

	Firework f;
	
	public Panel() {
		super();
		
		f = new Firework(Frame.size.width/2, Frame.size.height, 20, 10);
		f.applyForce(0, -30);
	}
	
	@Override
	public void paint(Graphics g) {
		
		if (Frame.state == 2) {
			if (f.paint(g)) {
				f = null;
			}
		}
		
	}
	
}
