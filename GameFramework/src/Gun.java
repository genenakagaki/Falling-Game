import java.awt.Graphics;

public class Gun extends PolygonModel2D{
	
	boolean reloading;
	long reloadCount;

	Projectile[] bullet = new Projectile[1000];
	int ammo;
	
	public int[][] getXCoords(){
		int[][] xCoords = {
			{60, 60, 20, 20},
			{0, 20, 20, 0},
			{-25, 0, 0, -25}
		};
		return xCoords;
	}
	
	public int[][] getYCoords(){
		int[][] yCoords= {
			{4, -4, -4, 4},
			{20, 10, -10, -20},
			{10, 20, -20, -10}
		};
		return yCoords;
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
	
	public void draw(Graphics g){
		super.draw(g);
		
		for (int i = 0; i < bullet.length; i++){
			bullet[i].draw(g);
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