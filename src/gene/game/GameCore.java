package gene.game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public abstract class GameCore implements KeyListener,
										  MouseListener,
										  MouseMotionListener,
									  	  MouseWheelListener {
		
	protected static final int FONT_SIZE = 24;
	protected ScreenManager screen;
		
	private static final DisplayMode[] DISPLAY_MODES = {
		new DisplayMode(1280, 800, 32, 0),
		new DisplayMode(1280, 800, 24, 0),
		new DisplayMode(1280, 800, 16, 0),
		new DisplayMode( 800, 600, 32, 0),
		new DisplayMode( 800, 600, 24, 0),
		new DisplayMode( 800, 600, 16, 0)
	};
	private boolean running;
	
	public final void stop() {
		running = false;
	}
	
	public final void run() {
		try {
			initialize();
			gameLoop();
		}
		finally{
			screen.restoreScreen();
		}
	}
	
	private void initialize() {
		screen = new ScreenManager();
		DisplayMode displayMode = screen.findFirstCompatibleMode(DISPLAY_MODES);
		screen.setFullScreen(displayMode);
		
		Window window = screen.getFullScreenWindow();
		window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
		
		running = true;
		
		init();
	}
	
	public abstract void init();
	
	public final Image loadImg(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public abstract void loadImgs();
	
	private final void gameLoop() {
		int fps = 60;
		int frameInterval = 1000/60;
		int sleepTime;
		long startTime = System.currentTimeMillis();
		
		while (running) {
			gameUpdate();
			
			drawScreen();
			
			sleepTime = frameInterval - (int)(startTime - System.currentTimeMillis());
			
			try {
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException ex) {
			}
			
			startTime = System.currentTimeMillis();
		}
	}
	
	public abstract void gameUpdate();
	
	private void drawScreen() {
		Graphics2D g = null;
		g = screen.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		draw(g);
		g.dispose();
		
		screen.update();
	}
	
	public abstract void draw(Graphics2D g);
}
