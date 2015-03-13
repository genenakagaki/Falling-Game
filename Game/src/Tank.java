import java.awt.Graphics;

public class Tank extends PolygonModel implements AI{
	
	private boolean boost;
	
	private Gun gun;
	
	private int recoilCount;
	private int recoilAngle;
	
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
	
	/* --------------------
	       Overrides
	 -------------------- */
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

	
	/* --------------------
	      AI methods
	-------------------- */
	public void turnTowards(PolygonModel target, int degrees) {
		double xc = Lookup.cos[angle];
		double yc = Lookup.sin[angle];
		
		double angleToTarget = (target.x - x) * yc - (target.y - y) * xc;
		
		if (angleToTarget > 0){
			if (angleToTarget > 10){
				rotateLeftBy(degrees);
			}
			else if (angleToTarget > 5){
				rotateLeftBy(degrees/2);
			}
			else{
				rotateLeftBy(1);
			}
		}
		else if (angleToTarget < 0){
			if (angleToTarget < -10){
				rotateRightBy(degrees);
			}
			else if (angleToTarget < -5){
				rotateRightBy(degrees/2);
			}
			else{
				rotateRightBy(1);
			}
		}
	}
	
	public void moveTowards(PolygonModel target, int speed, int degrees){
		if (distanceTo(target) > 0){
			rotateLeftBy(degrees);
			moveForwardBy(speed);
		}
		if (distanceTo(target) < 0){
			rotateRightBy(degrees);
			moveForwardBy(speed);
		}
	}
	
	/* ------------------------
	    Get and Set methods
	------------------------ */
	public Gun getGun(){
		return gun;
	}
}