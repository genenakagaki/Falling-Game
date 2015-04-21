package gene.tennis;

import gene.input.*;
import gene.game.*;

import java.awt.*;
import java.awt.event.*;
import java.text.AttributedCharacterIterator;
import java.util.Iterator;
import java.util.LinkedList;

public class Tennis extends GameCore {
	
	public static void main(String[] args) {
		Tennis game = new Tennis();
		game.run();
	}
	
	private boolean logging;
	private LinkedList<String> logs = new LinkedList<String>();
	
	private void log(String message) {
		logs.add(message);
		if (logs.size() > 10) {
			logs.remove(0);
		}
	}
	
	private InputManager inputManager;

	private boolean paused;
	
	private Camera camera;
	
	public void init() {
		Window window = screen.getFullScreenWindow();
		inputManager = new InputManager(window);
		
		// allow input of the TAB key other keys normally used for focus traversal
		window.setFocusTraversalKeysEnabled(false);
		
		loadImgs();
		createGameActions();
		
		paused = false;
		
		camera = new Camera(0, 0, 0, 100, window.getWidth());
		
		toggleDisplayFPS();
		logging = false;
	}

	private Image bgImage;

	public void loadImgs() {
//		bgImage = loadImg("images/bgImage.jpg");
	}
	
	private GameAction exit;
	private GameAction pause; 
	
	private GameAction forward;
	private GameAction backward;
	private GameAction mainLeft;
	private GameAction mainRight;
	private GameAction subLeft;
	private GameAction subRight;
	private GameAction shoot;
	
	public void createGameActions() {
		exit  = new GameAction();
		pause = new GameAction(GameAction.ON_RELEASE);
		
		inputManager.mapToKey( exit, KeyEvent.VK_ESCAPE);
		inputManager.mapToKey(pause, KeyEvent.VK_P);
		
		forward   = new GameAction();
		backward  = new GameAction();
		mainLeft  = new GameAction();
		mainRight = new GameAction();
		subLeft   = new GameAction();
		subRight  = new GameAction();
		
		inputManager.mapToKey(  forward, KeyEvent.VK_W);
		inputManager.mapToKey( backward, KeyEvent.VK_S);
		inputManager.mapToKey( mainLeft, KeyEvent.VK_A);
		inputManager.mapToKey(mainRight, KeyEvent.VK_D);
		inputManager.mapToKey(  subLeft, KeyEvent.VK_LEFT);
		inputManager.mapToKey( subRight, KeyEvent.VK_RIGHT);
		
		shoot = new GameAction();
		
		inputManager.mapToKey(shoot, KeyEvent.VK_SPACE);
	}
	
	public void togglePause() {
		if (paused) {
			paused = false;
			pause.reset();
		}
		else {
			paused = true;
			inputManager.resetAllGameActions();
			log("paused");
		}
	}
	
	public void gameUpdate() {
		checkSystemInput();
		
		if (!paused) {
			collisionResponse();
			
			checkGameInput();
		}
	}
	
	private void checkSystemInput() {
		if (exit.isExecuting())  stop();
		if (pause.isExecuting()) togglePause();
	}
	
	private void checkGameInput() {
	}
	
	private void collisionResponse() {
	}
	
	public void draw(Graphics2D g) {
//		g.drawImage(bgImage, 0, 0, null);
		
		if (logging) {
			g.setColor(Color.black);
			
			g.setRenderingHint(
					RenderingHints.KEY_TEXT_ANTIALIASING, 
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			
			int yPos = 0;
			for (Iterator<String> iterator = logs.iterator(); iterator.hasNext();) {
				yPos += 10;
				g.drawString(iterator.next(), 10, yPos);
			}
		}
		
		displayInfo(g);
	}
	
	//--------------------------------------------------------------------------------//
	//    user input
	//--------------------------------------------------------------------------------//
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
}
