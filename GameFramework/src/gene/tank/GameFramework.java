package gene.tank;

import gene.input.*;

import java.awt.*;
import java.awt.event.*;
import java.text.AttributedCharacterIterator;
import java.util.Iterator;
import java.util.LinkedList;

public class GameFramework extends GameCore implements MouseListener, 
													   MouseMotionListener, 
													   MouseWheelListener{
	
	public static void main(String[] args){
		GameFramework game = new GameFramework();
		game.run();
	}

	private InputManager inputManager;
		
	private Tank tank;
	private Tank enemyTank;
	
	private boolean paused;
	
	private boolean logging;
	private LinkedList<String> logs = new LinkedList<String>();
	
	private void log(String str) {
		logs.add(str);
		if (logs.size() > 10){
			logs.remove();
		}
	}
	
	public void init(){
		super.init();
		Window window = screen.getFullScreenWindow();
		inputManager = new InputManager(window);
		
		// allow input of the TAB key other keys normally used for focus traversal
		window.setFocusTraversalKeysEnabled(false);
		
		loadImages();
		
		createGameActions();
		
		tank = new Tank(100, 100, 0, "tank/norrisTank");
		enemyTank = new Tank(300, 300, 0, "tank/norrisTank");
		
		paused = false;
		logging = true;
	}

	private Image bgImage;

	public void loadImages() {
		bgImage = loadImage("images/bgImage.jpg");
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
	
	public void togglePause(){
		if (paused) {
			paused = false;
			pause.reset();
		}
		else {
			paused = true;
			inputManager.resetAllGameActions();
		}
	}
	
	public void gameUpdate(){
		checkSystemInput();
		
		if (!paused){
			tank.update();
			enemyTank.update();
			
	//		enemyTank.turnTowards(tank, 1);
			enemyTank.moveTowards(tank, 1, 1);
	//		enemyTank.pointGunAt(tank, 1);
			enemyTank.shootAt(tank, 1);
			
			if (tank.hasCollidedWith(enemyTank)){
				tank.moveBackwardBy(30);
			}
			
			checkGameInput();
		}
	}
	
	private void checkSystemInput() {
		if (exit.isExecuting())  stop();
		if (pause.isExecuting()) togglePause();
	}
	
	private void checkGameInput() {
		if (forward.isExecuting())   tank.moveForwardBy(10);
		if (backward.isExecuting())  tank.moveBackwardBy(6);
		if (mainLeft.isExecuting())  tank.rotateLeftBy  (6);
		if (mainRight.isExecuting()) tank.rotateRightBy (6);
		
		if (subLeft.isExecuting())  tank.getGun().rotateLeftBy(2);
		if (subRight.isExecuting()) tank.getGun().rotateRightBy(2);
		
		if (shoot.isExecuting()) tank.shoot();
	}
	
	public void draw(Graphics2D g){
		g.drawImage(bgImage, 0, 0, null);
		
		tank.draw(g);
		enemyTank.draw(g);
		
		if (logging) {
			g.setColor(Color.black);
			
			int yPos = 0;
			for (Iterator<String> iterator = logs.iterator(); iterator.hasNext();) {
				yPos += 10;
				g.drawString(iterator.next(), 10, yPos);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {}
}
