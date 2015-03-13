import java.awt.Graphics;

public abstract class PolygonModel {

	public double x;
	public double y;

	public int angle;
	
	public int[][] xCoords = getXCoords();
	public int[][] yCoords = getYCoords();
	
	public PolygonModel(int x, int y, int angle){
		this.x = x;
		this.y = y;
		
		this.angle = angle;
	}
	
	public abstract int[][] getXCoords();
	public abstract int[][] getYCoords();
	
	public void draw(Graphics g){
		double[] xRotated = new double[4];
		double[] yRotated = new double[4];
		
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		for (int poly = 0; poly < xCoords.length; poly++){

			for (int i = 0; i < xRotated.length; i++){
				xRotated[i] = xCoords[poly][i] * cosA - yCoords[poly][i] * sinA;
				yRotated[i] = xCoords[poly][i] * sinA + yCoords[poly][i] * cosA;
			}
			
			int[] xCurrent = new int[4];
			int[] yCurrent = new int[4];
			
			for (int i = 0; i < xRotated.length; i++){
				xCurrent[i] = (int)(xRotated[i] + x);
				yCurrent[i] = (int)(yRotated[i] + y);
			}
			g.drawPolygon(xCurrent, yCurrent, 4);
		}
	}

	public void moveBy(int dist, int angle){	
		x += (dist * Lookup.cos[angle]);
		y += (dist * Lookup.sin[angle]);
	}
	
	public void moveForwardBy(int dist){
		moveBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist){
		x -= (dist * Lookup.cos[angle]);
		y -= (dist * Lookup.sin[angle]);
	}
	
	public void rotateRightBy(int degrees){
		this.angle += degrees;
		
		if (angle >= 360){
			angle -= 360;
		}
		else if (angle < 0){
			angle += 360;
		}
	}
	
	public void rotateLeftBy(int degrees){
		this.angle -= degrees;
		
		if (angle >= 360){
			angle -= 360;
		}
		else if (angle < 0){
			angle += 360;
		}
	}
	
	public double distanceTo(PolygonModel target){
		double xc = Lookup.cos[angle];
		double yc = Lookup.sin[angle];
		 
		int xu = (int)(target.x - x);
		int yu = (int)(target.y - y);
		
		return xu * yc - xc * yu;
	}
}
