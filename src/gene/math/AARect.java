package gene.math;

// Axis Aligned Rectangle
public class AARect {

	public int x;
	public int y;
	
	public int w; // width
	public int h; // height
	
	public AARect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void moveUpBy(int dy) {
		y -= dy;
	}
	
	public void moveDownBy(int dy) {
		y += dy;
	}
	
	public void moveLeftBy(int dx) {
		x -= dx;
	}
	
	public void moveRightBy(int dx) {
		x += dx;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public boolean contains(int x, int y) {
		return ( (this.x < x)     && 
				 (this.x + w > x) &&
				 (this.y < y)     &&
				 (this.y + h > y) );
	}
}
