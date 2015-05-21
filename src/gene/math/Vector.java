package gene.math;

public class Vector {

	public int x;
	public int y;
	public int z;
	
	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector crossProductOf(Vector u, Vector v) {
		Vector crossProduct = new Vector(u.y*v.z - v.y*u.z,
										 u.x*v.z - v.x*u.z,
										 u.x*v.y - u.x*v.y);
		return crossProduct;
	}
	
//	public int dotProductOf(int[] u, int[] v) {
//		int[] dotProduct = {
//				
//		}
//		return dotProduct;
//	}
}
