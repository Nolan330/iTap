package vu.edl.flashcard;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class MessageScreen {
	
	private MessageMap msgMap;
	private Bitmap background;
	
	private MediaPlayer sounds;
	private boolean soundComplete = false;
	private boolean newDisplay = true;
	
	public MessageScreen(MessageMap messageMap, MediaPlayer player, Bitmap bck) {
		msgMap = messageMap;
		sounds = player;
		sounds.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
		    public void onCompletion(MediaPlayer mp) {
		        soundComplete = true;
		    }
		});
		background = bck;
	}
	
	public void displayMessage(Canvas canvas, FlashCardPanel fcPanel) {
		if(newDisplay) {
			initDisplay(canvas, fcPanel);
		}
		
		canvas.drawBitmap(background, 
						  canvas.getWidth()/2 - background.getWidth()/2,
						  canvas.getHeight()/2 - background.getHeight()/2, null);
		
		msgMap.draw(canvas);
		
		if(soundComplete) {
			advance(fcPanel);
		}
	}
	
	private void advance(FlashCardPanel fcPanel) {
		resetDisplay();
		fcPanel.advance();
	}
	
	private void initDisplay(Canvas canvas, FlashCardPanel fcPanel) {
		msgMap.playSound(sounds, fcPanel.getContext());
		msgMap.setCoords(canvas.getWidth()/2, canvas.getHeight()/2);
		newDisplay = false;
	}
	
	private void resetDisplay() {
		newDisplay = true;
		soundComplete = false;
	}
}
