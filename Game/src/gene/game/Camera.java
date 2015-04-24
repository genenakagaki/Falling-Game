package gene.game;

import gene.math.*;

import java.awt.Point;
import java.awt.Rectangle;

public class Camera {
	
	private int x, y, z;
	
	private int angle;
	
	private ViewWindow viewWindow;
	
	public Camera(int x, int y, int z, int angle, int viewAngle, int windowWidth) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
		
		viewWindow = new ViewWindow(x, y, z, viewAngle, windowWidth);
	}
	
	public ViewWindow getViewWindow() {
		return viewWindow;
	}
}
