import java.awt.Graphics;
import java.awt.Color;

public class Gun extends PolygonModel2D{
	
	boolean reloading;
	long reloadCount;

	Projectile[] bullet = new Projectile[1000];
	int ammo;
	
	public int[][] getXCoords(){
		int[][] xCoords = {
			{0, 20, 20, 0},
			{0, 18, 18, 0},
			{-25, 0, 0, -25},
			{-25, 0, 0, -25},
			{60, 60, 18, 18},
			{60, 60, 18, 18},
			{60, 60, 18, 18}
		};
		return xCoords;
	}
	
	public int[][] getYCoords(){
		int[][] yCoords= {
			{20, 10, -10, -20},
			{17, 7, -7, -17},
			{10, 20, -20, -10},
			{7, 17, -17, -7},
			{4, -4, -4, 4},
			{4, 2, 2, 4},
			{-2, -4, -4, -2}
		};
		return yCoords;
	}
	
	public Color[] getColors(){
		Color[] colors = {
			new Color(200, 180, 30),
			new Color(220, 200, 50),
			new Color(200, 220, 120),
			new Color(220, 240, 140),
			new Color(240, 210, 20),
			new Color(220, 190, 15),
			new Color(220, 190, 15)
		};
		return colors;
	}
	
	public Gun(int x, int y, int angle){
		super(x, y, angle);
		
		reloading = false;
		reloadCount = 0;
		
		for (int i = 0; i < bullet.length; i++){
			bullet[i] = new Projectile(-10, -10, angle);
		}
		ammo = bullet.length;	
	}
	
	
	public void update(){
		if (reloading){
			reload();
		}
	}
	
	public void draw(Graphics g, double zoom){
		super.draw(g, zoom);
		
		for (int i = 0; i < bullet.length; i++){
			bullet[i].draw(g, zoom);
		}
	}
	
	public void shoot(){
		if (ammo > 0){
			bullet[ammo-1] = new Projectile(x, y, angle);
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
			reloadCount = 80;
		}
	}
}