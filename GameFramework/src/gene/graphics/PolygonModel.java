package gene.graphics;

import gene.tank.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.LinkedList;

public abstract class PolygonModel {

	// turning point
	public double x;
	public double y;

	public int angle;
	
	public final int[][] xCoords;
	public final int[][] yCoords;
	public Color[] colors; 
	
	public int[][] xCurrent;
	public int[][] yCurrent;
	
	public BoundingCircle boundingCircle;

	public PolygonModel(int x, int y, int angle, String fileName){
		this.x = x;
		this.y = y;
		
		this.angle = angle;
		
		xCoords = getCoords(fileName +"_x");
		yCoords = getCoords(fileName +"_y");
		colors  = getColors(fileName +"_color");
				
		initCurrentCoords();

		boundingCircle = new BoundingCircle(x, y, xCoords, yCoords);
	}
	
	private void initCurrentCoords(){
		xCurrent = new int[xCoords.length][];
		yCurrent = new int[yCoords.length][];
		
		for (int i = 0; i < xCoords.length; i++){
			int length = xCoords[i].length;
			xCurrent[i] = new int[length];
			yCurrent[i] = new int[length];
		}
	}
	
	public abstract int[][] getCoords(String file);
	public abstract Color[] getColors(String file);
		
	/* --------------------
	      Game update
	-------------------- */
	public void update(){
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];
		
		for (int poly = 0; poly < xCoords.length; poly++){
			int numCoords = xCoords[poly].length;
			
			for (int i = 0; i < numCoords; i++){
				double xRotated = xCoords[poly][i] * cosA - yCoords[poly][i] * sinA;
				double yRotated = xCoords[poly][i] * sinA + yCoords[poly][i] * cosA;
				
				xCurrent[poly][i] = (int)(xRotated + x);
				yCurrent[poly][i] = (int)(yRotated + y);
			}
		}
		
		boundingCircle.xCurrent = (int)(boundingCircle.x * cosA - boundingCircle.y * sinA + x);
		boundingCircle.yCurrent = (int)(boundingCircle.x * sinA + boundingCircle.y * cosA + y);
	}
	
	public void draw(Graphics g){
		for (int poly = 0; poly < xCoords.length; poly++){
			int numCoords = xCoords[poly].length;
			
			g.drawPolygon(xCurrent[poly], yCurrent[poly], numCoords);
			if (colors[poly] != null){
				g.setColor(colors[poly]);
				g.fillPolygon(xCurrent[poly], yCurrent[poly], numCoords);
			}
		}
		boundingCircle.draw(g);
	}

	/* --------------------
	        Movement
	-------------------- */
	public void moveBy(int dist, int angle){
		x += (dist * Lookup.cos[angle]);
		y += (dist * Lookup.sin[angle]);
	}
	
	public void moveForwardBy(int dist){
		moveBy(dist, angle);
	}
	
	public void moveBackwardBy(int dist){
		x -= (dist * Lookup.cos[angle]);
		y -= (dist * Lookup.sin[angle]);
	}
	
	public void rotateRightBy(int degrees){
		this.angle += degrees;
		
		if (angle >= 360){
			angle -= 360;
		}
		else if (angle < 0){
			angle += 360;
		}
	}
	
	public void rotateLeftBy(int degrees){
		this.angle -= degrees;
		
		if (angle >= 360){
			angle -= 360;
		}
		else if (angle < 0){
			angle += 360;
		}
	}
	
	/* ------------------------------
		   Collision detection
	------------------------------ */
	public boolean hasCollidedWith(PolygonModel target) {
		return boundingCircle.hasCollidedWith(target.boundingCircle);
	}
	
	//	use x = target.xCurrent[i][r] as ray
	
	/* Ray Casting Algorithm
	public boolean hasCollidedWith(PolygonModel target) {
		double m;
		double b;
		double interceptY;
		double xDiff;
		int counter;
		boolean condition;
		
		for (int i = 0; i < target.xCurrent.length; i++) {
			if (target.xCurrent[i].length > 2) {
				for (int r = 0; r < target.xCurrent[i].length; r++) {
					counter = 0;
					
					for (int ii = 0; ii < this.xCurrent.length; ii++) {
						if (this.xCurrent[ii].length > 2) {
							for (int rr = 0; rr < this.xCurrent[ii].length - 1; rr++) {
								xDiff = this.xCurrent[ii][rr] - this.xCurrent[ii][rr + 1];
								if (xDiff != 0) {
									m = (this.yCurrent[ii][rr] - this.yCurrent[ii][rr + 1])/(xDiff);
									b = this.yCurrent[ii][rr] - m * this.xCurrent[ii][rr];
									
									interceptY = m * target.xCurrent[i][r] + b;
									if (this.yCurrent[ii][rr] < this.yCurrent[ii][rr + 1]) {
										condition = (interceptY >= this.yCurrent[ii][rr]) && (interceptY <= this.yCurrent[ii][rr + 1]);
									} else {
										condition = (interceptY <= this.yCurrent[ii][rr]) && (interceptY >= this.yCurrent[ii][rr + 1]);
									}
									
									if (condition && (target.yCurrent[i][r] <= interceptY)) {
										counter += 1;
									}
								} else {
									condition = target.xCurrent[i][r] == this.xCurrent[ii][rr];
									if (condition && (target.yCurrent[i][r] <= this.yCurrent[ii][rr])) {
										counter += 1;
									}
								}
							}
						}
					}
					
					if (counter % 2 == 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	*/
	
