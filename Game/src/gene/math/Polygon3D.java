package gene.math;

import gene.tennis.*;

import java.awt.Color;
import java.awt.Graphics;

public class Polygon3D {
	
	private int x;
	private int y;
	private int z;
	
	private Vector[] vertices = {
			new Vector(-100, 0,  100),
			new Vector(-100, 0, -100),
			new Vector( 100, 0, -100),
			new Vector( 100, 0,  100)
	};
	
	private int[] xCurrent;
	private int[] yCurrent;
	
	public Polygon3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		xCurrent = new int[vertices.length];
		yCurrent = new int[vertices.length];
	}
	
	public void update() {
		int cameraDist = Tennis.getViewWindow().getCameraDist();
		for (int i = 0; i < vertices.length; i++) {
			Vector vertex = vertices[i];
			
			// translate polygon to world
			vertex.x += x;
			vertex.y += y;
			vertex.z += z;
			
			// translate polygon to view window
			xCurrent[i] = (int)(cameraDist * vertex.x / -vertex.z);
			yCurrent[i] = (int)(cameraDist * vertex.y / -vertex.z);
		}
		
		// Find normal to polygon
//		int[] vector_u = {
//				xc[1] - xc[0],
//				yc[1] - yc[0],
//				zc[1] - zc[0]
//		};
//		int[] vector_v = {
//				xc[2] - xc[1],
//				yc[2] - yc[1],
//				zc[2] - zc[1]
//		};
//		int[] crossProduct = Vector.crossProductOf(vector_u, vector_v);
//		
//		// find dot product of normal and vector from vertex in polygon ot camera
//		
//		// can you see the polygon?
//		
//		if (dotProduct > 0) { // can see the polygon
//			for each vector
//				
//		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillPolygon(xCurrent, yCurrent, 4);
	}
}
