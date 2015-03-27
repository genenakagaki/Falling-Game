package gene.tank;

import gene.graphics.PolygonModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Gun extends PolygonModel{
	
	private boolean reloading;
	private int reloadCount;

	private Projectile[] bullets;
	private int ammo;
	
	public Gun(int x, int y, int angle, String gunFile){
		super(x, y, angle, gunFile);
			
		reloading = false;
		reloadCount = 0;
		
		ammo = 1000;	
		bullets = new Projectile[ammo];
		
		for (int i = 0; i < ammo; i++){
			bullets[i] = new Projectile(-100, -100, angle, "tank/projectile");
		}
		
	}
	
	public int[][] getCoords(String gunCoordFile){
		LinkedList<String> coordsList = getFileContent(gunCoordFile);
		return toInt2D(coordsList);
	}

	public Color[] getColors(String gunFile){
		LinkedList<String> colorList = getFileContent(gunFile);
		return toColors(colorList);
	}
	
	public void update(){
		super.update();
		
		if (reloading){
			reload();
		}
		
		for (int i = 0; i < bullets.length; i++){
			bullets[i].update();
		}
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		for (int i = 0; i < bullets.length; i++){
			bullets[i].draw(g);
		}
	}
	
	public void shoot(){
		if (ammo > 0){
			bullets[ammo-1].setPosition((int)x, (int)y, angle);
			bullets[ammo-1].moveForwardBy(60);
			bullets[ammo-1].shoot();
			
			reload();
			
			ammo --;
		}	
	}
	
	public void reload(){
		if (reloading){
			reloadCount --;
			if (reloadCount <= 0){
				reloading = false;
			}
		}
		else{	
			reloading = true;
			reloadCount = 40;
		}
	}
	
	public boolean hasCollidedWith(PolygonModel target){
		for (int i = 0; i < bullets.length; i++){
			if (bullets[i].hasCollidedWith(target)){
				return true;
			}
		}
		
		return super.hasCollidedWith(target);
	}
	
	
	/* --------------------
	    Boolean methods
	-------------------- */
	public boolean isReloading(){
		return reloading;
	}
	
	/* ------------------------------
	       Get and Set methods
	------------------------------ */
	public Projectile[] getBullets(){
		return bullets;
	}
}