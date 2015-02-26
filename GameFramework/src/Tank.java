import java.awt.Graphics;
import java.awt.Color;

public class Tank extends PolygonModel2D{
	
	boolean boost;
	
	Gun gun;
	
	int recoilCount;
	int recoilAngle;
	
	public int[][] getXCoords(){
		int[][] xCoords = { 
			{42, 42, -35, -35}, 
			{42, 42, -35, -35},
			{38, 38},
			{30, 30},
			{22, 22},
			{14, 14},
			{6, 6},
			{-2, -2},
			{-10, -10},
			{-18, -18},
			{-26, -26},
			{-34, -34},
			{38, 38},
			{30, 30},
			{22, 22},
			{14, 14},
			{6, 6},
			{-2, -2},
			{-10, -10},
			{-18, -18},
			{-26, -26},
			{-34, -34},
			{38, 38, 30, 30, 38, 38, -30, -38, -38, -30},
			{38, 38, 30, 30, 38, 38, -25, -30, -30, -25}
		};
		return xCoords;
	}
	
	public int[][] getYCoords(){
		int[][] yCoords = {
			{22,  30,  30,  22}, 
			{-22, -30, -30, -22},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{23, 29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-23, -29},
			{-25, -15, -8, 8, 15, 25,  25, 18, -18, -25},
			{-18, -15, -8, 8, 15, 18,  18, 15, -15, -18}
		};
		return yCoords;
	}
	
	public Color[] getColors(){
		Color[] colors = {
			new Color(150,85,10),
			new Color(150,85,10),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(10,15,20),
			new Color(190,115,40),
			new Color(170,95,20)
		};
		return colors;
	}
	
	public Tank(int x, int y, int angle){
		super(x, y, angle);

		gun = new Gun(x, y, angle);
		
		recoilCount = 0;
	}
	
	public void draw(Graphics g, double zoom){
		super.draw(g, zoom);
		gun.draw(g, zoom);
		
		if (recoilCount > 0){
			if (recoilCount > 4) {
				moveForwardBy(1, recoilAngle);
			}
			gun.moveForwardBy(1, recoilAngle);
			
			recoilCount--;
		}
	}
	
	public void shoot(GameFramework game){
		if (!gun.reloading && !boost){
			gun.shoot();
			
			moveBackwardBy(5, gun.angle);
			gun.moveBackwardBy(9);
			
			recoilCount = 9;
			recoilAngle = gun.angle;
			
			game.audio.shoot1AC.play();
		}	
	}
	
	public void moveForwardBy(int dist){
		if (boost){
			super.moveForwardBy(dist * 2);
			gun.moveForwardBy(dist * 2, angle);
		}
		super.moveForwardBy(dist);
		gun.moveForwardBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist){
		super.moveBackwardBy(dist);
		gun.moveBackwardBy(dist, angle);
	}
	
	public void rotateLeftBy(int degrees){
		super.rotateLeftBy(degrees);
		gun.rotateLeftBy(degrees);
	}
	
	public void rotateRightBy(int degrees){
		super.rotateRightBy(degrees);
		gun.rotateRightBy(degrees);
	}
}