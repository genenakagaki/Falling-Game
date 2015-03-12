import java.awt.*;

public class Projectile extends PolygonModel2D{
	
	boolean shot;
	
	public int[][] getXCoords(){
		int[][] xCoords = {
				{2, 4, 4, 2},
				{-4, 2, 2, -4}
		};
		return xCoords;
	}
	
	public int[][] getYCoords(){
		int[][] yCoords = {
				{4, 2, -2, -4},
				{4, 4, -4, -4}
		};
		return yCoords;
	}
	
	public Projectile(int x, int y, int angle){
		super(x, y, angle);
		shot = false;
	}
	
	public void draw(Graphics g){
		if (shot){
			super.draw(g);
			moveForwardBy(20);
		}
	}
}
