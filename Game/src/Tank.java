import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Tank extends PolygonModel implements AI{
	
	private boolean boost;
	
	private Gun gun;
	
	private int recoilCount;
	private int recoilAngle;
	
	public int[][] getCoords(String tankFile){
		LinkedList<String> coordsList = getFileContent(tankFile);
		return toInt2D(coordsList);
	}

	public Color[] getColors(String tankFile){
		LinkedList<String> colorList = getFileContent(tankFile);
		
		return toColors(colorList);
	}
	
	public Tank(int x, int y, int angle, String tankFile){
		super(x, y, angle, tankFile);
		
		gun = new Gun(x, y, angle, "tank/norrisGun");
		
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
		if (!gun.isReloading() && !boost){
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
		int angleToTarget = angleTo(target);
		
		if (angleToTarget > 0){
			if (angleToTarget > 10){
				rotateRightBy(degrees);
			}
			else if (angleToTarget > 5){
				rotateRightBy(degrees/2);
			}
			else{
				rotateRightBy(1);
			}
		}
		else if (angleToTarget < 0){
			if (angleToTarget < -10){
				rotateLeftBy(degrees);
			}
			else if (angleToTarget < -5){
				rotateLeftBy(degrees/2);
			}
			else{
				rotateLeftBy(1);
			}
		}
	}
	
	public void moveTowards(PolygonModel target, int speed, int degrees){
		if (angleTo(target) > 0){
			rotateRightBy(degrees);
		}
		else if (angleTo(target) < 0){
			rotateLeftBy(degrees);
		}

		moveForwardBy(speed);
	}
	
	public void shootAt(PolygonModel target, int degrees){
		int angleToTarget = gun.angleTo(target);
		
		if (angleToTarget < 2 && angleToTarget > -2){
			shoot();
		}
		else{
			pointGunAt(target, degrees);
		}
	}
	
	public void pointGunAt(PolygonModel target, int degrees){
		int angleToTarget = gun.angleTo(target);
		
		if (angleToTarget > 0){
			if (angleToTarget > 10){
				gun.rotateRightBy(degrees);
			}
			else if (angleToTarget > 5){
				gun.rotateRightBy(degrees/2);
			}
			else{
				gun.rotateRightBy(1);
			}
		}
		else if (angleToTarget < 0){
			if (angleToTarget < -10){
				gun.rotateLeftBy(degrees);
			}
			else if (angleToTarget < -5){
				gun.rotateLeftBy(degrees/2);
			}
			else{
				gun.rotateLeftBy(1);
			}
		}
	}

	
	/* ------------------------
	    Get and Set methods
	------------------------ */
	public Gun getGun(){
		return gun;
	}
}