package vu.edl.flashcard;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class FlashCardThread extends Thread {
	
	// Used for debugging
	private final String TAG = this.getClass().getSimpleName();
	// Indicates thread status
	private boolean running;
	// SurfaceHolder for the FlashCardPanel to be displayed on screen
	private SurfaceHolder surface;
	private FlashCardPanel fcPanel;
	
	public FlashCardThread(SurfaceHolder holder, FlashCardPanel panel) {
		super();
		surface = holder;
		fcPanel = panel;
	}
	
	public void setRunning(boolean status) {
		running = status;
	}
	
	@Override
	public void run() {
		Canvas canvas = null;
		running = true;
		while(running) {
			try {
				if(surface != null) {
					canvas = surface.lockCanvas();
					synchronized(surface) {
						if(canvas != null) {
							fcPanel.render(canvas);
						}
					}
					Thread.sleep(15);
				}
			} 
			catch (InterruptedException e) {
				Log.d(TAG, "Thread was unable to sleep");
			} 
			finally {
				if(canvas != null) {
					surface.unlockCanvasAndPost(canvas);
				}
			}	
		}
	}
}
