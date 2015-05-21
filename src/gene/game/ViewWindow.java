package gene.game;

import gene.math.*;

public class ViewWindow {
	
	private int x, y, z;
	
	private int cameraDist;
	
	public ViewWindow(int x, int y, int z, int viewAngle, int windowWidth) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		cameraDist = calcDist(viewAngle, windowWidth);
		
		this.z += cameraDist;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private int calcDist(int angle, int width) {
		double radAngle = angle/180 * Math.PI;
		
		return (int)((width/2) / Math.tan(radAngle));
	}
	
	public int getCameraDist() {
		return cameraDist;
	}

}
