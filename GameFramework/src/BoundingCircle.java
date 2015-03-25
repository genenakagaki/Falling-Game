import java.awt.Graphics;


public class BoundingCircle {
	
	public int x;
	public int y;
	
	public int xCurrent;
	public int yCurrent;
	
	public double radius;
	
	public BoundingCircle(int x, int y, int[][] xCoords, int[][] yCoords) {
		int[] minMax = getMinMax(xCoords);
		int min = minMax[0];
		int max = minMax[1];
		
		int width = max - min;
		this.x = (max + min) / 2;
		
		minMax = getMinMax(yCoords);
		
		min = minMax[0];
		max = minMax[1];
		
		int height = max - min;
		this.y = (max + min) / 2;
	
		radius = Math.sqrt(width * width + height * height)/2;		
	}
	
	private int[] getMinMax(int[][] coords) {
		int min = 1000;
		int max = -1000;
		
		for (int i = 0; i < coords.length; i++) {
			
			for (int j = 0; j < coords[i].length; j++) {
				if (coords[i][j] > max) {
					max = coords[i][j];
				} 
				else if (coords[i][j] < min) {
					min = coords[i][j];
				}
			}
		}
		
		int[] minMax = {min, max};
		return minMax;
	}
	
	public boolean hasCollidedWith(BoundingCircle target){
		int xDist = xCurrent - target.xCurrent;
		int yDist = yCurrent - target.yCurrent;
		int dist = xDist*xDist + yDist*yDist;
		
		return (dist < radius*radius + 2*radius*target.radius + target.radius*target.radius);
	}
	
	public void draw(Graphics g){
		g.drawOval((int)(xCurrent - radius), (int)(yCurrent - radius), (int)radius*2, (int)radius*2);
	}
}
