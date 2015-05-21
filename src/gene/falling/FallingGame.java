package gene.falling;

import gene.game.*;
import gene.math.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;

public class FallingGame extends GameCore {
	
	public static void main(String[] args) {
		new FallingGame().run();
	}
	
	public static int width;
	public static int height;
	
	private boolean paused   = true;
	private boolean gameOver = false;
	
	private static Camera camera;
	
	private Player player;
	private Layer[] layers = new Layer[30];
	
	private int score;
	
	private Menu menu;
	
	// mouse input
	private int xMouse;
	private int yMouse;
	// system input
	private boolean input_exit;
	private boolean input_pause;
	// game input
	private boolean input_up;
	private boolean input_down;
	private boolean input_left;
	private boolean input_right;
	private boolean input_topLeft;
	private boolean input_topRight;
	private boolean input_shift;
	
	public void init() {
		Window window = screen.getFullScreenWindow();
		
		// allow input of the TAB key other keys normally used for focus traversal
		window.setFocusTraversalKeysEnabled(false);
		window.addKeyListener(this);
		window.addMouseListener(this);
		
		width  = screen.getWidth();
		height = screen.getHeight();
		
		loadImgs();
		camera = new Camera();
		camera.x = 0;
		camera.y = 0;
		camera.z = 0;
		
		player = new Player(0, 0, 100);
		player.update();
		for (int i = 0; i < layers.length; i++) {
			layers[i] = new Layer();
		}
		
		score = -1;
		
		menu = new Menu("", "Start");
	}
	
	private Image bgImage;

	public void loadImgs() {
		bgImage = loadImg("images/bgImage.jpg");
	}
	
	public void pause() {
		paused = !paused;
		menu.setTitle("Paused");
		menu.setButton("Resume");
		input_pause = false;
	}
	
	// --------------------------------------------------------------------------------
	//   Game update
	// --------------------------------------------------------------------------------
	private int count = 200;
	private int layerIndex = 0;
	private int layerSpeed = 4;
	
	public void gameUpdate() {
		checkSystemInput();
				
		if (!paused && !gameOver) {
			checkGameInput();
			
			player.update();
			
			if (count == 200) {
				if (layerIndex < 30){
					layers[layerIndex].generateHole();
					layers[layerIndex].generateWall();
					layers[layerIndex].showLayer();
					layers[layerIndex].update();
					
					layerIndex++;
					count = 0;
				}
				score++;
			}
			
			count++;

			for (int i = 0; i < layers.length; i++) {
				if (layers[i].isOnScreen()) {
					layers[i].update();
					layers[i].moveOutBy(layerSpeed);		
				}
			}
			
			if (score >= 10) {
				layerSpeed ++;
			}
			
			if (score >= 20) {
				layerSpeed ++;
			}
			
			collisionResponse();
		}
		
		if (gameOver) {
			player.moveOutBy(4);
			player.update();
			for (int i = 0; i < layers.length; i++) {
				if (layers[i].isOnScreen()) {
					layers[i].update();
					layers[i].moveOutBy(layerSpeed);		
				}
			}
		}
	}
	
	private void checkSystemInput() {
		if (input_exit)  stop();
		if (input_pause) pause();
	}
	
	private void checkGameInput() {
		int moveSpeed = 6;
		if (input_shift) {
			moveSpeed = 10;
		}
		if (input_left) {
			camera.moveLeftBy(moveSpeed);
			player.moveLeftBy(moveSpeed);
		}
		if (input_right) {
			camera.moveRightBy(moveSpeed);
			player.moveRightBy(moveSpeed);
		}
		if (input_up) {
			camera.moveUpBy(moveSpeed);
			player.moveDownBy(moveSpeed);
		}
		if (input_down) {
			camera.moveDownBy(moveSpeed);
			player.moveUpBy(moveSpeed);
		}
		if (input_topLeft) {
			player.rotateLeftBy(moveSpeed - 3);
		}
		if (input_topRight) {
			player.rotateRightBy(moveSpeed - 3);
		}
	}
	
	private void collisionResponse() {
		if (player.hasCollidedWith(layers)) {
			gameOver = true;
			menu.setTitle("Game Over");
			menu.setButton("Restart");
		}
	}
	
	// --------------------------------------------------------------------------------
	//   Draw
	// --------------------------------------------------------------------------------
	
	public void draw(Graphics2D g) {
		for (int i = layers.length-1; i >= 0; i--) {
			if (layers[i].isOnScreen()) {
				layers[i].draw(g);
			}
		}
		
		g.setColor(Color.GREEN);
		g.drawString("Score: "+ score , 10, 30);
		
		player.draw(g);
		
		
		if (paused) { 
			menu.draw(g);
		}
		
		if (gameOver) {
			menu.draw(g);
		}
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	//---------------------------------------------------------------------------------
	//    user input
	//---------------------------------------------------------------------------------
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
		if (menu.start.contains(e.getX(), e.getY())) {
			if (gameOver) {
				gameOver = false;
				init();				
			}
			else {
				pause();
			}
		}
		if (menu.quit.contains(e.getX(), e.getY())) {
			stop();
		}
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
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_ESCAPE) input_exit  = true;
		if (code == KeyEvent.VK_P)      input_pause = true;
		
		if (code == KeyEvent.VK_W) input_up    = true;
		if (code == KeyEvent.VK_S) input_down  = true;
		if (code == KeyEvent.VK_A) input_left  = true;
		if (code == KeyEvent.VK_D) input_right = true;
		if (code == KeyEvent.VK_LEFT) input_topLeft  = true;
		if (code == KeyEvent.VK_RIGHT) input_topRight = true;
		if (code == KeyEvent.VK_SHIFT) input_shift = true;
	}
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_ESCAPE) input_exit = false;
		if (code == KeyEvent.VK_P) input_pause = false;
		
		if (code == KeyEvent.VK_W) input_up    = false;
		if (code == KeyEvent.VK_S) input_down  = false;
		if (code == KeyEvent.VK_A) input_left  = false;
		if (code == KeyEvent.VK_D) input_right = false;	
		if (code == KeyEvent.VK_LEFT) input_topLeft  = false;
		if (code == KeyEvent.VK_RIGHT) input_topRight = false;
		if (code == KeyEvent.VK_SHIFT) input_shift = false;
	}
}
