package gene.game;

import gene.graphics.ScreenManager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public abstract class GameCore  implements KeyListener,
										   MouseListener,
										   MouseMotionListener,
									  	   MouseWheelListener {
	
	private boolean D = Lookup.DEBUG;
	
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
			System.exit(0);
		}
	}
	
	private boolean displayingInfo;
	private boolean displayingFPS;
	
	public void toggleDisplayInfo() {
		displayingInfo = !displayingInfo;
	}
	public void toggleDisplayFPS() {
		displayingFPS = !displayingFPS;
	}
	
	private void initialize() {
		screen = new ScreenManager();
		DisplayMode displayMode = screen.findFirstCompatibleMode(DISPLAY_MODES);
		screen.setFullScreen(displayMode);
		
		Window window = screen.getFullScreenWindow();
		window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
//		window.setBackground(Color.blue);
//		window.setForeground(Color.white);
		
		running = true;
		
		displayingInfo = false;
		displayingFPS  = false;
		
		init();
	}
	
	public abstract void init();
	
	public final Image loadImg(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public abstract void loadImgs();
	
	private int fps;
	
	private final void gameLoop() {
		final int MAX_FPS = 58;
		fps = MAX_FPS;
		
		final int UPDATE_INTERVAL = 1000/MAX_FPS;
		int renderInterval = UPDATE_INTERVAL;

		long startTime = System.currentTimeMillis();
		long lastRenderTime = System.currentTimeMillis();
		long sleepTime;
		
		long timeLost;
		
		while (running) {
			gameUpdate();
			
			if (System.currentTimeMillis() - lastRenderTime > renderInterval) {
				drawScreen();
				screen.update();
			}
			
			sleepTime = UPDATE_INTERVAL - (int)(startTime - System.currentTimeMillis());
			
			if (sleepTime <= 0) {
				System.out.println("unko");
				renderInterval ++;
				fps = 1000/renderInterval;
				timeLost = Math.abs(sleepTime);
			}
			else {
				timeLost = 0;
				
				try {
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException ex) {
				}
			}
			
			startTime = System.currentTimeMillis() + timeLost;
		}
	}
	
	public abstract void gameUpdate();
	
	private void drawScreen() {
		Graphics2D g = null;
		g = screen.getGraphics();
		draw(g);
		g.dispose();
	}
	
	public abstract void draw(Graphics2D g);
	
	public void displayInfo(Graphics2D g) {
		if (displayingInfo) {
			g.setColor(Color.black);
			g.drawString("FPS: "+ fps, 10, 10);
		}
		else if (displayingFPS) {
			g.setColor(Color.black);
			g.drawString("FPS: "+ fps, 10, 10);
		}
	}
}
