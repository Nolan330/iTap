package vu.edl.flashcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MotionEvent;

public class MediaMap extends Map {
	
	// Used for debugging
	//private final String TAG = this.getClass().getSimpleName();
	
	private Uri introId;
	private Uri interactionId;
	private Uri testId;
	private Uri congratsId;
	private boolean playedIntro = false;
	private boolean playedCongrats = false;
	static final int INTRODUCTION = 0;
	static final int INTERACTION = 1;
	static final int TESTING = 2;
	static final int CONGRATS = 3;
			
	private Bitmap img;
	
	private boolean tapped = false;
	private boolean correct = false;
	
	public MediaMap(Bitmap image, Uri introPath, Uri interactionPath, 
					Uri testPath, Uri congratsPath) {
		img = image;
		img = img.copy(Bitmap.Config.ARGB_8888, true);
		introId = introPath;
		interactionId = interactionPath;
		testId = testPath;
		congratsId = congratsPath;
	}
	
	public Bitmap getImage() {
		return img;
	}
	
	public int getWidth() {
		return img.getWidth();
	}
	
	public int getHeight() {
		return img.getHeight();
	}
	
	public void setTransparent() {
		for(int i = 0; i < img.getWidth(); ++i) {
			for(int j = 0; j < img.getHeight(); ++j) {
				if(img.getPixel(i, j) == Color.WHITE) {
					img.setPixel(i, j, Color.TRANSPARENT);
				}
			}
		}
	}
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(img, super.getX(), super.getY(), null);
	}
	
	// Sound Methods
	public void playSound(MediaPlayer sounds, int sound, Context context) {
		Uri playId;
		
		switch(sound) {
		case INTRODUCTION:
			playId = introId;
			playedIntro = true;
			break;
		case INTERACTION:
			playId = interactionId;
			break;
		case TESTING:
			playId = testId;
			break;
		case CONGRATS:
			playId = congratsId;
			playedCongrats = true;
			break;
		default:
			throw new IllegalArgumentException("Unknown Sound ID requested");
		}
		
		super.playSound(sounds, context, playId);
	}
	
	public boolean hasPlayedIntro() {
		return playedIntro;
	}
	
	public boolean hasPlayedCongrats() {
		return playedCongrats;
	}
	
	// Event Handling methods
	public void handleEvent(MotionEvent event) {
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (tappedWithinRange(event.getX(), event.getY())) {
				tapped = true;
			} 
			break;
		default:
			break;
		}
	} 
	
	public boolean wasTapped() {
		return tapped;
	}
	
	public void setTapped(boolean status) {
		tapped = status;
	}
	
	public boolean wasCorrect() {
		return correct;
	}
	
	public void setCorrect(boolean status) {
		correct = status;
	}
	
	private boolean tappedWithinRange(float eventX, float eventY) {
		return eventX >= (super.getX()) && 
			   eventY >= (super.getY()) &&
			   eventX <= (super.getX() + img.getWidth()) &&
			   eventY <= (super.getY() + img.getHeight());
	}
}
