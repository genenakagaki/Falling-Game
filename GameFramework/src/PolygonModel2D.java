import java.awt.Graphics;
import java.awt.Color;

public abstract class PolygonModel2D {

	int x;
	int y;
	int polyLength;
	double xDouble;
	double yDouble;

	int angle;
	
	double zoom;
	
	int[][] xCoords = getXCoords();
	int[][] yCoords = getYCoords();
	Color[] colors = getColors();
	
	public PolygonModel2D(int x, int y, int angle){
		this.x = x;
		this.y = y;
		this.xDouble = x;
		this.yDouble = y;
		
		this.angle = angle;
	}
	
	public abstract int[][] getXCoords();
	public abstract int[][] getYCoords();
	public abstract Color[] getColors();
	
	public void draw(Graphics g, double zoom){
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		for (int poly = 0; poly < xCoords.length; poly++){
			polyLength = xCoords[poly].length;
			int[] xRotated = new int[polyLength];
			int[] yRotated = new int[polyLength];

			for (int i = 0; i < xRotated.length; i++){
				xRotated[i] = (int)(xCoords[poly][i] * cosA - yCoords[poly][i] * sinA);
				yRotated[i] = (int)(xCoords[poly][i] * sinA + yCoords[poly][i] * cosA);
			}
			
			// current x and y points
			int[] xCurrent = new int[polyLength];
			int[] yCurrent = new int[polyLength];
			
			for (int i = 0; i < xRotated.length; i++){
				xCurrent[i] = (int)((xRotated[i] + x) * zoom);
				yCurrent[i] = (int)((yRotated[i] + y) * zoom);
			}
			
			g.setColor(colors[poly]);
			g.drawPolygon(xCurrent, yCurrent, polyLength);
			g.setColor(colors[poly]);
		    g.fillPolygon(xCurrent, yCurrent, polyLength);
		}
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
