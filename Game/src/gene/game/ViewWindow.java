package gene.game;

import gene.math.*;

public class ViewWindow {
	
	private Point3D coord;
	
	private int width;
		
	private int viewAngle;
	private int cameraDist;
	
	public ViewWindow(Point3D cameraLocation, int viewAngle, int width) {
		this.coord = cameraLocation;
		this.viewAngle  = viewAngle;
		this.width = width;
		
		cameraDist = calcDist(viewAngle, width);
		
		coord.z += cameraDist;
	}
	
	public void setPosition(int x, int y) {
		coord.x = x;
		coord.y = y;
	}
	
	public void project(Point3D p) {
		p.x = cameraDist * p.x / -p.z;
		p.y = cameraDist * p.y / -p.z;
	}
	
	private int calcDist(int angle, int width) {
		double radAngle = angle/180 * Math.PI;
		
		return (int)((width/2) / Math.tan(radAngle));
	}

}
