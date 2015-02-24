import java.awt.Graphics;

// Axis Aligned Rectangle
public class AARect {
	
	int x; // position x
	int y; // position y
	
	int w; // width
	int h; // height
	
	public AARect(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void draw(Graphics g){
		g.drawRect(x, y, w, h);
	}
	
	public void moveBy(int dx, int dy){
		x += dx;
		y += dy;
	}
}
