package gene.math;

public class Vector {

	public float x;
	public float y;
	public float z;
	
	public Vector(float x, float y, float z) {
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
