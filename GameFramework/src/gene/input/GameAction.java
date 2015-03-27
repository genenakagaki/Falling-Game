package gene.input;

public class GameAction {
	
	public static final int WHILE_PRESSED = 0;
	public static final int    ON_RELEASE = 1;
	
	private boolean executing;
	
	private int type;
	
	public GameAction() {
		type = WHILE_PRESSED;
	}
	
	public GameAction(int type) {
		this.type = type;
	}
	
	public synchronized void press() {
		if (type == WHILE_PRESSED) {
			executing = true;
		}
	}
	
	public synchronized void release() {
		if (type == WHILE_PRESSED) {
			executing = false;
		}
		if (type == ON_RELEASE) {
			if (executing) {
				executing = false;
			}
			else {
				executing = true;
			}
		}
	}
	
	public boolean isExecuting() {
		return executing;
	}
	
	public void reset() {
		executing = false;
	}
}
