import java.awt.*;

public class Circle {

	int x;
	int y;
	int xDouble;
	int yDouble;
	
	int r;
	
	int angle = 10;
	
	public Circle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public void draw(Graphics g){
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		g.drawOval(x -r , y -r, r*2, r*2);
		
		g.drawLine(x, y, (int)(x + r * cosA), (int)(y + r * sinA));
	}
	
	public boolean hasCollidedWith(Line line){
		return line.distanceTo(x, y) <= r;
	}
	
	
	
	public void moveForwardBy(int dist, int angle){
		xDouble += (dist * Lookup.cos[angle]);
		yDouble += (dist * Lookup.sin[angle]);
		x = (int)xDouble;
		y = (int)yDouble;
	}
	
	public void moveForwardBy(int dist){
		moveForwardBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist, int angle){
		xDouble -= (dist * Lookup.cos[angle]);
		yDouble -= (dist * Lookup.sin[angle]);
		x = (int)xDouble;
		y = (int)yDouble;
	}
	
	public void moveBackwardBy(int dist){
		moveBackwardBy(dist, angle);
	}
	
	public void rotateRightBy(int degrees){
		this.angle += degrees;
		if (angle >= 360){
			angle -= 360;
		}
	}
	
	public void rotateLeftBy(int degrees){
		this.angle -= degrees;
		if (angle < 0){
			angle += 360;
		}
	}
}
