
import java.awt.Graphics;

public class OutermostPolygon {
	
	public Line[] lines;
		
	public OutermostPolygon(int x[], int y[]){
		Line line;
		for (int i = 0; i < x.length; i++){
			line = new Line(x[i], y[i], x[i+1], y[i+1]);
		}
	}
	
	public void draw(Graphics g){}
	
	public boolean hasCollidedWith(Line line){
		return line.distanceTo(x, y) <= r;
	}
	
	
	public boolean hasCollidedWith(OutermostPolygon polygon){
		
		return polygon.distanceTo(x, y);
	}
	
	public double distanceTo(int x, int y){
		return (x - x1) * yc - (y - y1) * xc;
	}
}
