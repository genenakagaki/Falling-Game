

public class NPC extends PolygonModel2D{

	double xc;
	double yc;
	
	public NPC(int x, int y, int r) {
		super(x, y, r);
	}
	
	public double distanceTo(Circle c){
		xc = Lookup.cos[angle];
		yc = Lookup.sin[angle];
		 
		int xu = c.x - x;
		int yu = c.y - y;
		
		return xu * yc - xc * yu;
	}
	
	public void turnTowards(Circle c, int degrees){
		
		if (c.isToLeftOf(this)){
			rotateLeftBy(degrees);
		}
		if (c.isToRightOf(this)){
			rotateRightBy(degrees);
		}
	}
	
	public void moveTowards(Circle c){
		if (distanceTo(c) > 0){
			rotateLeftBy(5);
			moveForwardBy(5);
		}
		if (distanceTo(c) < 0){
			rotateRightBy(5);
			moveForwardBy(5);
		}
	}

	public int[][] getXCoords() {
		return null;
	}

	public int[][] getYCoords() {
		return null;
	}
}
