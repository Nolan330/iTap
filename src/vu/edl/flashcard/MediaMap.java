package vu.edl.flashcard;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;

public class MediaMap extends Map {
	
	// Used for debugging
	//private final String TAG = this.getClass().getSimpleName();
	
	private HashMap<String, Sound> soundMap = new HashMap<String, Sound>();
	static final String INTRODUCTION = "intro";
	static final String INTERACTION_TAP = "tap";
	static final String INTERACTION_DRAG = "drag";
	static final String INTERACTION_WATCH = "watch";
	static final String TESTING = "testing";
	static final String SUBSEQUENT_TEST = "second_test";
	static final String REMINDER_TAP = "r" + INTERACTION_TAP;
	static final String REMINDER_DRAG = "r" + INTERACTION_DRAG;
	static final String REMINDER_TEST = "r" + TESTING;
	static final String CELEBRATE = "celebrate";
	static final String CONGRATS = "congrats";
	static final String EMPTY = "empty";
			
	private Bitmap img;
	private String mapName;
	
	private boolean tapped = false;
	private boolean correct = false;
	
	public MediaMap(Bitmap image, HashMap<String, Sound> sMap, String mapId) {
		img = image;
		img = img.copy(Bitmap.Config.ARGB_8888, true);
		mapName = mapId;
		soundMap = sMap;
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
	
	public String getName() {
		return mapName;
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
	public void playSound(MediaPlayer sounds, String sound, Context context) {
		super.playSound(sounds, context, soundMap.get(sound));
	}
	
	public boolean hasPlayedSound(String sound) {
		return  soundMap.get(sound).hasPlayed();
	}
	
	public static String playInteraction(int test, boolean remind) {
		switch(test) {
		case FlashCardPanel.TAP_TEST:
			return remind ? MediaMap.REMINDER_TAP : MediaMap.INTERACTION_TAP;
		case FlashCardPanel.DRAG_TEST:
			return remind ? MediaMap.REMINDER_DRAG : MediaMap.INTERACTION_DRAG;
		case FlashCardPanel.PASSIVE_TEST:
			return MediaMap.INTERACTION_WATCH;
		default:
			throw new IllegalArgumentException("Unknown test requested");
		}
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
