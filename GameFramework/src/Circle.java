import java.awt.*;

public class Circle {

	int x;
	int y;
	double xDouble;
	double yDouble;
	
	int r;
	
	int angle = 10;
	
	public Circle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r = r;
		this.xDouble = x;
		this.yDouble = y;
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
	
	public boolean isToLeftOf(Circle c){
		double xc = Lookup.cos[c.angle];
		double yc = Lookup.sin[c.angle];
		
		return (x - c.x) * yc - (y - c.y) * xc > 10;
	}
	
	public boolean isToRightOf(Circle c){
		double xc = Lookup.cos[c.angle];
		double yc = Lookup.sin[c.angle];
		
		return (x - c.x) * yc - (y - c.y) * xc < -10;
	}
	
	public void moveBy(int dist, int degrees){
		
		if (degrees >= 360){
			degrees -= 360;
		}
		else if (degrees < 0){
			degrees += 360;
		}
		
		xDouble += (dist * Lookup.cos[degrees]);
		yDouble += (dist * Lookup.sin[degrees]);
		x = (int)xDouble;
		y = (int)yDouble;
	}
	
	public void moveForwardBy(int dist){
		moveBy(dist, angle);
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
