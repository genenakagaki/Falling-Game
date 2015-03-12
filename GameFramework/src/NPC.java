import java.awt.Graphics;

public class NPC extends PolygonModel2D {
	
	double x;
	double y;
	
	int r;
	
	int angle = 10;
	
	public NPC(int x, int y, int angle) {
		super(x, y, angle);
	}

	public int[][] getXCoords() {
		return null;
	}

	public int[][] getYCoords() {
		return null;
	}
	
	public void draw(Graphics g){
		int xInt = (int)x;
		int yInt = (int)y;
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		g.drawOval(xInt -r , yInt -r, r*2, r*2);
		
		g.drawLine(xInt, yInt, (int)(x + r * cosA), (int)(y + r * sinA));
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

