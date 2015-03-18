import java.awt.*;
import java.util.LinkedList;

public class Projectile extends PolygonModel{
	
	boolean shot;
	
	public int[][] getCoords(String projectileFile){
		LinkedList<String> coordsList = getFileContent(projectileFile);
		return toInt2D(coordsList);
	}

	public Color[] getColors(String projectileFile){
		LinkedList<String> colorList = getFileContent(projectileFile);
		
		return toColors(colorList);
	}
	
	public Projectile(int x, int y, int angle, String projectileFile){
		super(x, y, angle, projectileFile);
		shot = false;
	}
	
	public void draw(Graphics g){
		if (shot){
			super.draw(g);
			moveForwardBy(20);
		}
	}
}
