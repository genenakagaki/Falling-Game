import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.Time;

import javax.swing.*;

public class GameFramework extends Applet implements Runnable, KeyListener{
	
	//Double buffer
	Image dbi;
	Graphics dbg;
	
	Thread t = new Thread(this);

	boolean W_pressed = false;
	boolean S_pressed = false;
	boolean A_pressed = false;
	boolean D_pressed = false;
	
	boolean LT_pressed = false;
	boolean RT_pressed = false;
	
	boolean paused = false;
	
	double zoom = 1;
			
//	AARect r1 = new AARect(10, 10, 100, 260);
	Tank tank = new Tank(100, 100, 90);
	
	Audio audio;
	
	public void init(){
		this.resize(800, 800);
		this.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				if (notches > 0) {
					if (zoom > 0.5) {
						zoom = zoom * 0.9;
					}
				} else {
					if (zoom < 2) {
						zoom = zoom * 1.1;
					}
				}
			}
			
		});
		
		requestFocus();
		
		// Create thread and run
		t.start();
		
		audio = new Audio(this);
		addKeyListener(this);
	}
	
	public void run() {
		System.out.println("hi"+ paused);
		long startTime = System.currentTimeMillis();
		long timeDiff;
		
		while(true){
			if (!paused) {
				if (W_pressed) tank.moveForwardBy(2);
				if (S_pressed) tank.moveBackwardBy(1);
				if (A_pressed) tank.rotateLeftBy(1);
				if (D_pressed) tank.rotateRightBy(1);
				
				if (LT_pressed) tank.gun.rotateLeftBy(2);
				if (RT_pressed) tank.gun.rotateRightBy(2);
					
			
				tank.gun.update();
				repaint();
			}
			
			timeDiff = System.currentTimeMillis() - startTime;
						
			try{
				t.sleep(16);
			}catch(Exception e){}
			
			startTime = System.currentTimeMillis();
		}
	}
	
	public void update(Graphics g){
		if (dbi == null) {
			dbi = createImage(this.getSize().width, this.getSize().height);
			dbg = dbi.getGraphics();
		}
		
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		dbg.setColor(getForeground());
		paint(dbg);
		
		g.drawImage(dbi, 0, 0, this);
	}

	public void paint(Graphics g){
		tank.draw(g, zoom);
	}
	
	public void pause() {
		if (paused) {
			paused = false;
		} else if (!paused) {
			paused = true;
		}
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
		if (code == e.VK_P)		pause();
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
