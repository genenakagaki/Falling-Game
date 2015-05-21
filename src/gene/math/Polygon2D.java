package gene.math;

import gene.falling.*;
import gene.game.*;

import java.awt.Color;
import java.awt.Graphics2D;

public class Polygon2D {
	
	protected int x;
	protected int y;
	protected int z;
	
	protected int angle = 180;
	
	protected Vector[] vertices;
	
	protected int[] xScreen;
	protected int[] yScreen;
	
	private Vector[] currentCoords;
	
	private Vector[] rotatedModel;
	
	private Color color = new Color(80, 80, 80, 100);
	
	public Polygon2D(int x, int y, int z, Vector[] vertices) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.vertices = vertices;
		
		initialize();
	}
	
	private void initialize() {
		xScreen = new int[4];
		yScreen = new int[4];
		
		currentCoords = new Vector[4];	
		rotatedModel = new Vector[4];
	}
	
	public void update() {
		
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		double cosC = Lookup.cos[Camera.angle];
		double sinC = Lookup.sin[Camera.angle];
		
		for (int i = 0; i < vertices.length; i++) {
			// vertex coordinate in model space
			int xm = vertices[i].x;
			int ym = vertices[i].y;
			int zm = vertices[i].z;
			
			// Rotate model around z-axis
			int xr = (int)(-ym * sinA + xm * cosA);
			int yr = (int)(-ym * cosA - xm * sinA);
			int zr = zm;
			
			
			// Translate model to world
			int xw = xr + x;
			int yw = yr + y;
			int zw = zr + z;
			
			rotatedModel[i] = new Vector(xw, yw, zw);
			
			// Translate world to view
			int xv = xw - Camera.x;
			int yv = yw - Camera.y;
			int zv = zw - Camera.z;
			
			// Rotate Camera around y-axis
			int xc = (int)(xv * cosC - zv * sinC);
			int yc = yv;
			int zc = (int)(xv * sinC + zv * cosC);
			
			currentCoords[i] = new Vector(xc, yc, zc);
		}
	}
	
	public void draw(Graphics2D g) {
		int d = Camera.d;
		int xOrigin = Camera.xOrigin;
		int yOrigin = Camera.yOrigin;
		
		
		// Group vertices into onScreen and offScreen
		for (int i = 0; i < vertices.length; i++) {
			// vertex coordinates after rotate camera around y-axis
			int xc = currentCoords[i].x;
			int yc = currentCoords[i].y;
			int zc = currentCoords[i].z;
			
			if (zc > -10) {
				Vector adjacentVertex;
				
				// check if the adjacent vertices have z <= -10
				if (i == 0) {
					adjacentVertex = currentCoords[3];
				}
				else {
					adjacentVertex = currentCoords[i - 1];
				}
				
				// if one of the adjacent vertices have z > -10
				if (adjacentVertex.z > -10) {
					// check the other vertex
					if (i == 3) {
						adjacentVertex = currentCoords[0];
					}
					else {
						adjacentVertex = currentCoords[i + 1];
					}
					
				}
				// if both of the adjacent vertices have z > -10
				if (adjacentVertex.z > -10) {
					// overlap vertex currently working on to one of the adjacent vertex
					if (i == 3) {
						currentCoords[i] = currentCoords[0];
					}
					else {
						currentCoords[i] = currentCoords[i + 1];
					}
				}
				// if adjacent vertex has z <= -10
				else {
					int xc2 = adjacentVertex.x;
					int zc2 = adjacentVertex.z;
					
					int dx = xc - xc2;
					int dz = zc - zc2;

					zc = -10;
					
					xc = (zc - zc2) * dx / dz + xc2;
					
					// 3D perspective transformation
					xScreen[i] = d * xc / zc;
					yScreen[i] = d * yc / zc;					
					
					//Adjustment of screen origin
					xScreen[i] += xOrigin;
					yScreen[i] += yOrigin;	
				}
			}
			else {
				// 3D perspective transformation
				xScreen[i] = d * xc / zc;
				yScreen[i] = d * yc / zc;					
				
				//Adjustment of screen origin
				xScreen[i] += xOrigin;
				yScreen[i] += yOrigin;				
			}
		}
		
		drawPoly(g);
	}
	
	public void drawPoly(Graphics2D g) {
		g.setPaint(color);
		g.fillPolygon(xScreen, yScreen, 4);
		
		g.setColor(Color.BLUE);
		g.drawPolygon(xScreen, yScreen, 4);
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
	
	public void moveInBy(int dz) {
		z += dz;
	}
	
	public void moveOutBy(int dz) {
		z -= dz;
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public Vector[] getVertices() {
		return rotatedModel;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int getZ() {
		return z;
	}
}
