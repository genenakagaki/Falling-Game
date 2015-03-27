package gene.tank;

public class Camera {
	
	int x;
	int y;
	int z;
	
	public void moveLeftBy(int dx) {
		x -= dx;
	}
	
	public void moveRightBy(int dx) {
		x += dx;
	}

}
