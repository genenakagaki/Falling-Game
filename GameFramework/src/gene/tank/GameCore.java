package gene.tank;

import gene.graphics.ScreenManager;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class GameCore{
	
	protected static final int FONT_SIZE = 24;
		
	private static final DisplayMode[] DISPLAY_MODES = {
		new DisplayMode(1280, 800, 32, 0),
		new DisplayMode(1280, 800, 24, 0),
		new DisplayMode(1280, 800, 16, 0),
		new DisplayMode( 800, 600, 32, 0),
		new DisplayMode( 800, 600, 24, 0),
		new DisplayMode( 800, 600, 16, 0)
	};
	
	protected ScreenManager screen;
	private boolean running;
	
	public void stop(){
		running = false;
	}
	
	public void run(){
		try{
			init();
			gameLoop();
		}
		finally{
			screen.restoreScreen();
		}
	}
	
	public void init(){
		screen = new ScreenManager();
		DisplayMode displayMode = screen.findFirstCompatibleMode(DISPLAY_MODES);
		screen.setFullScreen(displayMode);
		
		Window window = screen.getFullScreenWindow();
		window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
//		window.setBackground(Color.blue);
//		window.setForeground(Color.white);
		
		running = true;
	}
	
	public Image loadImage(String fileName){
		return new ImageIcon(fileName).getImage();
	}
	
	final int FPS = 60;
	final int FRAME_TIME = 1000/FPS;
	
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		int sleepTime;
		
		while (running){
			gameUpdate();
			drawScreen();
			screen.update();
			
			sleepTime = FRAME_TIME - (int)(startTime - System.currentTimeMillis());
			
			try{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException ex) {
			}
			
			startTime = System.currentTimeMillis();
		}
	}
	
	public void gameUpdate(){
	}
	
	private void drawScreen(){
		Graphics2D g = null;
		g = screen.getGraphics();
		draw(g);
		g.dispose();
	}
	
	public abstract void draw(Graphics2D g);
}
