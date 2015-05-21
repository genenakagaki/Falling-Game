package gene.game;

import gene.falling.*;
import gene.math.*;

import java.awt.Point;
import java.awt.Rectangle;

public class Camera {
	
	public static int x = 0;
	public static int y = 0;
	public static int z = 0;
	public static int d = 400;
	
	public static int angle = 180;
	
	public static int xOrigin;
	public static int yOrigin;
	
	public Camera() {
		xOrigin = FallingGame.getWidth()  /2;
		yOrigin = FallingGame.getHeight() /2;
	}
	
	public void moveUpBy(int dy) {
		y += dy;
	}
	
	public void moveDownBy(int dy) {
		y -= dy;
	}
	
	public void moveLeftBy(int dx) {
		x -= dx;
	}
	
	public void moveRightBy(int dx) {
		x += dx;
	}
	
	public void moveInBy(int dz) {
		z += dz;
	}
	
	public void moveOutBy(int dz) {
		z -= dz;
	}
	
	public void rotateLeftBy(int degrees) {
		angle -= degrees;
		if (angle < 0) {
			angle += 360;
		}
	}
	
	public void rotateRightBy(int degrees) {
		angle += degrees;
		if (angle >= 360) {
			angle -= 360;
		}
		
	}
	
//	public void moveForwardBy(int dist) {
//		z -= (int)(dist * Lookup.cos[angle]);
//		x -= (int)(dist * Lookup.sin[angle]);
//	}
//	
//	public void moveBackwardBy(int dist) {
//		z += (int)(dist * Lookup.cos[angle]);
//		x += (int)(dist * Lookup.sin[angle]);
//	}
}
