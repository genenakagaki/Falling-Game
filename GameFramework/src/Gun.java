import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Gun extends PolygonModel{
	
	private boolean reloading;
	private int reloadCount;

	private Projectile[] bullet = new Projectile[1000];
	private int ammo;
	
	public int[][] getCoords(String gunCoordFile){
		LinkedList<String> coordsList = getFileContent(gunCoordFile);
		return toInt2D(coordsList);
	}

	public Color[] getColors(String gunFile){
		LinkedList<String> colorList = getFileContent(gunFile);
		
		return toColors(colorList);
	}
	
	public Gun(int x, int y, int angle, String gunFile){
		super(x, y, angle, gunFile);
			
		reloading = false;
		reloadCount = 0;
		
		for (int i = 0; i < bullet.length; i++){
			bullet[i] = new Projectile(-10, -10, angle, "tank/projectile");
		}
		
		ammo = bullet.length;	
	}
	
	
	public void update(){
		if (reloading){
			reload();
		}
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		for (int i = 0; i < bullet.length; i++){
			bullet[i].draw(g);
		}
	}
	
	public void shoot(){
		if (ammo > 0){
			bullet[ammo-1] = new Projectile((int)x, (int)y, angle, "tank/projectile");
			bullet[ammo-1].moveForwardBy(60);
			
			bullet[ammo-1].shot = true;
			
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
	
	/* --------------------
	    Boolean methods
	-------------------- */
	public boolean isReloading(){
		return reloading;
	}
}