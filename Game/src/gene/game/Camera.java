package gene.game;

import gene.math.*;

import java.awt.Point;
import java.awt.Rectangle;

public class Camera {
	
	private Point3D coord;
	
	private ViewWindow viewWindow;
	
	public Camera(int x, int y, int z, int viewAngle, int windowWidth) {
		this.coord = coord;
		
		viewWindow = new ViewWindow(coord, viewAngle, windowWidth);
	}
}
