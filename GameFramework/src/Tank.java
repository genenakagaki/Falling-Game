import java.awt.Graphics;

public class Tank extends PolygonModel2D{
	
	int gunAngle;
	
	public int[][] getXCoords(){
		
		int[][]Xpts = {
			{50, 50, -50, -50}, 
			{40, 40, -40, -40}, 
			{40, 40, -40, -40}, 
			{40, 40,  15,  15}
		};
		return Xpts;
	}
	
	public int[][] getYCoords(){
		
		int[][] Ypts = {
			{-25,  25,  25, -25}, 
			{ 25,  35,  35,  25}, 
			{-25, -35, -35, -25}, 
			{ -5,   5,   5,  -5}
		};
		return Ypts;
	}
	
	public Tank(int x, int y, int angle){
		super(x, y, angle);
	}
	
	public void draw(Graphics g){
		super.draw(g);
		
		g.drawOval(x -15, y -15, 30, 30);
	}
	
	public void rotateGunRightBy(int degrees){
		gunAngle += degrees;
		if (angle >= 360){
			angle = 0;
		}
	}
	public void rotateGunLeftBy(int degrees){
		gunAngle -= degrees;
		if (angle < 0){
			angle += 360;
		}
	}
}

/*
public void draw(Graphics g){
	//rotated x and y points
	int[] rXpts = new int[4];
	int[] rYpts = new int[4];
	
	double cosA = Lookup.cos[angle];
	double sinA = Lookup.sin[angle];
	
	for (int poly = 0; poly < Xpts.length; poly++){
		if (poly == 3){
			int ang = angle + gunAngle;
			
			if (ang >= 360) ang -= 360;
			if (ang < 0)    ang += 360;
			
			cosA = Lookup.cos[ang];
			sinA = Lookup.sin[ang];
		}
		
		for (int i = 0; i < rXpts.length; i++){
			rXpts[i] = (int)(Xpts[poly][i] * cosA - Ypts[poly][i] * sinA);
			rYpts[i] = (int)(Xpts[poly][i] * sinA + Ypts[poly][i] * cosA);
		}
		
		// current x and y points
		int[] cXpts = new int[4];
		int[] cYpts = new int[4];
		
		for (int i = 0; i < rXpts.length; i++){
			cXpts[i] = rXpts[i] + x;
			cYpts[i] = rYpts[i] + y;
		}
		g.drawPolygon(cXpts, cYpts, 4);
	}
	
	g.drawOval(x -15, y -15, 30, 30);
}
*/