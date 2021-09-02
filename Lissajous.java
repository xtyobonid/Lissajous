import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Lissajous extends JComponent {
	private double angle;
	private int width;
	private int height;
	private Curve[] curves;
	
	public Lissajous(int width2, int height2) {
		angle = 0;
		width = width2;
		height = height2;
		
		curves = new Curve[width * height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				curves[y * width + x] = new Curve(x, y);
			}
		}
		
		new Timer(20, (ActionEvent e) -> {
            repaint();
        }).start();
	}
	
	public void paintComponent(Graphics g) {
		//Draw horizontal base circles
		for (int i = 0; i < width; i ++) {
			g.drawOval(i * 60 + 80, 10, 40, 40);
			g.fillOval(i * 60 + 97 + (int)(20 * Math.cos((i + 1) * angle)), 28 + (int)(20 * Math.sin((i + 1) * angle)), 5,5);
			for (int j = 0; j < height; j++) {
				//System.out.println((j * width + i) + " " + (i * 60 + 97 + (int)(20 * Math.cos((i + 1) * angle))) + " " + ((int)(20 * Math.sin((i + 1) * angle))));
				curves[j * width + i].addXPoint(i * 60 + 97 + (int)(20 * Math.cos((i + 1) * angle)));
			}
		}
		
		//Draw vertical base circles
		for (int i = 0; i < height; i++) {
			g.drawOval(10, i * 60 + 80, 40, 40);
			g.fillOval(28 + (int)(20 * Math.cos((i + 1) * angle)), i * 60 + 97 + (int)(20 * Math.sin((i + 1) * angle)), 5,5);
			for (int j = 0; j < width; j ++) {
				curves[i * width + j].addYPoint(i * 60 + 97 + (int)(20 * Math.sin((i + 1) * angle)));
			}
		}
		
		for (int i = 0; i < curves.length; i++) {
			curves[i].draw(g);
		}
		
		angle -= 0.01;
		if (angle < -1 * Math.PI * 2) {
			restart();
		}
	}
	
	public void restart() {
		angle = 0;
		curves = new Curve[width * height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				curves[y * width + x] = new Curve(x, y);
			}
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		Lissajous j = new Lissajous(14,14);
		f.add(j);
		f.setSize(j.width * 60 + 90, j.height * 60 + 115);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}