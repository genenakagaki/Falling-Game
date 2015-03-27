package gene.input;

import java.awt.*;
import java.awt.event.*;

public class InputManager implements KeyListener
//									 MouseListener, 
//									 MouseMotionListener, 
//									 MouseWheelListener 
									 {
	private Component comp;
	
	public InputManager(Component comp) {
		this.comp = comp;
		
		comp.addKeyListener(this);
//		comp.addMouseListener(this);
//		comp.addMouseMotionListener(this);
//		comp.addMouseWheelListener(this);
		
		// allow input of the TAB key other keys normally used for focus traversal
		comp.setFocusTraversalKeysEnabled(false);
	}
	
	private final int NUM_KEY_CODES   = 600;
//	private final int NUM_MOUSE_CODES =   9;

	private GameAction[] keyActions   = new GameAction[NUM_KEY_CODES];	
//	private GameAction[] mouseActions = new GameAction[NUM_MOUSE_CODES];
	
	public void mapToKey(GameAction gameAction, int keyCode) {
		keyActions[keyCode] = gameAction;
	}
	
//	public void mapToMouse(GameAction gameAction, int mouseCode) {
//		mouseActions[mouseCode] = gameAction;
//	}
	
	public void resetAllGameActions() {
		for (int i = 0; i < keyActions.length; i++){
			if (keyActions[i] != null){
				keyActions[i].reset();
			}
		}
	}
	
	private GameAction getKeyAction(KeyEvent e){
		int keyCode = e.getKeyCode();
		
		if (keyCode < keyActions.length) {
			return keyActions[keyCode];
		}
		else{
			return null;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		GameAction gameAction = getKeyAction(e);
		
		if (gameAction != null){
			gameAction.press();
		}
		e.consume();
	}

	public void keyReleased(KeyEvent e) {
		GameAction gameAction = getKeyAction(e);
		
		if (gameAction != null){
			gameAction.release();
		}
		e.consume();
	}

//	public void mouseClicked(MouseEvent e) {}
//	public void mousePressed(MouseEvent e) {}
//	public void mouseReleased(MouseEvent e) {}
//	public void mouseEntered(MouseEvent e) {}
//	public void mouseExited(MouseEvent e) {}
//	public void mouseDragged(MouseEvent e) {}
//	public void mouseMoved(MouseEvent e) {}
//	public void mouseWheelMoved(MouseWheelEvent e) {}
}
