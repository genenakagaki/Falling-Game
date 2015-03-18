

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener{

	boolean W_pressed  = false;
	boolean S_pressed  = false;
	boolean A_pressed  = false;
	boolean D_pressed  = false;
	boolean LT_pressed = false;
	boolean RT_pressed = false;
	
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W)     W_pressed = true;
		if (code == KeyEvent.VK_S)     S_pressed = true;
		if (code == KeyEvent.VK_A)     A_pressed = true;
		if (code == KeyEvent.VK_D)     D_pressed = true;
		if (code == KeyEvent.VK_LEFT)  LT_pressed = true;
		if (code == KeyEvent.VK_RIGHT) RT_pressed = true;
//		if (code == KeyEvent.VK_SHIFT) tank.setBoost(true);
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W)     W_pressed = false;
		if (code == KeyEvent.VK_S)     S_pressed = false;
		if (code == KeyEvent.VK_A)     A_pressed = false;
		if (code == KeyEvent.VK_D)     D_pressed = false;
		if (code == KeyEvent.VK_LEFT)  LT_pressed = false;
		if (code == KeyEvent.VK_RIGHT) RT_pressed = false;
//		if (code == KeyEvent.VK_SHIFT) tank.setBoost(false);
//		if (code == KeyEvent.VK_SPACE) tank.shoot(this);
	}
}
