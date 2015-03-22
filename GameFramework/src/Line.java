import java.awt.*;

public class Line {

	int x1;
	int y1;
	
	int x2;
	int y2;
	
	double xc;
	double yc;
	double xn;
	double yn;
	
	public Line(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		int xv = x2 - x1;
		int yv = y2 - y1;
		
		double vMag = Math.sqrt(xv*xv + yv*yv);
		
		xc = xv / vMag;
		yc = yv / vMag;
		
		xn =  yc;
		yn = -xc;
	}
	
	public void draw(Graphics g){
		g.drawLine(x1, y1, x2, y2);
	}
	
	public double distanceTo(int x, int y){
		return (x - x1) * yc - (y - y1) * xc;
	}
}
