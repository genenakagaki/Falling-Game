import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFramework{
	
	public static void main(String[] args){
		GameFramework game = new GameFramework();
		game.run();
		
	}
		
	private DisplayMode[] DISPLAY_MODES = {
		new DisplayMode(1280, 800, 32, 0),
		new DisplayMode(1280, 800, 24, 0),
		new DisplayMode(1280, 800, 16, 0),
		new DisplayMode( 800, 600, 32, 0),
		new DisplayMode( 800, 600, 24, 0),
		new DisplayMode( 800, 600, 16, 0)
	};
	
	private ScreenManager screen;
	private Image bgImage;
	
	boolean W_pressed   = false;
	boolean S_pressed   = false;
	boolean A_pressed   = false;
	boolean D_pressed   = false;
	boolean LT_pressed  = false;
	boolean RT_pressed  = false;
	boolean ESC_pressed = false;
	
	boolean running;
	
	Tank tank;
	Tank enemyTank;
	
	public void run(){
		screen = new ScreenManager();
		JFrame frame = new JFrame();
		frame.addKeyListener(new GameKeyListener());
		running = true;
		
		try{
			DisplayMode displayMode = screen.findFirstCompatibleMode(DISPLAY_MODES);
			screen.setFullScreen(displayMode, frame);
			initialize();
			animationLoop();
		}
		finally{
			screen.restoreScreen();
		}
	}
	
	public void initialize(){
		loadImages();
		
		tank = new Tank(40, 40, 0, "tank/norrisTank");
		enemyTank = new Tank(300, 300, 0, "tank/norrisTank");
	}
	
	public void loadImages(){
		bgImage = loadImage("images/bgImage.jpg");
	}
	
	private Image loadImage(String fileName){
		return new ImageIcon(fileName).getImage();
	}
	
	public void animationLoop(){
		
		while (running){
			Graphics2D g = null;
			g = screen.getGraphics();
			gameUpdate();
			draw(g);
			g.dispose();
			screen.update();
			
			try{
				Thread.sleep(20);
			}
			catch (InterruptedException ex) {}
		}
	}
	
	public void gameUpdate(){
		tank.getGun().update();
		enemyTank.getGun().update();
		
//		enemyTank.turnTowards(tank, 1);
		enemyTank.moveTowards(tank, 1, 1);
//		enemyTank.pointGunAt(tank, 1);
		enemyTank.shootAt(tank, 1);
		
		if (W_pressed) tank.moveForwardBy(5);
		if (S_pressed) tank.moveBackwardBy(3);
		if (A_pressed) tank.rotateLeftBy(3);
		if (D_pressed) tank.rotateRightBy(3);
		
		if (LT_pressed) tank.getGun().rotateLeftBy(2);
		if (RT_pressed) tank.getGun().rotateRightBy(2);
		
		if (ESC_pressed) running = false;
	}
	
	public void draw(Graphics g){
		g.drawImage(bgImage, 0, 0, null);
		
		tank.draw(g);
		enemyTank.draw(g);
	}

	public class GameKeyListener implements KeyListener{
		public void keyTyped(KeyEvent e) {}
	
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			
			if (code == KeyEvent.VK_W)      W_pressed  = true;
			if (code == KeyEvent.VK_S)      S_pressed  = true;
			if (code == KeyEvent.VK_A)      A_pressed  = true;
			if (code == KeyEvent.VK_D)      D_pressed  = true;
			if (code == KeyEvent.VK_LEFT)   LT_pressed = true;
			if (code == KeyEvent.VK_RIGHT)  RT_pressed = true;
			if (code == KeyEvent.VK_SHIFT)  tank.setBoost(true);
			
			if (code == KeyEvent.VK_ESCAPE) ESC_pressed = true;
		}
	
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			
			if (code == KeyEvent.VK_W)     W_pressed = false;
			if (code == KeyEvent.VK_S)     S_pressed = false;
			if (code == KeyEvent.VK_A)     A_pressed = false;
			if (code == KeyEvent.VK_D)     D_pressed = false;
			if (code == KeyEvent.VK_LEFT)  LT_pressed = false;
			if (code == KeyEvent.VK_RIGHT) RT_pressed = false;
			if (code == KeyEvent.VK_SHIFT) tank.setBoost(false);
			if (code == KeyEvent.VK_SPACE) tank.shoot();
		}
	}
}
