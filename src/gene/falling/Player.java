package gene.falling;

import java.awt.Color;
import java.awt.Graphics;

import gene.game.Lookup;
import gene.math.AARect;
import gene.math.Polygon2D;
import gene.math.Polygon3D;
import gene.math.Vector;

public class Player extends Polygon2D {
	
	private static Vector[] vertices = {
			new Vector(-15, 9, 0),
			new Vector(-15, -9, 0),
			new Vector(15, -9, 0),
			new Vector(15, 9, 0)
	};

	public Player(int x, int y, int z) {
		super(x, y, z, vertices);
	}
	
	public void rotateLeftBy(int degrees) {
		angle -= degrees;
		if (angle < 0) {
			angle += 360;
//			System.out.println("in if " + angle);
		}
//		System.out.println("rotate left by" + angle);
	}
	
	public void rotateRightBy(int degrees) {
		angle += degrees;
		if (angle >= 360) {
			angle -= 360;
		}
//		System.out.println("rotate right by" + angle);
	}
	
	public boolean hasCollidedWith(Layer[] layers) {
		for (int i = 0; i < layers.length; i++) {
			if (layers[i].isOnScreen()) {
				int yTop    = layers[i].top.y;
				int yBottom = layers[i].bottom.y;
				int xLeft   = layers[i].left.x;
				int xRight  = layers[i].right.x;
				
				int xh = xLeft + (xRight - xLeft) / 2;
				int yh = yTop + (yBottom - yTop) / 2;
				int zh = layers[i].getHole().getZ();
				
				int holeAngle = layers[i].holeAngle;
				
				// Flip to match screen
				double cosA = Lookup.cos[180];
				double sinA = Lookup.sin[180];
				
				int xr = (int)(-yh * sinA + xh * cosA);
				int yr = (int)(-yh * cosA - xh * sinA);
				int zr = zh;
								
				if ( (z <= zr) && (z > zr -6) ) {
					if ( (x < xr - 10) || (x > xr + 10) ||
							(y < yr - 10) || (y > yr + 10) ) {
						return true;
					}
					else {
						if (angle > 180) {
							angle -= 180;
						}
						if (holeAngle > 180) {
							holeAngle -= 180;
						}
						holeAngle -= (holeAngle - 90) * 2;
						if (holeAngle > 180) {
							holeAngle -= 180;
						}
						
						if ( (angle < holeAngle - 20) || (angle > holeAngle + 20) ) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
