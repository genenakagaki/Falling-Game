package gene.graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/* ScreenManager
 * Initializing and displaying full screen graphics modes.
 * Double buffering and page flipping.
 */

public class ScreenManager{

	private GraphicsDevice gDevice;
	
	public ScreenManager(){
		GraphicsEnvironment gEnviron = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gDevice = gEnviron.getDefaultScreenDevice();
	}
	
	public DisplayMode[] getCompatibleDisplayModes(){
		return gDevice.getDisplayModes();
	}
	
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
		DisplayMode goodModes[] = gDevice.getDisplayModes();
		
		for (int i = 0; i < modes.length; i++){
			for (int j = 0; j < goodModes.length; j++){
				if (displayModesMatch(modes[i], goodModes[j])){
					return modes[i];
				}
			}
		}
		
		return null;
	}
	
	public DisplayMode getCurrentDisplayMode() {
		return gDevice.getDisplayMode();
	}
	
	public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2){
		if (mode1.getWidth() != mode2.getWidth()
				|| mode1.getHeight() != mode2.getHeight()){
			
			return false;
		}
		
		if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& mode1.getBitDepth() != mode2.getBitDepth()){
			
			return false;
		}
		
		if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& mode1.getRefreshRate() != mode2.getRefreshRate()){
			
			return false;
		}
		
		return true;
	}
	
	public void setFullScreen(DisplayMode displayMode){
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		
		gDevice.setFullScreenWindow(frame);
		
		if (displayMode != null && gDevice.isDisplayChangeSupported()){
			try {
				gDevice.setDisplayMode(displayMode);
			}
			catch (IllegalArgumentException ex) {}
		}
		
		frame.createBufferStrategy(2);
	}
	
	public Graphics2D getGraphics(){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			BufferStrategy strategy = window.getBufferStrategy();
			return (Graphics2D)strategy.getDrawGraphics();
		}
		
		return null;
	}
	
	public void update(){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			BufferStrategy strategy = window.getBufferStrategy();
			if (!strategy.contentsLost()){
				strategy.show();
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	public Window getFullScreenWindow(){
		return gDevice.getFullScreenWindow();
	}
	
	public int getWidth(){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			return window.getWidth();
		}
		
		return 0;
	}
	
	public int getHeight(){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			return window.getHeight();
		}
		
		return 0;
	}

	public void restoreScreen(){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			window.dispose();
		}
		
		gDevice.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibleImage(int w, int h, int transparency){
		Window window = gDevice.getFullScreenWindow();
		
		if (window != null){
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, transparency);
		}
		
		return null;
	}
	
}
