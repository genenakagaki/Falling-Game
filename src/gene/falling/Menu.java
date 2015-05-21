package gene.falling;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gene.math.AARect;

public class Menu extends AARect {
	
	public AARect start;
	public AARect quit;
	
	private String titleStr;
	private String buttonStr;
	
	private Font font = new Font("Arial", Font.PLAIN, 20);
	
	public Menu(String titleStr, String buttonStr) {
		super(FallingGame.getWidth()/2 - 100, FallingGame.getHeight()/2 - 150, 200, 300);
		
		this.titleStr = titleStr;
		this.buttonStr = buttonStr;
		
		start = new AARect(x + 40, y + 80, 120, 40);
		quit = new AARect(x + 40, y + 160, 120, 40);
	}
	
	public void draw(Graphics g) {
		g.setFont(font);
		
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.WHITE);
		g.fillRect(start.x, start.y, start.w, start.h);		
		g.fillRect(quit.x, quit.y, quit.w, quit.h);
		
		g.setColor(Color.BLUE);
		g.drawString(titleStr, x + 20, y + 40);
		g.drawString(buttonStr, start.x + 20, start.y + 25);
		g.drawString("Quit", quit.x + 20, quit.y + 25);
	}	
	
	public void setTitle(String titleStr) {
		this.titleStr = titleStr;
	}
	
	public void setButton(String startStr) {
		this.buttonStr = startStr;
	}
}