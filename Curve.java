import java.awt.*;
import java.util.*;

public class Curve {
	private ArrayList<Integer> xPoints;
	private ArrayList<Integer> yPoints;
	private int x;
	private int y;
	
	public Curve(int x2, int y2) {
		xPoints = new ArrayList<Integer>();
		yPoints = new ArrayList<Integer>();
		x = x2;
		y = y2;
	}
	
	public void addXPoint(int vx) {
		xPoints.add(vx);
	}
	
	public void addYPoint(int vy) {
		yPoints.add(vy);
	}
	
	public void draw(Graphics g) {
		//System.out.println(xPoints);
		//g.drawPolygon(arrayConvert(xPoints), arrayConvert(yPoints), xPoints.size());
		for (int i = 0; i < xPoints.size(); i++) {
			g.drawOval(xPoints.get(i), yPoints.get(i), 1,1);
		}
	}
	
	public static int[] arrayConvert(ArrayList<Integer> gar) {
		int[] ret = new int[gar.size()];
		for (int i = 0; i < gar.size(); i++) {
			ret[i] = gar.get(i);
		}
		return ret;
	}
}