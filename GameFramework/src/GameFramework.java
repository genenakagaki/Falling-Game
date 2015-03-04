import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.Time;

public class GameFramework extends Applet implements Runnable, KeyListener{
	
	Thread t = new Thread(this);

	boolean W_pressed = false;
	boolean S_pressed = false;
	boolean A_pressed = false;
	boolean D_pressed = false;
	
	boolean LT_pressed = false;
	boolean RT_pressed = false;
			
	Tank tank = new Tank(100, 100, 90);
	
	Line line = new Line(100, 100, 900, 600);
	Circle c = new Circle(500, 200, 30);
	BadCircle bc = new BadCircle(100, 600, 30);
	
	Audio audio;
	
	public void init(){
		this.resize(800, 800);
		addKeyListener(this);
		
		audio = new Audio(this);
		
		requestFocus();
		
		// Create thread and run
		t.start();
	}
	
	public void run() {
		long startTime = System.currentTimeMillis();
		long timeDiff;
		
		while(true){
			/*
			if (W_pressed) tank.moveForwardBy(4);
			if (S_pressed) tank.moveBackwardBy(2);
			if (A_pressed) tank.rotateLeftBy(1);
			if (D_pressed) tank.rotateRightBy(1);
			*/
			
			if (LT_pressed) tank.gun.rotateLeftBy(2);
			if (RT_pressed) tank.gun.rotateRightBy(2);
			
			//Collision Detection
			if (W_pressed) c.moveForwardBy(5);
			if (S_pressed) c.moveBackwardBy(5);
			if (A_pressed) c.rotateLeftBy(2);
			if (D_pressed) c.rotateRightBy(2);
			
			bc.turnTowards(c, 5);
			bc.moveTowards(c);
			
			if (c.hasCollidedWith(line)){
				double d = line.distanceTo(c.x, c.y);
				int    r = c.r;
				
				double dist = r - d + 5;
				
				c.moveBy((int)(dist * line.xn), (int)(dist * line.yn));
			}
			
			tank.gun.update();
			repaint();
			
			timeDiff = System.currentTimeMillis() - startTime;
						
			try{
				t.sleep(16);
			}catch(Exception e){}
			
			startTime = System.currentTimeMillis();
		}
	}
	
	public void paint(Graphics g){
		//tank.draw(g);
		
		//Collision Detection
		
		line.draw(g);
		c.draw(g);
		bc.draw(g);
		
		double d = line.distanceTo(c.x, c.y);
	}

	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_W)     W_pressed = true;
		if (code == e.VK_S)     S_pressed = true;
		if (code == e.VK_A)     A_pressed = true;
		if (code == e.VK_D)     D_pressed = true;
		if (code == e.VK_LEFT)  LT_pressed = true;
		if (code == e.VK_RIGHT) RT_pressed = true;
		if (code == e.VK_SHIFT) tank.boost = true;
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_W)     W_pressed = false;
		if (code == e.VK_S)     S_pressed = false;
		if (code == e.VK_A)     A_pressed = false;
		if (code == e.VK_D)     D_pressed = false;
		if (code == e.VK_LEFT)  LT_pressed = false;
		if (code == e.VK_RIGHT) RT_pressed = false;
		if (code == e.VK_SHIFT) tank.boost = false;
		if (code == e.VK_SPACE) tank.shoot(this);
	}	
}
