package gene.falling;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import gene.math.Polygon2D;
import gene.math.Polygon3D;
import gene.math.Vector;

public class Layer {
	
	private static Vector[] holeVertices = {
			new Vector( 20,  12, 0),
			new Vector( 20, -12, 0),
			new Vector(-20, -12, 0),
			new Vector(-20,  12, 0)
	};
	
	private static Vector[][] wallVertices = new Vector[8][];
	
	private Polygon3D wall;
	private Polygon2D hole;
	
	private int z;
	
	// Hole vertices
	public Vector top;
	public Vector bottom;
	public Vector left;
	public Vector right;
	
	public int holeAngle;
	
	public Layer() {
		generateHole();
		
		generateWall();
	}
	
	public void update() {
//		wall.update();
		hole.update();
		z = hole.getZ();
	}
	
	public void draw(Graphics2D g) {
		wall.draw(g);
//		hole.draw(g);
	}
	
	public void showLayer() {
		moveInBy(800);
	}
	
	public boolean isOnScreen() {
		return (z > 10);
	}
	
	public Polygon2D getHole() {
		return hole;
	}
	
	// --------------------------------------------------------------------------------
	//   Movements
	// --------------------------------------------------------------------------------
	public void moveUpBy(int dy) {
		wall.moveUpBy(dy);
		hole.moveUpBy(dy);
	}
	
	public void moveDownBy(int dy) {
		wall.moveDownBy(dy);
		hole.moveDownBy(dy);
	}
	
	public void moveLeftBy(int dx) {
		wall.moveLeftBy(dx);
		hole.moveLeftBy(dx);
	}
	
	public void moveRightBy(int dx) {
		wall.moveRightBy(dx);
		hole.moveRightBy(dx);
	}
	
	public void moveInBy(int dz) {
		wall.moveInBy(dz);
		hole.moveInBy(dz);
	}
	
	public void moveOutBy(int dz) {
		wall.moveOutBy(dz);
		hole.moveOutBy(dz);
	}
	
	// --------------------------------------------------------------------------------
	//   Generate methods (hole and wall)
	// --------------------------------------------------------------------------------
	public void generateHole() {
		int x = (int)(Math.random() * 640 - 320);
		int y = (int)(Math.random() * 640 - 320);
		
		hole = new Polygon2D(x, y, -100, holeVertices);
		
		holeAngle = (int)(Math.random() * 360);
		hole.setAngle(holeAngle);
		
		hole.update();
	}
	
	public void generateWall() {
		Vector[] holeCurrentVertices = hole.getVertices();
		
		// Determine the location of each vertex (top bottom left right)
		top    = holeCurrentVertices[0];
		bottom = holeCurrentVertices[0];
		left   = holeCurrentVertices[0];
		right  = holeCurrentVertices[0];
		for (Vector vertex: holeCurrentVertices) {
			if (vertex.x < left.x) {
				left = vertex;
			}
			else if (vertex.x > right.x) {
				right = vertex;
			}
			
			if (vertex.y > top.y) {
				top = vertex;
			}
			else if (vertex.y < bottom.y) {
				bottom = vertex;
			}
		}
		
		Vector[] sideLeftOfHole = {
				new Vector(left.x, left.y, 0),
				new Vector(left.x, left.y, 20),
				new Vector(top.x, top.y, 20),
				new Vector(top.x, top.y, 0)
		};
		wallVertices[0] = sideLeftOfHole;
		
		Vector[] sideTopOfHole = {
				new Vector(top.x, top.y, 0),
				new Vector(top.x, top.y, 20),
				new Vector(right.x, right.y, 20),
				new Vector(right.x, right.y, 0)
		};
		wallVertices[1] = sideTopOfHole;
		
		Vector[] sideRightOfHole = {
				new Vector(right.x, right.y, 0),
				new Vector(right.x, right.y, 20),
				new Vector(bottom.x, bottom.y, 20),
				new Vector(bottom.x, bottom.y, 0)
		};
		wallVertices[2] = sideRightOfHole;
		
		Vector[] sideBottomOfHole = {
				new Vector(bottom.x, bottom.y, 0),
				new Vector(bottom.x, bottom.y, 20),
				new Vector(left.x, left.y, 20),
				new Vector(left.x, left.y, 0)
		};
		wallVertices[3] = sideBottomOfHole;
		
		if (top.x < bottom.x) {
			Vector[] leftOfHole = {
					new Vector(  -500,   -500, 0),
					new Vector(left.x, left.y, 0), 
					new Vector( top.x,  top.y, 0),
					new Vector(  -500,    500, 0)
			};
			wallVertices[4] = leftOfHole;
			
			Vector[] topOfHole = {
					new Vector(   -500,     500, 0),
					new Vector(  top.x,   top.y, 0),
					new Vector(right.x, right.y, 0),
					new Vector(    500,     500, 0)
			};
			wallVertices[5] = topOfHole;
			
			Vector[] rightOfHole = {
					new Vector(     500,      500, 0),
					new Vector( right.x,  right.y, 0),
					new Vector(bottom.x, bottom.y, 0),
					new Vector(     500,     -500, 0)
			};
			wallVertices[6] = rightOfHole;
			
			Vector[] bottomOfHole = {
					new Vector(     500,     -500, 0),
					new Vector(bottom.x, bottom.y, 0),
					new Vector(left.x, left.y, 0),
					new Vector(    -500,     -500, 0)
			};
			wallVertices[7] = bottomOfHole;
		}
		else {
			Vector[] leftOfHole = {
					new Vector(  -500,   -500, 0),
					new Vector(bottom.x, bottom.y, 0),
					new Vector(left.x, left.y, 0), 
					new Vector(  -500,    500, 0)
			};
			wallVertices[4] = leftOfHole;
			
			Vector[] topOfHole = {
					new Vector(   -500,     500, 0),
					new Vector(left.x, left.y, 0),
					new Vector(  top.x,   top.y, 0),
					new Vector(    500,     500, 0)
			};
			wallVertices[5] = topOfHole;
			
			Vector[] rightOfHole = {
					new Vector(     500,      500, 0),
					new Vector(top.x, top.y, 0),
					new Vector( right.x,  right.y, 0),
					new Vector(     500,     -500, 0)
			};
			wallVertices[6] = rightOfHole;
			
			Vector[] bottomOfHole = {
					new Vector(     500,     -500, 0),
					new Vector(right.x, right.y, 0),
					new Vector(bottom.x, bottom.y, 0),
					new Vector(    -500,     -500, 0)
			};
			wallVertices[7] = bottomOfHole;
		}
		wall = new Polygon3D(0, 0, -100, wallVertices);
	}
}