//	    (Separating Axis theorem)
//	public boolean hasCollidedWith(PolygonModel tar=get){
//		int a;
//		int b;
//				
//		for (int i = 0; i < 4; i++){
//			
//		}
//	}
	
	 	
//	public double distanceTo(PolygonModel target)
//		double xc = Lookup.cos[angle];
//		double yc = Lookup.sin[angle];
//		 
//		int xu = (int)(target.x - x);
//		int yu = (int)(target.y - y);
//		
//		return xu * yc - xc * yu;
//	}
	
	/* ----------
	      AI
	---------- */
	// is to left  if negative
	// is to right if negative 
	public int angleTo(PolygonModel target){
		double cosA = Lookup.cos[angle];
		double sinA = Lookup.sin[angle];

		return (int)((x - target.x) * sinA - (y - target.y) * cosA) ;
	}
	
	/* ------------------------------
    	Coordinates and color
	------------------------------ */
	public LinkedList<String> getFileContent(String tankFile){
		LinkedList<String> coordsList = new LinkedList<String>();
		
		BufferedReader reader = null;
		String text = null;
	
		try {
		    reader = new BufferedReader(new FileReader(new File(tankFile)));
		    while ((text = reader.readLine()) != null) {
		        coordsList.add(text);
		    }
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) 			{e.printStackTrace();} 
		finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {}
		}
		
		return coordsList;
	}
	
	public int[][] toInt2D(LinkedList<String> list){
		int[][] resultInts = new int[list.size()][];
		for (int i = 0; i < list.size(); i++){
			String str = list.get(i);
			String intStr = "";
			
			LinkedList<Integer> ints = new LinkedList<Integer>();
			
			for (int j = 0; j < str.length(); j++){
				if (str.charAt(j) == ','){
					// insert the number in intStr to ints
					if (intStr.charAt(0) == '-'){
						ints.addLast(-Integer.parseInt(intStr.substring(1)));
					}
					else{
						ints.addLast(Integer.parseInt(intStr));
					}
					intStr = "";
				}
				else {
					// append number to intStr
					intStr = intStr + str.substring(j, j+1);
				}
			}
			ints.addLast(Integer.parseInt(intStr));
			
			// convert linked list to int array
			int[] intsArray = new int[ints.size()];
			for (int j = 0; j < ints.size(); j++){
				intsArray [j] = ints.get(j);
			}
			
			resultInts[i] = intsArray;
		}
		
		return resultInts;
	}
	
	public Color[] toColors(LinkedList<String> list){
		Color[] colors = new Color[list.size()];
		
		for (int i = 0; i < colors.length; i++){
			String str = list.get(i);
			String colorStr = "";
			
			int red   = 0;
			int green = 0;
			int blue  = 0;
	
			for (int j = 0; j < str.length(); j++){
				if (str.charAt(j) == ','){
					if (red == 0){
						red = Integer.parseInt(colorStr);
					}
					else if (green == 0){
						green = Integer.parseInt(colorStr);
					}
					else{
						blue = Integer.parseInt(colorStr);
					}
					colorStr = "";
				}
				else{
					colorStr = colorStr + str.substring(j, j+1);
				}
			}
			
			colors[i] = new Color(red, green, blue);	
		}
		
		return colors;
	}
}
