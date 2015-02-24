import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.Time;

public class GameFramework extends Applet implements Runnable, KeyListener{
	
	Thread t = new Thread(this);

	boolean pressedUP = false;
	boolean pressedDN = false;
	boolean pressedLT = false;
	boolean pressedRT = false;
	
	boolean A_pressed = false;
	boolean D_pressed = false;
	
	AARect r1 = new AARect(10, 10, 100, 260);
	Tank tank = new Tank(100, 100, 90);
	
	public void init(){
		this.resize(800, 800);
		addKeyListener(this);
		
		requestFocus();
		
		// Create thread and run
		t.start();
	}
	
	public void run() {
		long startTime = System.currentTimeMillis();
		long timeDiff;
		
		while(true){
			if (pressedUP) tank.moveForwardBy(1);
			if (pressedDN) tank.moveBackwardBy(2);
			if (pressedLT) tank.rotateLeftBy(2);
			if (pressedRT) tank.rotateRightBy(2);
			
			if (A_pressed) tank.rotateGunLeftBy(2);
			if (D_pressed) tank.rotateGunRightBy(2);
			
			
			
			repaint();
			
			timeDiff = System.currentTimeMillis() - startTime;
						
			try{
				t.sleep(16);
			}catch(Exception e){}
			
			startTime = System.currentTimeMillis();
		}
	}
	
	public void paint(Graphics g){
		tank.draw(g);
	}

	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_UP)    pressedUP = true;
		if (code == e.VK_DOWN)  pressedDN = true;
		if (code == e.VK_LEFT)  pressedLT = true;
		if (code == e.VK_RIGHT) pressedRT = true;
		if (code == e.VK_A)     A_pressed = true;
		if (code == e.VK_D)     D_pressed = true;
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_UP)    pressedUP = false;
		if (code == e.VK_DOWN)  pressedDN = false;
		if (code == e.VK_LEFT)  pressedLT = false;
		if (code == e.VK_RIGHT) pressedRT = false;
		if (code == e.VK_A)     A_pressed = false;
		if (code == e.VK_D)     D_pressed = false;
	}	
}
