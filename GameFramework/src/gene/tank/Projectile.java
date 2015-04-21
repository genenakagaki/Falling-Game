package gene.tank;

import gene.graphics.PolygonModel;

import java.awt.*;
import java.util.LinkedList;

public class Projectile extends PolygonModel{
	
	private int life;
	
	private boolean shot;

	public Projectile(int x, int y, int angle, String projectileFile) {
		super(x, y, angle, projectileFile);
		life = 100;
		shot = false;
	}
	
	public int[][] getCoords(String projectileFile) {
		LinkedList<String> coordsList = getFileContent(projectileFile);
		return toInt2D(coordsList);
	}

	public Color[] getColors(String projectileFile) {
		LinkedList<String> colorList = getFileContent(projectileFile);
		return toColors(colorList);
	}
	
	public void update() {
		if (shot && life > 0) {
			super.update();
			
			life--;
		}
		else {
			shot = false;
		}
		
//		if (life <= 0) {
//			x = -100;
//			y = -100;
//		}
	}
	
	public void draw(Graphics g) {
		if (shot && life > 0) {
			super.draw(g);
			moveForwardBy(20);
		}
	}
	
	public void shoot() {
		shot = true;
	}
	
	public void setPosition(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	/* ------------------------------
	       Get and Set methods
	------------------------------ */
	public boolean isShot() {
		return shot;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
}
