package ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel {

	public static Random random = Firework.random;
	
	Firework f;
	
	ArrayList<Firework> fireworks;
	
	int fireworkDelay = 35;
	int frameCount = 0;
	
	public Panel() {
		super();
		
		f = new Firework(Frame.size.width/2, Frame.size.height, 20, 10);
		f.applyForce(0, -30);
		
		fireworks = new ArrayList<>();
	}
	
	@Override
	public void paint(Graphics g) {
		
		if (Frame.state == 2) {
			frameCount++;
			if (frameCount == fireworkDelay) {
				Firework f = new Firework(random.nextInt(Frame.size.width), Frame.size.height, random.nextInt(20) + 10, random.nextInt(15)+5);
				f.applyForce(0, -(random.nextInt(20)+30));
				fireworks.add(f);
				
				frameCount = 0;
				fireworkDelay = random.nextInt(25)+1;
			}
			
			
			for (int i = 0; i < fireworks.size(); i++) {
				if (fireworks.get(i).paint(g)) {
					fireworks.remove(i);
					i--;
				}
			}
		}
		
	}
	
}
