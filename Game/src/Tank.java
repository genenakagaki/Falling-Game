import java.awt.Graphics;

public class Tank extends PolygonModel2D{
	
	private boolean boost;
	
	Gun gun;
	
	int recoilCount;
	int recoilAngle;
	
	public int[][] getXCoords(){
		int[][] xCoords = {
			{38, 38, -38, -38}, 
			{35, 35, -35, -35}, 
			{35, 35, -35, -35}
		};
		return xCoords;
	}
	
	public int[][] getYCoords(){
		int[][] yCoords = {
			{-25,  25,  25, -25}, 
			{ 25,  30,  30,  25}, 
			{-25, -30, -30, -25}
		};
		return yCoords;
	}
	
	public Tank(int x, int y, int angle){
		super(x, y, angle);

		gun = new Gun(x, y, angle);
		
		recoilCount = 0;
	}
	
	public void draw(Graphics g){
		super.draw(g);
		gun.draw(g);
		
		if (recoilCount > 0){
			if (recoilCount > 4) {
				moveBy(1, recoilAngle);
			}
			gun.moveBy(1, recoilAngle);
			
			recoilCount--;
		}
	}
	
	public void shoot(){
		if (!gun.reloading && !boost){
			gun.shoot();
			
			moveBy(-5, gun.angle);
			gun.moveBackwardBy(9);
			
			recoilCount = 9;
			recoilAngle = gun.angle;
		}
	}
	
	public void moveForwardBy(int dist){
		if (boost){
			super.moveForwardBy(dist * 3);
			gun.moveForwardBy(dist * 3);
		}
		super.moveForwardBy(dist);
		gun.moveBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist){
		super.moveBackwardBy(dist);
		gun.moveBy(-dist, angle);
	}
	
	public void rotateLeftBy(int degrees){
		super.rotateLeftBy(degrees);
		gun.rotateLeftBy(degrees);
	}
	
	public void rotateRightBy(int degrees){
		super.rotateRightBy(degrees);
		gun.rotateRightBy(degrees);
	}
	
	public void setBoost(boolean boost){
		this.boost = boost;
	}
	
	public Gun getGun(){
		return gun;
	}
}